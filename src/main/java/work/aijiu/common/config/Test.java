package work.aijiu.common.config;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import work.aijiu.common.utils.EllConst;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.UserDao;
import work.aijiu.elasticsearch.elUtils;
import work.aijiu.entity.User;
import work.aijiu.service.UserService;
import work.aijiu.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {

        String node = "121.36.156.106";
        int port = 9200;
        String scheme = "http";
        //获取连接
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(EllConst.node, EllConst.port, EllConst.scheme)));
        CreateIndexResponse xiazheng_index = elUtils.createIndex("xiazheng_index", client);
        System.out.println(xiazheng_index);
        client.close();

//        SearchRequest searchRequest = new SearchRequest();
//        //创建索引 对应put
//        //CreateIndexRequest request = new CreateIndexRequest("aijiu_index");
//        try {
//            //获取所有信息
//            List<User> users = userService.selAll();
//            IndexRequest indexRequest = new IndexRequest("aijiu_index");
//            for (int i = 0; i < users.size(); i++) {
//                //设置规则
//                indexRequest.id(String.valueOf(i)); //id
//                indexRequest.timeout(TimeValue.timeValueSeconds(1)); //超时1秒
//                //将数据放入请求
//                IndexRequest source = indexRequest.source(JSON.toJSONString(users.get(i)), XContentType.JSON);
//                //客户端发送请求
//                IndexResponse index = client.index(source, RequestOptions.DEFAULT);
//                //获取结果
//                System.out.println(index.toString()); //返回IndexResponse的JSON
//            }

//            //查询结果
//            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//            SearchHits hits = searchResponse.getHits();
//            SearchHit[] searchHits = hits.getHits();
//            for(SearchHit hit : searchHits) {
//                System.out.println(hit.getSourceAsString());
//            }
//            IndexResponse indexResponse = new IndexResponse();
            //执行请求
//            CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//            System.out.println(createIndexResponse);
            //判断索引是否存在
//            GetIndexRequest getIndexRequest = new GetIndexRequest("aijiu_index");
//            boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
//            System.out.println(exists);
            //删除索引
//            DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("aijiu_index");
//            AcknowledgedResponse delete = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//            System.out.println(delete.isAcknowledged()); //获取删除状态
//            //文档添加
//            //创建对象
//            User user = new User();
//            user.setUserName("张三");
//            user.setSex("男");
//            //创建请求
//            IndexRequest indexRequest = new IndexRequest("aijiu_index");
//            //设置规则
//            indexRequest.id("1"); //id
//            indexRequest.timeout(TimeValue.timeValueSeconds(1)); //超时1秒
//            //将数据放入请求
//            IndexRequest source = indexRequest.source(JSON.toJSONString(user), XContentType.JSON);
//            //客户端发送请求
//            IndexResponse index = client.index(source, RequestOptions.DEFAULT);
//            //获取结果
//            System.out.println(index.toString()); //返回IndexResponse的JSON
//            //获取文档
//            //判断文档是否存在
//            GetRequest getRequest = new GetRequest("aijiu_index","1");
            //不获取返回的_source 的上下文 效率更高
//            getRequest.fetchSourceContext(new FetchSourceContext(false));
//            getRequest.storedFields("_none_");
//            boolean exists = client.exists(getRequest, RequestOptions.DEFAULT);
//            System.out.println(exists);
            //获取文档信息
//            GetRequest getRequest1 = new GetRequest("aijiu_index","1");
//
//            GetResponse documentFields = client.get(getRequest1, RequestOptions.DEFAULT);
//            System.out.println(documentFields.getSourceAsString());//打印文档内容

//            //更新文档
//            UpdateRequest updateRequest = new UpdateRequest("aijiu_index","1");
//            updateRequest.timeout(TimeValue.timeValueSeconds(1));
//
//            User user = new User();
//            user.setUserName("李四");
//            user.setSex("男");
//            updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);
//            UpdateResponse update = client.update(updateRequest, RequestOptions.DEFAULT);
//            System.out.println(update.status());
//
//
//
//            client.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } finally {
//            if(client != null){
//                try {
//                    client.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }
}
