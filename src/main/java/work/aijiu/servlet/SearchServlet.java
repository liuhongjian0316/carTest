package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import work.aijiu.common.utils.EllConst;
import work.aijiu.common.utils.SetHeader;
import work.aijiu.elasticsearch.elUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/search/searchparm".equals(req.getRequestURI())){
            //获取连接
            RestHighLevelClient client = elUtils.getClient();
            //搜索
            GetRequest getRequest1 = new GetRequest("aijiu_index");
            GetResponse documentFields = client.get(getRequest1, RequestOptions.DEFAULT);
            System.out.println(documentFields.getSourceAsString());//打印文档内容
            Map<String, Object> source = documentFields.getSource();
            JSONObject jsonObject = new JSONObject(source);
            //关闭
            elUtils.close(client);
            out.println(jsonObject);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
    }
}
