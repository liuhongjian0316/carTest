package work.aijiu.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;
import work.aijiu.job.MyScheduler;
import work.aijiu.service.UserService;
import work.aijiu.service.impl.UserServiceImpl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

/**
 * CGLIB实现动态代理不需要接口
 */
public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //enhancer.setSuperclass(UserService2.class);
        enhancer.setCallback(new TimeMethodInterceptor());
        enhancer.setSuperclass(UserServiceImpl.class);
        UserService userService = (UserServiceImpl)enhancer.create();
        //UserService2 userService = (UserService2)enhancer.create();
        userService.login("admin","admin");
    }
}

class TimeMethodInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        //调用被代理的类的方法
        result = methodProxy.invokeSuper(o, objects);
        System.out.println(objects.length);
        after(objects[0].toString());
        return result;
    }

    public void after(String username) throws SchedulerException {
        System.out.println("收集日志");
        System.out.println(username+"登陆了");
    }
}

class UserService2 {
    public void login(String name,String pwd) {
        if("admin".equals(name) && "admin".equals(pwd)){
            System.out.println("登录成功");
            System.out.println("用户名："+name+"-----密码："+pwd);
        }
        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



