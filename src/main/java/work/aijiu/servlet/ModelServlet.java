package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.CarJSONResult;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.service.ModelService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ModelServlet extends HttpServlet {

    @Autowired
    private ModelService modelService = SpringContextHolder.getBean(ModelService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        resp.setHeader("content-type","text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if(req.getRequestURI().equals("/car/modelinfo/modelName")){
            getModelInfo(out);
        }
        if("/car/modelinfo/countModel".equals(req.getRequestURI())){
            countModel(out);
        }

    }

    /**
     * 统计品牌总数
     * @param out
     */
    private void countModel(PrintWriter out) {
        out.println(JSON.toJSONString(CarJSONResult.ok(modelService.selAll().size())));
    }

    /**
     * 获取品牌信息
     * @param out
     */
    protected void getModelInfo(PrintWriter out){
        out.print(JSON.toJSONString(CarJSONResult.ok(modelService.selAll())));
    }
}
