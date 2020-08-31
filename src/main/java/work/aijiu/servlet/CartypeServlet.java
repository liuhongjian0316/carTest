package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.*;
import work.aijiu.entity.Cartype;
import work.aijiu.service.CarService;
import work.aijiu.service.CartypeService;
import work.aijiu.service.ModelService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CartypeServlet extends HttpServlet {

    @Autowired
    private CartypeService cartypeService = SpringContextHolder.getBean(CartypeService.class);
    private CarService carService = SpringContextHolder.getBean(CarService.class);
    private ModelService modelService = SpringContextHolder.getBean(ModelService.class);
    private String oldvehicleClassname;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/cartypeinfo/cartypeName")){
            cartypeinfo(out);
        }
        if(req.getRequestURI().equals("/car/cartypeinfo/addCartype")){
            //接收数据
            JSONObject json = new JSONObject();
            JSONObject cartypeJson = GetData.getJsonDatas(req, json);
            //逐条获取数据
            String vehicleClassname = cartypeJson.getString("vehicleClassname");
            String commentInfo = cartypeJson.getString("commentInfo");
            Cartype cartype = new Cartype();
            cartype.setVehicleClassname(vehicleClassname);
            cartype.setCommentInfo(commentInfo);
            cartype.setState("1");
            //执行添加方法
            addCartype(cartype,out);
        }
        if(req.getRequestURI().equals("/car/cartypeinfo/editCartype")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject cartypeJson = GetData.getJsonDatas(req, json);
            //逐条获取数据
            Integer carinfoId = cartypeJson.getInteger("cartypeId");
            String vehicleClassname = cartypeJson.getString("vehicleClassname");
            String commentInfo = cartypeJson.getString("commentInfo");
            Cartype cartype = new Cartype();
            cartype.setState("1");
            cartype.setVehicleClassname(vehicleClassname);
            cartype.setCommentInfo(commentInfo);
            cartype.setCartypeId(carinfoId);
            editCartype(cartype,out);
        }
        if (req.getRequestURI().equals("/car/cartypeinfo/delAllCartype")){
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
                    cartypeService.delCartypeById(ids[i]);
                }
                out.println(JSON.toJSONString(CarJSONResult.ok()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(req.getRequestURI().equals("/car/cartypeinfo/delCartype")){
            delCartype(req.getParameter("carinfoId"),out);
        }
        if("/car/cartypeinfo/countCartype".equals(req.getRequestURI())){
            countCartype(out);
        }
    }

    /**
     * 统计汽车分类总数
     * @param out
     */
    private void countCartype(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(cartypeService.selAll().size())));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/cartypeinfo/checkTypeIsRep")){
            checkTypeIsRep(req.getParameter("vehicleClassname"),out);
        }
        if(req.getRequestURI().equals("/car/cartypeinfo/cartypePage")){
            //获取数据
            cartypePage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if(req.getRequestURI().equals("/car/cartypeinfo/getCartypeById")){
            getCartypeById(req.getParameter("cartypeId"),out);
        }
        if("/car/cartypeinfo/getCartypeDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(carService.selectDate())));
        }
        if("/car/cartypeinfo/getCarModelDate".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(modelService.selectDate())));
        }
        if("/car/cartypeinfo/oldvehicleClassname".equals(req.getRequestURI())){
            oldvehicleClassname = req.getParameter("vehicleClassname");
        }
        if("/car/cartypeinfo/checkTypeIsRep2".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(cartypeService.checkNameIsRep2(req.getParameter("vehicleClassname"),oldvehicleClassname))));
        }
    }

    /**
     * 汽车分类信息
     * @param out
     */
    protected void cartypeinfo(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(cartypeService.selAll())));
    }

    /**
     * 车辆分类分页
     * @param page
     * @param limit
     * @param out
     */
    protected void cartypePage(Integer page, Integer limit, PrintWriter out){
        out.println(JSON.toJSONString(cartypeService.selPage(page,limit)));
    }

    /**
     * 检查车辆分类名称是否重复
     * @param vehicleClassname
     * @param out
     */
    protected void checkTypeIsRep(String vehicleClassname, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(cartypeService.checkNameIsRep(vehicleClassname))));
    }

    /**
     * 添加汽车分类
     * @param cartype
     * @param out
     */
    protected void addCartype(Cartype cartype, PrintWriter out){
        if(cartypeService.addCartype(cartype) > 0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return ;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 编辑框赋值
     * @param cartypeId
     * @param out
     */
    protected void getCartypeById(String cartypeId, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(cartypeService.selById(cartypeId))));
    }

    /**
     * 修改车辆分类信息
     * @param cartype
     * @param out
     */
    protected void editCartype(Cartype cartype, PrintWriter out){
        if(cartypeService.editCartype(cartype)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
    }

    /**
     * 单条删除汽车分类信息
     * @param carinfoId
     * @param out
     */
    protected void delCartype(String carinfoId, PrintWriter out){
        if(cartypeService.delCartypeById(carinfoId)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

}
