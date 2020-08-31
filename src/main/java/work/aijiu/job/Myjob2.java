package work.aijiu.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.redis.RedisService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Myjob2 implements Job {

    private RedisService redisService = SpringContextHolder.getBean(RedisService.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        redisService.hgetall("loginLog").forEach(
                (k,v)-> redisService.del("loginLog")
        );
    }
}

