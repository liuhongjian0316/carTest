package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import work.aijiu.common.utils.*;
import work.aijiu.entity.TCardlog;
import work.aijiu.service.TCardlogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CardlogServlet extends HttpServlet {

    private TCardlogService tCardlogService = SpringContextHolder.getBean(TCardlogService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/cardlog/cardlogPage".equals(req.getRequestURI())){
            cardlogPage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/cardlog/getCardlogById".equals(req.getRequestURI())){
            getCardlogById(new Integer(req.getParameter("cardlogid")),out);
        }
        if("/car/cardlog/delCardlog".equals(req.getRequestURI())){
            delCardlog(new Integer(req.getParameter("cardlogid")),out);
        }
        if("/car/cardlog/searchCardlog".equals(req.getRequestURI())){
            searchCardlog(req.getParameter("carnumber"),req.getParameter("addtime"),
                    new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/cardlog/addCardlog".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject cardlogJson = GetData.getJsonDatas(req, json);
            TCardlog cardlog = new TCardlog();
            cardlog.setAddnumber(cardlogJson.getDouble("addnumber"));
            cardlog.setAddtime(cardlogJson.getString("addtime"));
            cardlog.setCarnumber(cardlogJson.getString("carnumber"));
            cardlog.setConsume(cardlogJson.getDouble("consume"));
            cardlog.setCost(cardlogJson.getDouble("cost"));
            cardlog.setResidue(cardlogJson.getDouble("residue"));
            addCardlog(cardlog,out);
        }
        if("/car/cardlog/editCardlog".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject cardlogJson = GetData.getJsonDatas(req, json);
            TCardlog cardlog = new TCardlog();
            cardlog.setAddnumber(cardlogJson.getDouble("addnumber"));
            cardlog.setAddtime(cardlogJson.getString("addtime"));
            cardlog.setCarnumber(cardlogJson.getString("carnumber"));
            cardlog.setConsume(cardlogJson.getDouble("consume"));
            cardlog.setCost(cardlogJson.getDouble("cost"));
            cardlog.setResidue(cardlogJson.getDouble("residue"));
            cardlog.setCardlogid(cardlogJson.getInteger("cardlogid"));
            editCardlog(cardlog,out);
        }
        if("/car/cardlog/delAllCardlog".equals(req.getRequestURI())){
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
                    tCardlogService.deleteById(new Integer(ids[i]));
                }
                out.println(JSON.toJSONString(CarJSONResult.ok()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if("/car/cardlog/getOlicardCost".equals(req.getRequestURI())){
            getOlicardCost(out);
        }
    }

    /**
     * 获取油卡消费
     * @param out
     */
    private void getOlicardCost(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(tCardlogService.getCardCost())));
    }

    /**
     * 加油日志分页
     * @param page
     * @param limit
     * @param out
     */
    protected void cardlogPage(Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tCardlogService.cardlogPage(page,limit)));
    }

    /**
     * 添加油卡日志
     * @param tCardlog
     * @param out
     */
    protected void addCardlog(TCardlog tCardlog, PrintWriter out){
        if(tCardlogService.insert(tCardlog)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return ;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 编辑模态框赋值
     * @param cardlogid
     * @param out
     */
    protected void getCardlogById(Integer cardlogid,PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(tCardlogService.queryById(cardlogid))));
    }

    /**
     * 修改加油日志
     * @param tCardlog
     * @param out
     */
    protected void editCardlog(TCardlog tCardlog,PrintWriter out){
        if(tCardlogService.update(tCardlog)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
    }

    /**
     * 删除日志
     * @param cardlogid
     * @param out
     */
    protected void delCardlog(Integer cardlogid, PrintWriter out){
        if(tCardlogService.deleteById(cardlogid)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 搜素日志
     * @param carnumber
     * @param addtime
     * @param page
     * @param limit
     * @param out
     */
    protected void searchCardlog(String carnumber, String addtime,Integer page, Integer limit,PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tCardlogService.searchPage(carnumber,addtime ,page,limit)));
    }
}
