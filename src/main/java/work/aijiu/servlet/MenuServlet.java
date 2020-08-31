package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.VO.MenuVo;
import work.aijiu.VO.TreeVo;
import work.aijiu.common.utils.*;
import work.aijiu.entity.Menu;
import work.aijiu.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MenuServlet extends HttpServlet {

    @Autowired
    private MenuService menuService = SpringContextHolder.getBean(MenuService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        resp.setHeader("content-type","text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        if("/car/menu/showMenu".equals(req.getRequestURI())){
            //接收数据
            String roleid = req.getParameter("roleid");
            //通过response对象获得输出流。返回数据用
            //查询菜单
            List<Menu> menus = menuService.showMenu(roleid);
            //返回给界面
            out.println(JSON.toJSONString(new CarJSONResult(menus)));
        }
        if("/car/menu/showMenuList".equals(req.getRequestURI())){
            System.out.println(new TreeData(0,"",menuService.getmenuList()));
            out.println(JSON.toJSONString(new TreeData(0,"",menuService.getmenuList())));
        }
        if("/car/menu/getById".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(menuService.getByID(req.getParameter("id")))));
        }
        if("/car/menu/delete".equals(req.getRequestURI())){
            if(menuService.delete(req.getParameter("id"))>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("删除成功")));
            }else{
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
            }
        }
        if("/car/menu/getroleList".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(new TreeData(0,"",menuService.getroleList(req.getParameter("roleid")))));
        }
        if("/car/menu/getByRm".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(menuService.getRmByRm(req.getParameter("roleid"),req.getParameter("menuid")))));
        }
        if("/car/menu/rmdel".equals(req.getRequestURI())){
            if(menuService.rmdel(req.getParameter("roleid"),req.getParameter("menuid"))>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("删除成功")));
                return;
            }
            out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/menu/getmenu".equals(req.getRequestURI())){
            List<MenuVo> data = menuService.getmenu(req.getParameter("userinfoid"));
            List<List<MenuVo>> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                if ("0".equals(data.get(i).getPid())){
                    list.add(data);
                }
            }
            out.println(JSON.toJSONString(CarJSONResult.ok(data)));
        }
        if("/car/menu/add".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject carJson = GetData.getJsonDatas(req, json);
            String mid = carJson.getString("mid");
            String name = carJson.getString("name");
            String pid = carJson.getString("pid");
            String url = carJson.getString("url");
            Menu menu = new Menu();
            menu.setMid(mid);
            menu.setName(name);
            menu.setPid(pid);
            menu.setUrl(url);
            int insert = menuService.insert(menu);
            if(insert>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("添加成功")));
            }
            else{
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
            }
        }
        if("/car/menu/edit".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject carJson = GetData.getJsonDatas(req, json);
            String mid = carJson.getString("mid");
            String name = carJson.getString("name");
            String pid = carJson.getString("pid");
            String url = carJson.getString("url");
            String id = carJson.getString("id");
            Menu menu = new Menu();
            menu.setMid(mid);
            menu.setName(name);
            menu.setUrl(url);
            menu.setId(id);
            menu.setPid(pid);
            menu.setRloeid(null);
            if(menuService.update(menu)>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("修改成功")));
            }else{
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
            }
        }

        if("/car/menu/rmadd".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject carJson = GetData.getJsonDatas(req, json);
            String roleid = carJson.getString("roleid");
            String menuid = carJson.getString("menuid");
            if(menuService.rmadd(roleid,menuid)>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("添加成功")));
                return;
            }
            out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
        }
        if("/car/menu/rmedit".equals(req.getRequestURI())){
            //获取数据
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject carJson = GetData.getJsonDatas(req, json);
            String roleid = carJson.getString("roleid");
            String menuid = carJson.getString("menuid");
            String roleid2 = carJson.getString("roleid2");
            String menuid2 = carJson.getString("menuid");
            if(menuService.rmupdate(roleid,menuid,roleid2,menuid2)>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("修改成功")));
                return;
            }
            out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
        }
    }
}
