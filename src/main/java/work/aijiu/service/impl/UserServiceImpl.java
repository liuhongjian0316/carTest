package work.aijiu.service.impl;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import work.aijiu.common.utils.IpUtils;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.UserDao;
import work.aijiu.entity.Log;
import work.aijiu.entity.Setting;
import work.aijiu.entity.User;
import work.aijiu.redis.RedisService;
import work.aijiu.service.SettingService;
import work.aijiu.service.UserService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/28 17:17
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {


    private UserDao userDao = SpringContextHolder.getBean(UserDao.class);

    private RedisService redisService = SpringContextHolder.getBean(RedisService.class);

    private SettingService settingService = SpringContextHolder.getBean(SettingService.class);



    @Override
    public List<User> login(String accunt,String pwd) {
        //从redis中获取用户
        List<User> userList = (List<User>) redisService.get(accunt);
        //双重检测锁
        if(null == userList){
            synchronized (this){
                //查询缓存
                userList = (List<User>) redisService.get(accunt);
                if(userList ==null){
                    //查询数据库
                    userList = userDao.selUserByAccoundAndPwd(accunt,pwd);
                    //数据库查出的数据放入缓存
                    if(userList.size()>0){
                        redisService.set(userList.get(0).getUserAccount().toString(),userList);
                    }
                }else{
                    //System.out.println("查询缓存。。。。。。。。。。。");
                }
            }
        }else{
            //System.out.println("查询缓存。。。。。。。。。");
        }
        return userList;
    }

    @Override
    public LayuiPageResult userPage(Integer page, Integer limit) {
        return new LayuiPageResult(userDao.selAll().size(),userDao.userPage(page,limit));
    }

    @Override
    public boolean insertUser(User user) {
        return userDao.insertUser(user)>0;
    }

    @Override
    public boolean checkAccount(String userAccount) {
        return userDao.checkAccount(userAccount)>0;
    }

    @Override
    public boolean checkAccount2(String userAccount, String olduserAccount) {
        List<User> users = userDao.selByAccount(olduserAccount);
        return userDao.checkAccount2(userAccount,users.get(0).getUserinfoid())>0;
    }

    @Override
    public boolean checkName(String userName) {
        return userDao.checkName(userName)>0;
    }

    @Override
    public boolean checkTel(String tel) {
        return userDao.checkTel(tel)>0;
    }

    @Override
    public List<User> selById(String userinfoid) {
        return userDao.selById(userinfoid);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user)>0;
    }

    @Override
    public boolean delete(String userinfoid) {
        return userDao.delete(userinfoid)>0;
    }

    @Override
    public LayuiPageResult search(String userAccount, String tel, String departName, Integer page, Integer limit) {

        return new LayuiPageResult(userDao.selAll().size(),userDao.search(userAccount,tel,departName,page,limit));
    }

    @Override
    public boolean addPermission(String roleid, String userinfoid) {
        return userDao.updateRole(roleid,userinfoid)>0;
    }

    @Override
    public int countUser() {
        return userDao.countUser();
    }

    @Override
    public int countOrdUser() {
        return userDao.countOrdUser();
    }

    @Override
    public int countAdmin() {
        return userDao.countAdmin();
    }

    @Override
    public List<Map<String, Object>> selectRoleDate() {
        return userDao.selectRoleDate();
    }

    @Override
    public List<Map<String, Object>> selectSexDate() {
        return userDao.selectSexDate();
    }

    @Override
    public List<Map<String, Object>> selectDeaprtmentDate() {
        return userDao.selectDeaprtmentDate();
    }

    @Override
    public List<User> selAll() {
        return userDao.selAll();
    }

    @Override
    public void savelog(String username,String passwod) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new TimeMethodInterceptor());
        enhancer.setSuperclass(UserServiceImpl.class);
        UserService userService = (UserServiceImpl)enhancer.create();
        userService.login(username,passwod);
    }

    @Override
    public List<Log> getloginLog(){
        Map<Object, Object> loginLog = redisService.hgetall("loginLog");
        List<Log> list = new ArrayList<>();
        loginLog.forEach((k,v)-> list.add(new Log(k,v)));
        return list;
    }
    class TimeMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            Object result = null;
            //调用被代理的类的方法
            result = methodProxy.invokeSuper(o, objects);
            after(objects[0].toString());
            return result;
        }

        public void after(String username) throws SchedulerException {
            //获取当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            FileWriter fw = null;
            //InetAddress ip4 = null;
            String publicip = null;
            try {
                Setting setting = settingService.queryById(1);
                //如果文件存在，则追加内容；如果文件不存在，则创建文件
                File f=new File(setting.getSaveurl());
                fw = new FileWriter(f, true);
                //ip4 = Inet4Address.getLocalHost();
                publicip = IpUtils.publicip();
            } catch (IOException e) {
                e.printStackTrace();
            }
            PrintWriter pw = new PrintWriter(fw);
            String res = sdf.format(new Date())+":账号"+username+"在"+publicip+"登陆了"+"("+System.getProperty("os.name")+")";
            redisService.hset("loginLog", UUID.randomUUID().toString(),res);
            pw.println(res);
            pw.flush();
            try {
                fw.flush();
                pw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
