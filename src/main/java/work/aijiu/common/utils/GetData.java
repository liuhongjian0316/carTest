package work.aijiu.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public  class GetData {

    public static JSONObject getJsonDatas(HttpServletRequest req,JSONObject json) throws UnsupportedEncodingException {
        BufferedReader br;
        String jsonStr = null;
        try {
            br = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            json= JSONObject.parseObject(sb.toString());
            jsonStr = JSONObject.toJSONString(json);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static JSONArray getListDatas(HttpServletRequest req,JSONArray list,JSONObject json){
        BufferedReader br;
        String jsonStr = null;
        String jsonStr2 = null;
        try {
            br = new BufferedReader(new InputStreamReader(req.getInputStream(),"UTF-8"));
            String line = null;
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
            //json= JSONObject.parseObject(sb.toString());
            jsonStr = JSONObject.toJSONString(json);
            list = json.getJSONArray("list");
            jsonStr2 = JSONArray.toJSONString(list);


        } catch (IOException e) {
            e.printStackTrace();
        }
       return list;

    }

    public static void main(String[] args) {

    }
}
