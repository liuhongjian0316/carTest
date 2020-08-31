package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import work.aijiu.common.utils.CarJSONResult;
import work.aijiu.common.utils.SetHeader;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.service.DrivertypeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DrivertypeServlet extends HttpServlet {

    private DrivertypeService drivertypeService = SpringContextHolder.getBean(DrivertypeService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/drivertype/driverTypeName")){
            driverTypeName(out);
        }
    }

    /**
     * 下拉框获取驾驶证类型
     * @param out
     */
    protected void driverTypeName(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(drivertypeService.selAll())));
    }
}
