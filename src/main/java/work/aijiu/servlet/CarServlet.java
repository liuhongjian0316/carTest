package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import work.aijiu.VO.CarinfoVO;
import work.aijiu.common.utils.*;
import work.aijiu.dao.CarinfoVODao;
import work.aijiu.entity.Carinfo;
import work.aijiu.entity.TDispatch;
import work.aijiu.service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CarServlet extends HttpServlet {

    private CarService carService = SpringContextHolder.getBean(CarService.class);
    private CarinfoVOService carinfoVOService = SpringContextHolder.getBean(CarinfoVOService.class);
    private TDispatchService tDispatchService = SpringContextHolder.getBean(TDispatchService.class);
    private ApproverService approverService = SpringContextHolder.getBean(ApproverService.class);
    private String oldplateNumber;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/carinfo/carPage".equals(req.getRequestURI())){
            getCarinfoPage(new Integer(req.getParameter("limit")),new Integer(req.getParameter("page")),out);
        }
        if("/car/carinfo/getCarById".equals(req.getRequestURI())){
            getCarById(req.getParameter("carinfoId"),out);
        }
        if("car/carinfo/delCar".equals(req.getRequestURI())){
            //获取车辆id
            String carinfoId = req.getParameter("carinfoId");
            //执行删除方法
            delCar(carinfoId,out);
        }
        if("/car/carinfo/searchCar".equals(req.getRequestURI())){
            //接收数据 封装成javabean
            Carinfo carinfo = new Carinfo();
            carinfo.setPlateNumber(req.getParameter("carNum"));
            carinfo.setVehicleClassname(req.getParameter("cartype"));
            carinfo.setBrandModel(req.getParameter("model"));
            carinfo.setState(req.getParameter("state"));
            //处理时间 2020-07-10 - 2020-08-08
            String regtime = null;
            String regbeg = null;
            String regend = null;
            String examtime = null;
            String exbeg = null;
            String exend = null;
            if(!"".equals(req.getParameter("regtime"))){
                //处理时间 2020-07-10 - 2020-08-08
                regtime = req.getParameter("regtime");

                regbeg = regtime.split(" - ")[0];
                regend = regtime.split(" - ")[1];
                carinfo.setRegTime(regbeg);
            }else{
                carinfo.setRegTime("");
            }
            if(!"".equals(req.getParameter("examtime"))){
                 examtime = req.getParameter("examtime");
                 exbeg = examtime.split(" - ")[0];
                 exend = examtime.split(" - ")[1];
                 carinfo.setExaminationTime(exbeg);
            }else{
                carinfo.setExaminationTime("");
            }
            //执行查询方法
            searchCar(carinfo,regbeg,regend,exbeg,exend,new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if(req.getRequestURI().equals("/car/carinfo/checkNumIsRep")){
            checkNumIsRep(req.getParameter("plateNumber"),out);
        }
        if("/car/carinfo/checkNumIsRep2".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(carService.checkCarNum2(req.getParameter("plateNumber"),oldplateNumber))));
        }
        if("/car/carinfo/getRepairCarPage".equals(req.getRequestURI())){
            getRepairCarPage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/carinfo/repairCar".equals(req.getRequestURI())){
            repairCar(req.getParameter("carinfoId"),out);
        }
        if("/car/carinfo/getCarStateDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(carService.getCarStateDate())));
        }
        if("/car/carinfo/getConsumption".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(carService.getConsumption())));
        }
        if("/car/carinfo/oldplateNumber".equals(req.getRequestURI())){
            oldplateNumber = req.getParameter("plateNumber");
        }
        if("/car/carinfo/selAll".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(carService.selAll())));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        //修改车辆信息
        if("/car/carinfo/editCar".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject carJson = GetData.getJsonDatas(req, json);
            //逐条获取数据
            Integer carinfoId = carJson.getInteger("carinfoId");
            String plateNumber = carJson.getString("plateNumber");
            String vehicleClassname = carJson.getString("vehicleClassname");
            String frameNumber = carJson.getString("frameNumber");
            String factoryNumber = carJson.getString("factoryNumber");
            String brandModel = carJson.getString("brandModel");
            String regTime = carJson.getString("regTime");
            String examinationTime = carJson.getString("examinationTime");
            Integer ratedPassengers = carJson.getInteger("ratedPassengers");
            String state = carJson.getString("state");
            String commentInfo = carJson.getString("commentInfo");
            //封装javabean
            Carinfo carinfo = new Carinfo(carinfoId,plateNumber,vehicleClassname,
                    frameNumber,factoryNumber,brandModel,regTime,examinationTime,
                    ratedPassengers,state,commentInfo);
            //调用修改汽车
            editCar(carinfo,out);
        }
        //添加车辆
        if("/car/carinfo/addCar".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject carJson = GetData.getJsonDatas(req, json);
            //逐条获取数据
            String plateNumber = carJson.getString("plateNumber");
            String vehicleClassname = carJson.getString("vehicleClassname");
            String frameNumber = carJson.getString("frameNumber");
            String factoryNumber = carJson.getString("factoryNumber");
            String brandModel = carJson.getString("brandModel");
            String regTime = carJson.getString("regTime");
            String examinationTime = carJson.getString("examinationTime");
            Integer ratedPassengers = carJson.getInteger("ratedPassengers");
            String commentInfo = carJson.getString("commentInfo");
            //封装成javabean
            Carinfo carinfo = new Carinfo(null,plateNumber,vehicleClassname,
                    frameNumber,factoryNumber,brandModel,regTime,examinationTime,
                    ratedPassengers,"1",commentInfo);
            //执行添加车辆
            addCar(carinfo,out);
        }
        //批量删除车辆信息
        if("/car/carinfo/delAllCar".equals(req.getRequestURI())){
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
                    carService.delCar(ids[i]);
                }
                out.println(JSON.toJSONString(CarJSONResult.ok()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if("/car/carinfo/getPlateNumber".equals(req.getRequestURI())){
            getPlateNumber(out);
        }
        if("/car/carinfo/countCar".equals(req.getRequestURI())){
            countCar(out);
        }
        if("/car/carinfo/ableCar".equals(req.getRequestURI())){
            ableCar(out);
        }
        if("/car/carinfo/selplateNumberByState".equals(req.getRequestURI())){
            selplateNumberByState(out);
        }
        if("/car/carinfo/sendCar".equals(req.getRequestURI())){
            JSONObject json = new JSONObject();
            JSONObject newJson = GetData.getJsonDatas(req, json);
            TDispatch tDispatch = new TDispatch();
            tDispatch.setCarNumber(newJson.getString("carNumber"));
            tDispatch.setDrivername(newJson.getString("drivername"));
            //执行将申请单状态改为4
            approverService.updateAppState4(newJson.getString("drivername"));
            sendCar(tDispatch,out);
        }
    }

    /**
     * 获取分页信息
     * @param limit
     * @param page
     * @param out
     */
    protected void getCarinfoPage(Integer limit, Integer page, PrintWriter out){
        out.println(JSON.toJSONString(carService.carPage(page,limit)));
    }

    /**
     * 修改页面赋值
     * @param id
     * @param out
     */
    protected void getCarById(String id, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(carService.getCarById(id))));
    }

    /**
     * 修改信息
     * @param carinfo
     * @param out
     */
    protected void editCar(Carinfo carinfo, PrintWriter out){
        if(carService.updateCar(carinfo)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
    }

    /**
     * 添加车辆
     * @param carinfo
     * @param out
     */
    protected void addCar(Carinfo carinfo, PrintWriter out){
        if((carService.insCar(carinfo)) > 0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 删除车辆
     * @param id
     * @param out
     */
    protected void delCar(String id, PrintWriter out){
        if(carService.delCar(id) > 0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 车辆搜索
     * @param carinfo
     * @param page
     * @param limit
     * @param out
     */
    protected  void searchCar(Carinfo carinfo ,String regbeg,String regend,String exbeg,String exend ,Integer page, Integer limit,PrintWriter out){
        out.println(JSON.toJSONString(carService.searchCar(carinfo,regbeg,regend,exbeg,exend ,page,limit)));
    }

    /**
     * 检查车牌号是否重复
     * @param plateNumber
     * @param out
     */
    protected void checkNumIsRep(String plateNumber, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(carService.checkCarNum(plateNumber))));
    }

    /**
     * 获取车辆车牌号
     * @param out
     */
    protected void getPlateNumber(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(carService.selAll())));
    }

    /**
     * 获取所需要修的车辆
     * @param page
     * @param limit
     * @param out
     */
    protected void getRepairCarPage(Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(carService.selRepairCarPage(page,limit)));
    }

    /**
     * 修车 即 更改车辆状态
     * @param carinfoId
     * @param out
     */
    protected void repairCar(String carinfoId, PrintWriter out){
        if(carService.repairCar(carinfoId)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return ;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("处理失败")));
    }

    /**
     * 统计总车辆
     * @param out
     */
    protected void countCar(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(carService.countCar())));
    }

    /**
     * 统计有效车辆
     * @param out
     */
    protected void ableCar(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(carService.ableCar())));
    }

    /**
     * 根据状态查询车牌号
     * @param out
     */
    protected void selplateNumberByState(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(carService.selByState("1"))));
    }

    /**
     * 派车
     * @param tDispatch
     * @param out
     */
    protected void sendCar(TDispatch tDispatch, PrintWriter out){
        //根据申请人姓名获取车辆调度id
        List<TDispatch> tDispatches = tDispatchService.selByName(tDispatch.getDrivername());
        tDispatch.setDispatchid(tDispatches.get(0).getDispatchid());
        //获取当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(date);
        tDispatch.setActualtime(time);
        tDispatch.setNamestate("1");
        if(tDispatchService.update(tDispatch)&&carService.updateStateByNumber("2",tDispatch.getCarNumber())){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("派车失败")));
    }
}
