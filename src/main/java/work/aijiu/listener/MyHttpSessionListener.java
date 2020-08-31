package work.aijiu.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName MyHttpSessionListener
 * @Description session 监听器
 * @Author Administrator
 * @Date 2020/4/11 17:49
 * @Version 1.0
 **/
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 1;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        online++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
        online--;
    }
}
