package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import work.aijiu.common.utils.CarJSONResult;
import work.aijiu.common.utils.GetData;
import work.aijiu.common.utils.SetHeader;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.entity.Approver;
import work.aijiu.entity.TDispatch;
import work.aijiu.service.ApproverService;
import work.aijiu.service.DriverService;
import work.aijiu.service.TDispatchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket")
public class ApproverServlet extends HttpServlet {


    private ApproverService approverService = SpringContextHolder.getBean(ApproverService.class);

    private TDispatchService tDispatchService = SpringContextHolder.getBean(TDispatchService.class);

    private DriverService driverService = SpringContextHolder.getBean(DriverService.class);
    private static CopyOnWriteArraySet<ApproverServlet> webSocketSet = new CopyOnWriteArraySet<ApproverServlet>();
    //这个session不是Httpsession，相当于用户的唯一标识，用它进行与指定用户通讯
    private  javax.websocket.Session session=null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/approver/untreateDate".equals(req.getRequestURI())){
            untreateDate(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),req.getParameter("departmentId"),out);
        }
        if("/car/approver/untreatedDetail".equals(req.getRequestURI())){
            untreatedDetail(req.getParameter("applicationid"),out);
        }
        if("/car/approver/updateState2".equals(req.getRequestURI())){
            updateState2(req.getParameter("approverState"),req.getParameter("applicationid"),req,resp,out);
        }
        if("/car/approver/treateDate".equals(req.getRequestURI())){
            treateDate(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")), req.getParameter("departmentId"),out);
        }
        if("/car/approver/delApp".equals(req.getRequestURI())){
            delApp(req.getParameter("approverState"),req.getParameter("applicationid"),out);
        }
        if("/car/approver/alertsData".equals(req.getRequestURI())){
            alertsData(req.getParameter("applicationName") ,new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/approver/read".equals(req.getRequestURI())){
            read(req.getParameter("applicationid"),out);
        }
        if("/car/approver/getredis".equals(req.getRequestURI())){
            //从redis中读取草稿数据
            String name = req.getParameter("name");
            Approver approver = approverService.getRedis(name);
            out.println(JSON.toJSONString(approver));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/approver/updateState".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject newJson = GetData.getJsonDatas(req, json);
            Approver approver = new Approver();
            approver.setApplicationName(newJson.getString("applicationName"));
            approver.setApplicationid(newJson.getString("applicationid"));
            approver.setApproverComment(newJson.getString("approverComment"));
            approver.setDepartmentId(newJson.getString("departmentId"));
            approver.setDestination(newJson.getString("destination"));
            approver.setDestinationTime(newJson.getString("destinationTime"));
            approver.setLeaveTime(newJson.getString("leaveTime"));
            approver.setReason(newJson.getString("reason"));
            approver.setReturnTime(newJson.getString("returnTime"));
            approver.setTel(newJson.getString("tel"));
            updateState(req.getParameter("approverState"),approver,req,resp,out);
        }
        if("/car/approver/toapplyfor".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject newJson = GetData.getJsonDatas(req, json);
            Approver approver = new Approver();
            approver.setApplicationName(newJson.getString("applicationName"));
            approver.setApplicationNumber(newJson.getString("applicationNumber"));
            approver.setApproverComment(newJson.getString("approverComment"));
            approver.setDepartmentId(newJson.getString("departmentId"));
            approver.setDestination(newJson.getString("destination"));
            approver.setLeaveTime(newJson.getString("leaveTime"));
            approver.setReason(newJson.getString("reason"));
            approver.setReturnTime(newJson.getString("returnTime"));
            approver.setTel(newJson.getString("tel"));
            toapplyfor(approver,out);
        }
        if("/car/approver/savetoRedis".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject newJson = GetData.getJsonDatas(req, json);
            Approver approver = new Approver();
            approver.setApplicationName(newJson.getString("applicationName"));
            approver.setApplicationNumber(newJson.getString("applicationNumber"));
            approver.setApproverComment(newJson.getString("approverComment"));
            approver.setDepartmentId(newJson.getString("departmentId"));
            approver.setDestination(newJson.getString("destination"));
            approver.setLeaveTime(newJson.getString("leaveTime"));
            approver.setReason(newJson.getString("reason"));
            approver.setReturnTime(newJson.getString("returnTime"));
            approver.setTel(newJson.getString("tel"));
            //写入缓存
            if (approverService.saveRedis(approver)) {
                out.println(JSON.toJSONString(CarJSONResult.ok(true)));
            } else {
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("存入草稿失败")));
            }
        }
        if("/car/approver/getAppNum".equals(req.getRequestURI())){
            getAppNum(out);
        }
    }

    /**
     * 未还车表格分页
     * @param page
     * @param limit
     * @param out
     */
    protected void untreateDate(Integer page, Integer limit,String departmentId, PrintWriter out){
        out.println(JSON.toJSONString(approverService.UntreatedPage(page, limit,departmentId)));
    }

    /**
     * 获取未处理详情
     * @param applicationid
     * @param out
     */
    protected void untreatedDetail(String applicationid, PrintWriter out){
        out.println(JSON.toJSONString(new CarJSONResult(approverService.selById(applicationid))));
    }

    /**
     * 按钮批复驳回
     * @param updateState
     * @param applicationid
     * @param request
     * @param response
     * @param out
     */
    protected void updateState2(String updateState,String applicationid,HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws IOException {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String approverTime = sdf.format(date);
        //如果状态2即同意，就将申请人编号，申请人姓名，联系电话，部门，用车时间，实际出车时间，申请表编号
        if(updateState.equals("2")){
            //根据申请表的id获取要添加的信息
            List<Approver> approvers = approverService.selById(applicationid);
            approvers.get(0).getApplicationName();
            TDispatch tDispatch = new TDispatch();
            tDispatch.setDriverlicensenumber(driverService.selPlateByName(approvers.get(0).getApplicationName()).get(0).getDrivinglicenseNumber());//驾驶证号
            tDispatch.setDrivername(approvers.get(0).getApplicationName());//申请人姓名
            tDispatch.setUsingtime(approvers.get(0).getLeaveTime());//用车时间
            tDispatch.setApplicationid(approvers.get(0).getApplicationid());//申请表编号
            tDispatch.setApplicationnumber(id);//申请人的id
            tDispatch.setState("0");//开始状态
            tDispatch.setNamestate("0");//名字可被读取
            //将信息添加到调度表中
            if(tDispatchService.insert(tDispatch)){
                sendMessage();
                sendMessage3();
            }
        }
        int i = approverService.updateStateById(id, id, approverTime, updateState, applicationid);
        if(i>0){
            sendMessage3();
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }else {
            out.println(JSON.toJSONString(CarJSONResult.errorMsg("操作失败")));
            return;
        }
    }

    /**
     * 修改状态
     * @param updateState
     * @param approver
     * @param request
     * @param response
     */
    protected void updateState(String updateState,Approver approver, HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws IOException {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String approverTime = sdf.format(date);
        //如果状态2即同意，就将申请人编号，申请人姓名，联系电话，部门，用车时间，实际出车时间，申请表编号
        if(updateState.equals("2")){
            //根据申请表的id获取要添加的信息
            List<Approver> approvers = approverService.selById(approver.getApplicationid());
            TDispatch tDispatch = new TDispatch();
            tDispatch.setDriverlicensenumber(driverService.selPlateByName(approver.getApplicationName()).get(0).getDrivinglicenseNumber());//驾驶证号
            tDispatch.setDrivername(approver.getApplicationName());//申请人姓名
            tDispatch.setUsingtime(approvers.get(0).getLeaveTime());//用车时间
            tDispatch.setApplicationid(approver.getApplicationid());//申请表编号
            tDispatch.setApplicationnumber(id);//申请人的id
            tDispatch.setState("0"); //开始状态
            tDispatch.setNamestate("0");//名字可被读取
            //将信息添加到调度表中
            if(tDispatchService.insert(tDispatch)){
                sendMessage();
                sendMessage3();
            }
        }
        int i = approverService.updateState(id, id, approverTime, updateState, approver.getApproverComment(), approver.getApplicationid());
        if(i>0){
            sendMessage3();
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }else {
            out.println(JSON.toJSONString(CarJSONResult.errorMsg("操作失败")));
            return;
        }
    }

    /**
     * 已处理分页
     * @param page
     * @param limit
     * @param out
     */
    protected void treateDate(Integer page, Integer limit, String departmentId ,PrintWriter out){
        out.println(JSON.toJSONString(approverService.treatedPage(page, limit,departmentId)));
    }

    /**
     * 删除申请单
     * @param approverState
     * @param applicationid
     * @param out
     */
    protected void delApp(String approverState,String applicationid, PrintWriter out){
        if(approverService.delApp(approverState, applicationid)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 未读信息表分页
     * @param page
     * @param limit
     * @param out
     */
    protected void alertsData(String applicationName,Integer page, Integer limit, PrintWriter out){
        out.println(JSON.toJSONString(approverService.selIsReadPage(applicationName,page, limit)));
    }

    /**
     * 未读信息变已读信息
     * @param applicationid
     */
    protected void read(String applicationid,PrintWriter out){
        if(approverService.read("1", applicationid)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("阅读失败")));
    }

    /**
     * 申请用车
     * @param approver
     * @param out
     */
    protected void toapplyfor(Approver approver,PrintWriter out) throws IOException {
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        approver.setDestinationTime(time);
        approver.setApproverState("1");//1 申请中
        approver.setIsRead("0"); //0 未读
        int applay = approverService.applay(approver);
        if(applay > 0){
            sendMessage2();
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 总申请单数
     * @param out
     */
    protected void getAppNum(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(approverService.selAll().size())));
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println("Session " + session.getId() + " has opened a connection");
        try {
            this.session=session;
            webSocketSet.add(this);     //加入set中
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
    }
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);  //从set中删除
        System.out.println("Session " +session.getId()+" has closed!");
    }
    @OnError
    public void onError(Session session, Throwable t) {
        t.printStackTrace();
    }
    public void sendMessage() throws IOException {
        //群发消息
        for (ApproverServlet item : webSocketSet) {
            try {
                //像前台发送信息1 前台接收 刷新页面
                item.session.getBasicRemote().sendText("1");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    public void sendMessage2() throws IOException {
        //群发消息
        for (ApproverServlet item : webSocketSet) {
            try {
                //像前台发送信息1 前台接收 刷新页面
                item.session.getBasicRemote().sendText("2");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
    public void sendMessage3() throws IOException {
        //群发消息
        for (ApproverServlet item : webSocketSet) {
            try {
                //像前台发送信息1 前台接收 刷新页面
                item.session.getBasicRemote().sendText("3");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
