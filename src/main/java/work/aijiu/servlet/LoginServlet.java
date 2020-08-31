package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work.aijiu.common.utils.CarJSONResult;
import work.aijiu.common.utils.GetData;
import work.aijiu.common.utils.SetHeader;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.entity.User;
import work.aijiu.listener.MyHttpSessionListener;
import work.aijiu.service.UserService;
import work.aijiu.service.impl.UserServiceImpl;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet  extends HttpServlet {

    @Autowired
    private UserService userService = SpringContextHolder.getBean(UserService.class);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        login(req,resp,out);
    }

    /**
     * 登录
     * @param req
     * @param resp
     * @param out
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp,PrintWriter out) throws IOException {
        //接收json数据
        JSONObject jsonDatas = GetData.getJsonDatas(req, new JSONObject());
        //获取userAccount和pwd
        String userAccount = jsonDatas.getString("userAccount");
        String pwd = jsonDatas.getString("pwd");
        //登录逻辑
        List<User> users = userService.login(userAccount, pwd);
        if(users.size() > 0){
            req.getSession().setAttribute("users",users);
            req.getSession().setAttribute("id",users.get(0).getUserinfoid());
            Cookie userAccountCookie = new Cookie("userAccount",userAccount);
            userAccountCookie.setMaxAge(60*60*24*7);
            resp.addCookie(userAccountCookie);
            Cookie pwdCookie = new Cookie("pwd",pwd);
            pwdCookie.setMaxAge(60*60*24*7);
            resp.addCookie(pwdCookie);
            userService.savelog(userAccount,pwd);
            out.println(JSON.toJSONString(CarJSONResult.ok()));
        }else{
            out.println(JSON.toJSONString(CarJSONResult.errorMsg("登录失败")));
        }
    }

}
