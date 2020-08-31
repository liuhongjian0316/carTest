package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import work.aijiu.common.utils.*;
import work.aijiu.entity.TDepart;
import work.aijiu.service.TDepartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class DepartServlet extends HttpServlet {

    private TDepartService tDepartService = SpringContextHolder.getBean(TDepartService.class);
    private String olddepname = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/depart/departmentPage")){
            departmentPage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if(req.getRequestURI().equals("/car/depart/getDepartmentById")){
            getDepartmentById(req.getParameter("departId"),out);
        }
        if(req.getRequestURI().equals("/car/depart/delDepartmentById")){
            delDepartmentById(req.getParameter("departId"),out);
        }
        if(req.getRequestURI().equals("/car/depart/checkDepIsRep")){
            checkDepIsRep(req.getParameter("departName"),out);
        }
        if("/car/depart/olddepname".equals(req.getRequestURI())){
            olddepname = req.getParameter("olddepname");
        }
        if("/car/depart/checkDepIsRep2".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(tDepartService.checkName2(req.getParameter("departName"),olddepname))));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/depart/pidName")){
            pidName(out);
        }
        if(req.getRequestURI().equals("/car/depart/addDepartment")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject depJson = GetData.getJsonDatas(req, json);
            //javabean
            TDepart tDepart = new TDepart();
            tDepart.setDepartName(depJson.getString("departName"));
            tDepart.setPid(depJson.getInteger("pid"));
            addDepartment(tDepart,out);
        }
        if(req.getRequestURI().equals("/car/depart/editDepartment")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject depJson = GetData.getJsonDatas(req, json);
            //javabean
            TDepart tDepart = new TDepart();
            tDepart.setDepartName(depJson.getString("departName"));
            tDepart.setPid(depJson.getInteger("pid"));
            tDepart.setDepartId(depJson.getInteger("departId"));
            editDepartment(tDepart,out);
        }
        if(req.getRequestURI().equals("/car/depart/delAllDepartment")){
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
                    tDepartService.deleteById(new Integer(ids[i]));
                }
                out.println(JSON.toJSONString(CarJSONResult.ok()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取部门信息填充下拉框
     * @param out
     */
    protected void pidName(PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(tDepartService.selAll())));
    }

    /**
     * 部门分页
     * @param page
     * @param limit
     * @param out
     */
    protected void departmentPage(Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tDepartService.departPage(page,limit)));
    }

    /**
     * 添加部门
     * @param tDepart
     * @param out
     */
    protected void addDepartment(TDepart tDepart, PrintWriter out){
        if(tDepartService.insert(tDepart)>0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 修改模态框赋值
     * @param departId
     * @param out
     */
    protected void getDepartmentById(String departId, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(tDepartService.queryById(new Integer(departId)))));
    }

    /**
     * 修改部门信息
     * @param tDepart
     * @param out
     */
    protected void editDepartment(TDepart tDepart, PrintWriter out){
        if(tDepartService.update(tDepart) > 0){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
    }

    /**
     * 单条删除部门信息
     * @param departId
     * @param out
     */
    protected void delDepartmentById(String departId, PrintWriter out){
        if(tDepartService.deleteById(new Integer(departId))){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 检查部门是否重复
     * @param departName
     * @param out
     */
    protected void checkDepIsRep(String departName, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(tDepartService.checkName(departName))));
    }
}
