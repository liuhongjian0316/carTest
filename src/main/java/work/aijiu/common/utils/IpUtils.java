package work.aijiu.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpUtils {
    public static String publicip() {
        URL url = null;
        URLConnection urlconn = null;
        BufferedReader br = null;
        try {
            url = new URL("https://202020.ip138.com");//爬取的网站是百度搜索ip时排名第一的那个
            urlconn = url.openConnection();
            br = new BufferedReader(new InputStreamReader(
                     urlconn.getInputStream(),"UTF-8"));
            String buf = null;
            String get= null;
            while ((buf = br.readLine()) != null) {
                get+=buf;
            }
            String res = get;
            int where,end;
            for(where=0;where<res.length()&&res.charAt(where)!='[';where++);
                for(end=where;end<get.length()&&get.charAt(end)!=']';end++);
                    res=res.substring(where+1,end);
            for(where=0;where<res.length()&&res.charAt(where)!='>';where++);
                for(end=where;end<res.length()&&res.charAt(end)!='<';end++);
                    res=res.substring(where+1,end);
            for(where=0;where<get.length()&&get.charAt(where)!=']';where++);
                for(end=where;end<get.length()&&get.charAt(end)!='<';end++);
                    get=get.substring(where+1,end);
                return res+"("+get+")";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        publicip();
    }
}
