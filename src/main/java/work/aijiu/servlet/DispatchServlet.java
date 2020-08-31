package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import work.aijiu.common.utils.CarJSONResult;
import work.aijiu.common.utils.GetData;
import work.aijiu.common.utils.SetHeader;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.entity.TDispatch;
import work.aijiu.entity.User;
import work.aijiu.service.ApproverService;
import work.aijiu.service.CarService;
import work.aijiu.service.TDispatchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DispatchServlet extends HttpServlet {

    private TDispatchService tDispatchService = SpringContextHolder.getBean(TDispatchService.class);
    private ApproverService approverService = SpringContextHolder.getBean(ApproverService.class);
    private CarService carService = SpringContextHolder.getBean(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/dispatch/returnMesAdminPage".equals(req.getRequestURI())){
            returnMesAdminPage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),req.getParameter("departmentId"),out);
        }
        if("/car/dispatch/delDispatch".equals(req.getRequestURI())){
            delDispatch(new Integer(req.getParameter("dispatchid")),out);
        }
        if("/car/dispatch/getDispatchById".equals(req.getRequestURI())){
            getDispatchById(new Integer(req.getParameter("dispatchid")),out);
        }
        if("/car/dispatch/unreturn".equals(req.getRequestURI())){
            unreturn(req,new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/dispatch/returnCar".equals(req.getRequestURI())){
            returnCar(req.getParameter("carNumber"),req.getParameter("dispatchid"),out);
        }
        if("/car/dispatch/returnMesPage".equals(req.getRequestURI())){
            returnMesPage(req,new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/dispatch/getNameInDis".equals(req.getRequestURI())){
            String driverName = req.getParameter("driverName");
            out.println(JSON.toJSONString(tDispatchService.NameIsZero(driverName)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/dispatch/getApproverName".equals(req.getRequestURI())){
            getApproverName(req.getParameter("departmentId"),out);
        }
        if("/car/dispatch/editDispatch".equals(req.getRequestURI())){
            JSONObject json = new JSONObject();
            JSONObject newJson = GetData.getJsonDatas(req, json);
            TDispatch tDispatch = new TDispatch();
            tDispatch.setActualtime(newJson.getString("actualtime"));
            tDispatch.setApplicationid(newJson.getString("applicationid"));
            tDispatch.setApplicationnumber(newJson.getString("applicationnumber"));
            tDispatch.setBeintime(newJson.getString("beintime"));
            tDispatch.setCarNumber(newJson.getString("carNumber"));
            tDispatch.setDispatchcommment(newJson.getString("dispatchcommment"));
            tDispatch.setDispatchid(newJson.getInteger("dispatchid"));
            tDispatch.setDriverlicensenumber(newJson.getString("driverlicensenumber"));
            tDispatch.setDrivername(newJson.getString("drivername"));
            tDispatch.setKmafter(newJson.getDouble("kmafter"));
            tDispatch.setKmbefore(newJson.getDouble("kmbefore"));
            tDispatch.setRunroute(newJson.getString("runroute"));
            tDispatch.setToll(newJson.getDouble("toll"));
            tDispatch.setUsingtime(newJson.getString("usingtime"));
            editDispatch(tDispatch,out);
        }
        if("/car/dispatch/getFinishNum".equals(req.getRequestURI())){
            getFinishNum(out);
        }
    }

    /**
     * 统计完成单数
     * @param out
     */
    private void getFinishNum(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(tDispatchService.selAll().size())));
    }

    /**
     * 获取申请人姓名
     * @param out
     */
    protected void getApproverName(String departmentId,PrintWriter out){
        //0 开始 1 结束
        out.println(JSON.toJSONString(CarJSONResult.ok(approverService.selappName(departmentId))));
    }

    /**
     * 换车记录后台
     * @param page
     * @param limit
     * @param out
     */
    protected void returnMesAdminPage(Integer page, Integer limit,String departmentId, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tDispatchService.retuenMsgAdmin(page,limit,departmentId)));
    }

    /**
     * 删除调度信息
     * @param dispatchid
     * @param out
     */
    protected void delDispatch(Integer dispatchid,PrintWriter out){
        if(tDispatchService.deleteById(dispatchid)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 编辑模态框赋值
     * @param dispatchid
     * @param out
     */
    protected void getDispatchById(Integer dispatchid, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(tDispatchService.selById(dispatchid))));
    }

    /**
     * 填充调度信息
     * @param tDispatch
     * @param out
     */
    protected void editDispatch(TDispatch tDispatch, PrintWriter out){
        if(tDispatchService.update(tDispatch)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return ;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("填充数据失败")));
    }

    /**
     * 换车分页
     * @param page
     * @param limit
     * @param out
     */
    protected void unreturn(HttpServletRequest request,Integer page,Integer limit,PrintWriter out){

        HttpSession session = request.getSession();
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tDispatchService.unreturnPage(((List<User>) session.getAttribute("users")).get(0).getUserName(),page,limit)));
    }

    protected void returnCar(String carNumber,String dispatchid,PrintWriter out){
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        TDispatch tDispatch = new TDispatch();
        tDispatch.setDispatchid(new Integer(dispatchid));
        tDispatch.setState("1");//结束
        tDispatch.setBeintime(time);//入库时间
        if(tDispatchService.update(tDispatch) && carService.updateStateByNumber("1",carNumber )){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("换车失败")));
    }

    /**
     * 换车记录
     * @param request
     * @param page
     * @param limit
     * @param out
     */
    protected void returnMesPage(HttpServletRequest request,Integer page,Integer limit,PrintWriter out){
        HttpSession session = request.getSession();
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tDispatchService.retuenMsg(((List<User>) session.getAttribute("users")).get(0).getUserName(),page,limit)));
    }
}
