package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import work.aijiu.common.utils.*;
import work.aijiu.entity.Driver;
import work.aijiu.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class DriverServlet extends HttpServlet {

    private DriverService driverService = SpringContextHolder.getBean(DriverService.class);
    private String olddrivinglicenseNumber = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/driver/driverPage")){
            driverPage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if(req.getRequestURI().equals("/car/driver/getDriverById")){
            getDriverById(req.getParameter("driverinfoId"),out);
        }
        if(req.getRequestURI().equals("/car/driver/searchDriver")){
            searchDriver(req.getParameter("name"),req.getParameter("sex"),req.getParameter("drivingType3"),
                    req.getParameter("number"),req.getParameter("state"),req.getParameter("tel"),
                    new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/driver/olddrivinglicenseNumber".equals(req.getRequestURI())){
            olddrivinglicenseNumber = req.getParameter("olddrivinglicenseNumber");
        }
        if("/car/driver/checkDriverNumIsRep2".equals(req.getRequestURI())){
            out.println( JSON.toJSONString(CarJSONResult.ok(driverService.checkIsRep2(req.getParameter("drivinglicenseNumber"),olddrivinglicenseNumber))));
        }
        if("/car/driver/checkDriverNumIsRep".equals(req.getRequestURI())){
            checkDriverNumIsRep(req.getParameter("drivinglicenseNumber"),out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/driver/addDriver")){
            //接收数据
            JSONObject json = new JSONObject();
            JSONObject driverJson = GetData.getJsonDatas(req, json);
            //逐条获取数据
            String commentInfo = driverJson.getString("commentInfo");
            String driverAddress = driverJson.getString("driverAddress");
            String driverAge = driverJson.getString("driverAge");
            String driverName = driverJson.getString("driverName");
            String driverNumber = driverJson.getString("driverNumber");
            String driverSex = driverJson.getString("driverSex");
            String drivingType = driverJson.getString("drivingType");
            String drivinglicenseNumber = driverJson.getString("drivinglicenseNumber");
            String telNumber = driverJson.getString("telNumber");
            Driver driver = new Driver();
            driver.setCommentInfo(commentInfo);
            driver.setDriverAddress(driverAddress);
            driver.setDriverAge(driverAge);
            driver.setDriverName(driverName);
            driver.setDriverNumber(driverNumber);
            driver.setDriverSex(driverSex);
            driver.setDrivingType(drivingType);
            driver.setDrivinglicenseNumber(drivinglicenseNumber);
            driver.setTelNumber(telNumber);
            driver.setState("1");
            addDriver(driver,out);
        }
        if(req.getRequestURI().equals("/car/driver/editDriver")){
            //接收数据
            JSONObject json = new JSONObject();
            JSONObject driverJson = GetData.getJsonDatas(req, json);
            //逐条获取数据
            String commentInfo = driverJson.getString("commentInfo");
            String driverAddress = driverJson.getString("driverAddress");
            String driverAge = driverJson.getString("driverAge");
            String driverName = driverJson.getString("driverName");
            String driverNumber = driverJson.getString("driverNumber");
            String driverSex = driverJson.getString("driverSex");
            String drivingType = driverJson.getString("drivingType");
            String drivinglicenseNumber = driverJson.getString("drivinglicenseNumber");
            String telNumber = driverJson.getString("telNumber");
            Integer driverinfoId = driverJson.getInteger("driverinfoId");
            Driver driver = new Driver();
            driver.setCommentInfo(commentInfo);
            driver.setDriverAddress(driverAddress);
            driver.setDriverAge(driverAge);
            driver.setDriverName(driverName);
            driver.setDriverNumber(driverNumber);
            driver.setDriverSex(driverSex);
            driver.setDrivingType(drivingType);
            driver.setDrivinglicenseNumber(drivinglicenseNumber);
            driver.setTelNumber(telNumber);
            driver.setDriverinfoId(driverinfoId);
            driver.setState("1");
            editDriver(driver,out);
        }
        if(req.getRequestURI().equals("/car/driver/delAllDriver")){
            //获取数据
            BufferedReader br;
            try {
                br = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
                String line = null;
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                //去掉首尾[]
                String s1 = StringUtil.trimFirstAndLastChar(sb.toString(), '[');
                String s2 = StringUtil.trimFirstAndLastChar(s1, ']');
                //转数组
                String[] ids = s2.split(",");
                //循环删除
                for (int i = 0; i < ids.length; i++) {
                    driverService.deleteById(ids[i]);
                }
                out.println(JSON.toJSONString(CarJSONResult.ok()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(req.getRequestURI().equals("/car/driver/delDriver")){
            delDriver(req.getParameter("driverinfoId"),out);
        }
        if("/car/driver/countDriver".equals(req.getRequestURI())){
            countDriver(out);
        }
        if("/car/driver/ableDriver".equals(req.getRequestURI())){
            ableDriver(out);
        }
    }

    /**
     * 统计有效驾驶员
     * @param out
     */
    private void ableDriver(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(driverService.countAbleDriver())));
    }

    /**
     * 统计所有驾驶员
     * @param out
     */
    private void countDriver(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(driverService.countDriver())));
    }

    /**
     * 驾驶员信息分页
     * @param page
     * @param limit
     * @param out
     */
    protected void driverPage(Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(new LayuiPageResult(driverService.selAll().size(),driverService.queryAllByLimit(page,limit))));
    }

    /**
     * 添加驾驶员信息
     * @param driver
     * @param out
     */
    protected void addDriver(Driver driver, PrintWriter out){
        if(driverService.insert(driver)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 修改模态框赋值
     * @param driverinfoId
     * @param out
     */
    protected void getDriverById(String driverinfoId, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(driverService.queryById(driverinfoId))));
    }

    /**
     * 修改车辆
     * @param driver
     * @param out
     */
    protected void editDriver(Driver driver, PrintWriter out){
        if(driverService.update(driver)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
    }

    /**
     * 单条删除
     * @param driverinfoId
     * @param out
     */
    protected void delDriver(String driverinfoId,PrintWriter out){
        if(driverService.deleteById(driverinfoId)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 检查驾驶证号是否重复
     * @param drivinglicenseNumber
     * @param out
     */
    protected void checkDriverNumIsRep(String drivinglicenseNumber,PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(driverService.checkIsRep(drivinglicenseNumber)>0)));
    }

    protected void searchDriver(String name, String sex, String drivingType3,
                                String number, String state, String tel,
                                Integer page, Integer limit,PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(new LayuiPageResult(driverService.selAll().size(),driverService.queryAll(name,sex,number,state,drivingType3,tel,page,limit))));
    }
}
