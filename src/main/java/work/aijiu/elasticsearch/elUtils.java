package work.aijiu.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import work.aijiu.common.utils.EllConst;

import java.io.IOException;

/**
 * el工具类
 */
public class elUtils{

    /**
     * 获取连接
     * @return
     */
    public static RestHighLevelClient getClient() {
        return new RestHighLevelClient(
                RestClient.builder(new HttpHost(EllConst.node, EllConst.port, EllConst.scheme)));
    }

    /**
     * 关闭连接
     * @param client
     */
    public static void close(RestHighLevelClient client){
        try {
            client.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(client != null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建索引
     * @param indexname
     * @return
     */
    public static CreateIndexResponse createIndex(String indexname, RestHighLevelClient client) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexname);
        IndexResponse indexResponse = new IndexResponse();
        return client.indices().create(request, RequestOptions.DEFAULT);
    }




}
