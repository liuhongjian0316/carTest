package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.*;
import work.aijiu.entity.Infraccioninfo;
import work.aijiu.service.CarService;
import work.aijiu.service.DriverService;
import work.aijiu.service.InfraccioninfoService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class InfraccionServlet extends HttpServlet {

    private InfraccioninfoService infraccioninfoService = SpringContextHolder.getBean(InfraccioninfoService.class);

    private CarService carService = SpringContextHolder.getBean(CarService.class);

    private DriverService driverService = SpringContextHolder.getBean(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/infraccion/infraccioninfoPage")){
            infraccioninfoPage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if(req.getRequestURI().equals("/car/infraccion/getInfraccionById")){
            getInfraccionById(req.getParameter("infraccioninfoId"),out);
        }
        if(req.getRequestURI().equals("/car/infraccion/delInfraccionById")){
            delInfraccionById(req.getParameter("infraccioninfoId"),out);
        }
        if(req.getRequestURI().equals("/car/infraccion/searchInfracc")){
            String begin = null;
            String end = null;
            String infraccionTime = null;
            if(!"".equals(req.getParameter("infraccionTime"))){
                begin = req.getParameter("infraccionTime").split(" - ")[0];
                end = req.getParameter("infraccionTime").split(" - ")[1];
                infraccionTime = begin;
            }else{
                infraccionTime = "";
            }

            searchInfracc(req.getParameter("plateNumber"),req.getParameter("drivingLicenseNumber"),infraccionTime ,
                    begin ,end ,
                    new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/infraccion/selcetTicketAndTimeDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(infraccioninfoService.selcetTicketAndTimeDate())));
        }
        if("/car/infraccion/selcetTicketCostAndTimeDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(infraccioninfoService.selcetTicketCostAndTimeDate())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/infraccion/getPlateNumber")){
            getPlateNumber(out);
        }
        if(req.getRequestURI().equals("/car/infraccion/getDLN")){
            getDLN(out);
        }
        if(req.getRequestURI().equals("/car/infraccion/addInfraccion")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject jsonDatas = GetData.getJsonDatas(req, json);
            //封装javabean
            Infraccioninfo infraccioninfo = new Infraccioninfo();
            infraccioninfo.setCommentInfo(jsonDatas.getString("commentInfo"));
            infraccioninfo.setDrivingLicenseNumber(jsonDatas.getString("drivingLicenseNumber"));
            infraccioninfo.setInfraccionCost(jsonDatas.getDouble("infraccionCost"));
            infraccioninfo.setInfraccionInfo(jsonDatas.getString("infraccionInfo"));
            infraccioninfo.setInfraccionSite(jsonDatas.getString("infraccionSite"));
            infraccioninfo.setInfraccionTime(jsonDatas.getString("infraccionTime"));
            infraccioninfo.setPlateNumber(jsonDatas.getString("plateNumber"));
            infraccioninfo.setResultsOfTransaction(jsonDatas.getString("resultsOfTransaction"));
            //执行方法
            addInfraccion(infraccioninfo,out);
        }
        if(req.getRequestURI().equals("/car/infraccion/editInfraccion")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject jsonDatas = GetData.getJsonDatas(req, json);
            //封装javabean
            Infraccioninfo infraccioninfo = new Infraccioninfo();
            infraccioninfo.setCommentInfo(jsonDatas.getString("commentInfo"));
            infraccioninfo.setDrivingLicenseNumber(jsonDatas.getString("drivingLicenseNumber"));
            infraccioninfo.setInfraccionCost(jsonDatas.getDouble("infraccionCost"));
            infraccioninfo.setInfraccionInfo(jsonDatas.getString("infraccionInfo"));
            infraccioninfo.setInfraccionSite(jsonDatas.getString("infraccionSite"));
            infraccioninfo.setInfraccionTime(jsonDatas.getString("infraccionTime"));
            infraccioninfo.setPlateNumber(jsonDatas.getString("plateNumber"));
            infraccioninfo.setResultsOfTransaction(jsonDatas.getString("resultsOfTransaction"));
            infraccioninfo.setInfraccioninfoId(jsonDatas.getInteger("infraccioninfoId"));
            editInfraccion(infraccioninfo,out);
        }
        if(req.getRequestURI().equals("/car/infraccion/delAllInfraccion")){
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
                    infraccioninfoService.delById(ids[i]);
                }
                out.println(JSON.toJSONString(CarJSONResult.ok()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if("/car/infraccion/getTicket".equals(req.getRequestURI())){
            getTicket(out);
        }
        if("/car/infraccion/getPenalty".equals(req.getRequestURI())){
            getPenalty(out);
        }
    }

    /**
     * 统计罚款数
     * @param out
     */
    private void getPenalty(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(infraccioninfoService.getPenalty())));
    }

    /**
     * 获取罚单数
     * @param out
     */
    private void getTicket(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(infraccioninfoService.selAll().size())));
    }

    /**
     * 车牌号下拉框
     * @param out
     */
    protected void getPlateNumber(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(carService.selAll())));
    }

    /**
     * 驾驶证号下拉框
     * @param out
     */
    protected void getDLN(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(driverService.selAll())));
    }

    /**
     * 罚单分页
     * @param page
     * @param limit
     * @param out
     */
    protected void infraccioninfoPage(Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(new LayuiPageResult(infraccioninfoService.selAll().size(),infraccioninfoService.selPage(page,limit))));
    }

    /**
     * 添加数据
     * @param infraccioninfo
     * @param out
     */
    protected void addInfraccion(Infraccioninfo infraccioninfo,PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(infraccioninfoService.insert(infraccioninfo))));
    }

    /**
     * 编辑模态框赋值
     * @param infraccioninfoId
     * @param out
     */
    protected void getInfraccionById(String infraccioninfoId, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(infraccioninfoService.selById(infraccioninfoId))));
    }

    /**
     * 修改罚单信息
     * @param infraccioninfo
     * @param out
     */
    protected void editInfraccion(Infraccioninfo infraccioninfo,PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(infraccioninfoService.update(infraccioninfo))));
    }

    /**
     * 删除罚单
     * @param infraccioninfoId
     * @param out
     */
    protected void delInfraccionById(String infraccioninfoId, PrintWriter out){
        if(infraccioninfoService.delById(infraccioninfoId)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 搜索
     * @param plateNumber
     * @param drivingLicenseNumber
     * @param infraccionTime
     * @param page
     * @param limit
     * @param out
     */
    protected void searchInfracc(String plateNumber,String drivingLicenseNumber,String infraccionTime,
                                 String begin , String end,
                                 Integer page,Integer limit,PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(new LayuiPageResult(infraccioninfoService.selAll().size(),infraccioninfoService.search(plateNumber,drivingLicenseNumber,infraccionTime,begin,end,page,limit))));
    }
}
