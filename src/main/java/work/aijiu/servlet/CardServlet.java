package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import work.aijiu.common.utils.*;
import work.aijiu.entity.TCard;
import work.aijiu.service.TCardService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class CardServlet extends HttpServlet {

    private TCardService tCardService = SpringContextHolder.getBean(TCardService.class);
    private String oldcardnumber = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/card/cardpage")){
            cardpage(new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if(req.getRequestURI().equals("/car/card/getCardById")){
            getCardById(new Integer(req.getParameter("cardid")),out);
        }
        if(req.getRequestURI().equals("/car/card/delCard")){
            delCard(new Integer(req.getParameter("cardid")),out);
        }
        if(req.getRequestURI().equals("/car/card/checkCardNumIsRep")){
            checkCardNumIsRep(req.getParameter("cardnumber"),out);
        }
        if(req.getRequestURI().equals("/car/card/searchCard")){
            searchCard(req.getParameter("cardnumber"),new Integer(req.getParameter("page")),new Integer(req.getParameter("limit")),out);
        }
        if("/car/card/oldcardnumber".equals(req.getRequestURI())){
            oldcardnumber = req.getParameter("oldcardnumber");
        }
        if("/car/card/checkCardNumIsRep2".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(tCardService.checkIsRep2(req.getParameter("cardnumber"),oldcardnumber))));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/card/addCard")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject cardJson = GetData.getJsonDatas(req, json);
            TCard card = new TCard();
            card.setBalance(cardJson.getDouble("balance"));
            card.setCardnumber(cardJson.getString("cardnumber"));
            card.setCarnumber(cardJson.getString("carnumber"));
            addCard(card,out);
        }
        if(req.getRequestURI().equals("/car/card/editCard")){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject cardJson = GetData.getJsonDatas(req, json);
            TCard card = new TCard();
            card.setBalance(cardJson.getDouble("balance"));
            card.setCardnumber(cardJson.getString("cardnumber"));
            card.setCarnumber(cardJson.getString("carnumber"));
            card.setCardid(cardJson.getInteger("cardid"));
            editCard(card,out);
        }
        if(req.getRequestURI().equals("/car/card/delAllCard")){
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
                    tCardService.deleteById(new Integer(ids[i]));
                }
                out.println(JSON.toJSONString(CarJSONResult.ok()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if("/car/card/getOlicardNum".equals(req.getRequestURI())){
            getOlicardNum(out);
        }
    }


    /**
     * 统计油卡数
     * @param out
     */
    private void getOlicardNum(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(tCardService.selAll().size())));
    }

    /**
     * 油卡分页
     * @param page
     * @param limit
     * @param out
     */
    protected void cardpage(Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tCardService.cardpage(page,limit)));
    }

    /**
     * 添加油卡
     * @param tCard
     * @param out
     */
    protected void addCard(TCard tCard, PrintWriter out){
        if(tCardService.insert(tCard)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
    }

    /**
     * 编辑模态框赋值
     * @param cardid
     * @param out
     */
    protected void getCardById(Integer cardid, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(tCardService.queryById(cardid))));
    }

    /**
     * 修改油卡数据
     * @param card
     * @param out
     */
    protected void editCard(TCard card, PrintWriter out){
        if(tCardService.update(card)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("更新数据失败")));
    }

    /**
     * 单个删除油卡
     * @param cardid
     * @param out
     */
    protected void delCard(Integer cardid, PrintWriter out){
        if(tCardService.deleteById(cardid)){
            out.println(JSON.toJSONString(CarJSONResult.ok()));
            return;
        }
        out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
    }

    /**
     * 检查卡号是否重复
     * @param cardnumber
     * @param out
     */
    protected void checkCardNumIsRep(String cardnumber, PrintWriter out){
        out.println(JSON.toJSONString(CarJSONResult.ok(tCardService.checkIsRep(cardnumber))));
    }

    /**
     * 搜索油卡
     * @param cardnumber
     * @param page
     * @param limit
     * @param out
     */
    protected void searchCard(String cardnumber, Integer page, Integer limit, PrintWriter out){
        page = (page-1)*limit;
        out.println(JSON.toJSONString(tCardService.searchPage(cardnumber,page,limit)));

    }
}
