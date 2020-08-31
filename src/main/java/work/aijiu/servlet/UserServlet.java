package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import work.aijiu.common.utils.*;
import work.aijiu.dao.UserDao;
import work.aijiu.entity.Log;
import work.aijiu.entity.Setting;
import work.aijiu.entity.User;
import work.aijiu.listener.MyHttpSessionListener;
import work.aijiu.service.SettingService;
import work.aijiu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private UserService userService = SpringContextHolder.getBean(UserService.class);
    private SettingService settingService = SpringContextHolder.getBean(SettingService.class);
    private String oldaccount = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/user/userPage")){
            userPage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if(req.getRequestURI().equals("/car/user/delUserById")){
            delUserById(req.getParameter("userinfoid"),out);
        }
        if(req.getRequestURI().equals("/car/user/getUserById")){
            getUserById(req.getParameter("userinfoid"),out);
        }
        if(req.getRequestURI().equals("/car/user/checkAccount")){
            checkAccount(req.getParameter("userAccount"),out);
        }
        if(req.getRequestURI().equals("/car/user/checkName")){
            checkName(req.getParameter("userName"),out);
        }
        if(req.getRequestURI().equals("/car/user/checkTel")){
            checkTel(req.getParameter("tel"),out);
        }
        if(req.getRequestURI().equals("/car/user/searchUser")){
            searchUser(req.getParameter("userAccount"),req.getParameter("tel"),req.getParameter("departName"),
                    new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/user/getSexDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(userService.selectSexDate())));
        }
        if("/car/user/getRoleDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(userService.selectRoleDate())));
        }
        if("/car/user/getDepartmentDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(userService.selectDeaprtmentDate())));
        }
        if("/car/user/getsesson".equals(req.getRequestURI())){
            List<User> users = (List<User>)req.getSession().getAttribute("users");
            out.println(JSON.toJSONString(users));
        }
        if("/car/user/destroySession".equals(req.getRequestURI())){
            //获取session
            HttpSession session = req.getSession();
            //去除session里的值
            session.removeAttribute("users");
            session.removeAttribute("id");
            //销毁session
            session.invalidate();
            out.println(JSON.toJSONString(CarJSONResult.ok()));
        }
        if("/car/user/oldaccount".equals(req.getRequestURI())){
            oldaccount = req.getParameter("account");
        }
        if("/car/user/checkAccount2".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(userService.checkAccount2(req.getParameter("userAccount"),oldaccount))));
        }
        if("/car/user/loginlog".equals(req.getRequestURI())){
            List<Log> list = userService.getloginLog();
            out.println(JSON.toJSONString(new LayuiPageResult(list.size(),list)));
        }
        if("/car/user/getsetting".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(settingService.queryById(1))));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/user/editUser")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject userJson = GetData.getJsonDatas(req, json);
            User user = new User();
            user.setAddr(userJson.getString("addr"));
            user.setDepartName(userJson.getString("departName"));
            user.setPwd(userJson.getString("pwd"));
            user.setRoleid(userJson.getString("roleid"));
            user.setSex(userJson.getString("sex"));
            user.setTel(userJson.getString("tel"));
            user.setUserAccount(userJson.getString("userAccount"));
            user.setUserName(userJson.getString("userName"));
            user.setUserinfoid(userJson.getString("userinfoid"));
            editUser(user,out);
        }
        if(req.getRequestURI().equals("/car/user/addUser")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject userJson = GetData.getJsonDatas(req, json);
            User user = new User();
            user.setAddr(userJson.getString("addr"));
            user.setDepartName(userJson.getString("departName"));
            user.setPwd(userJson.getString("pwd"));
            user.setRoleid(userJson.getString("roleid"));
            user.setSex(userJson.getString("sex"));
            user.setTel(userJson.getString("tel"));
            user.setUserAccount(userJson.getString("userAccount"));
            user.setUserName(userJson.getString("userName"));
            addUser(user,out);
        }
        if("/car/user/addpermissions".equals(req.getRequestURI())){
            addpermissions(req.getParameter("userinfoid"),req.getParameter("roloid"),out);
        }
        if("/car/user/countUser".equals(req.getRequestURI())){
            countUser(out);
        }
        if("/car/user/ordUser".equals(req.getRequestURI())){
            ordUser(out);
        }
        if("/car/user/online".equals(req.getRequestURI())){
            HttpSession session = req.getSession();
            out.println(JSON.toJSONString(CarJSONResult.ok(MyHttpSessionListener.online++)));
        }
        if("/car/user/getAdminUserNum".equals(req.getRequestURI())){
            getAdminUserNum(out);
        }
        if("/car/user/updateSetting".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject settingJson = GetData.getJsonDatas(req, json);
            String saveurl = settingJson.getString("saveurl");
            String serveurl = settingJson.getString("serveurl");
            Setting setting = new Setting();
            setting.setId(1);
            setting.setSaveurl(saveurl);
            setting.setServeurl(serveurl);
            if(settingService.update(setting)>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("配置成功")));
            }else {
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("配置失败")));
            }
        }

    }

    /**
     * 获取管理员总数
     * @param out
     */
    private void getAdminUserNum(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(userService.countAdmin())));
    }

    /**
     * 统计普通用户
     * @param out
     */
    private void ordUser(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(userService.countOrdUser())));
    }

    /**
     * 统计总用户
     * @param out
     */
    private void countUser(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(userService.countUser())));
    }

    /**
     * 用户信息分页
     * @param page
     * @param limit
     * @param out
     */
    protected void userPage(Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(userService.userPage(page,limit)));
    }

    /**
     * 根据id删除用户信息
     * @param userinfoid
     * @param out
     */
    protected void delUserById(String userinfoid,PrintWriter out){
        if(userService.delete(userinfoid)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 编辑模态框赋值
     * @param userinfoid
     * @param out
     */
    protected void getUserById(String userinfoid, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(userService.selById(userinfoid))));
    }

    /**
     * 编辑用户
     * @param user
     * @param out
     */
    protected void editUser(User user, PrintWriter out){
        if(userService.update(user)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
    }

    /**
     * 添加用户
     * @param user
     * @param out
     */
    protected void addUser(User user, PrintWriter out){
        if(userService.insertUser(user)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return ;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 检查账号是否重复
     * @param userAccount
     * @param out
     */
    protected void checkAccount(String userAccount,PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(userService.checkAccount(userAccount))));
    }

    /**
     * 检查名字是否重复
     * @param userName
     * @param out
     */
    protected void checkName(String userName, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(userService.checkName(userName))));
    }

    /**
     * 检查手机号是否重复
     * @param tel
     * @param out
     */
    protected void checkTel(String tel, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(userService.checkTel(tel))));
    }

    /**
     * 搜索用户
     * @param userAccount
     * @param tel
     * @param departName
     * @param page
     * @param limit
     * @param out
     */
    protected void searchUser(String userAccount, String tel, String departName, Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(userService.search(userAccount,tel,departName,page,limit)));
    }

    /**
     * 授权
     * @param userinfoid
     * @param roleid
     * @param out
     */
    protected void addpermissions(String userinfoid, String roleid,PrintWriter out){
        if(userService.addPermission(roleid,userinfoid)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("操作失败")));
    }
}
