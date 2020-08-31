package work.aijiu.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import work.aijiu.common.utils.IpUtils;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.entity.Setting;
import work.aijiu.redis.RedisService;
import work.aijiu.service.SettingService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Myjob implements Job {

    private RedisService redisService = SpringContextHolder.getBean(RedisService.class);
    private SettingService settingService = SpringContextHolder.getBean(SettingService.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Map<Object, Object> loginLog = redisService.hgetall("loginLog");
        loginLog.forEach(
                (k,v)->{
                    saveFile(v.toString());
                }
        );
    }

    public void saveFile(String msg){
        FileWriter fw = null;
        String publicip = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            Setting setting = settingService.queryById(1);
            File f=new File(setting.getServeurl());
            fw = new FileWriter(f, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        pw.println(msg);
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

