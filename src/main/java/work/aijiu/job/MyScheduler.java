package work.aijiu.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.ApproverDao;
import work.aijiu.entity.Task;
import work.aijiu.service.TaskService;
import work.aijiu.service.UserService;

import java.util.List;

public class MyScheduler {

    private static TaskService taskService = SpringContextHolder.getBean(TaskService.class);
    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }
    //任务名称 任务分组 cron表达式
    // 执行任务
    public static void run() throws SchedulerException{
        Task task = new Task();
        //1 为 开始的
        task.setJobStatus("1");
        List<Task> tasks = taskService.selAll();
        tasks.forEach(
                i-> {
                    try {
                        JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(i.getBeanClass())).withIdentity(i.getJobName(), i.getJobGroup()).build();

                        //cron 表达式 秒 分 小时 日期  月份 星期 年  eg: "*/5 * * * * ?  每个用空格隔开
                        CronTrigger cronTrigger = (CronTrigger)TriggerBuilder
                                .newTrigger()
                                .withIdentity(i.getJobName(), i.getJobGroup())
                                .withSchedule(
                                        CronScheduleBuilder.cronSchedule(i.getCronExpression().toString())
                                ).build();
                        Scheduler scheduler = getScheduler();
                        //将任务及其触发器放入调度器
                        scheduler.scheduleJob(jobDetail, cronTrigger);
                        //调度器开始调度任务
                        // 启动
                        if (!scheduler.isShutdown()) {
                            scheduler.start();
                        }
                    } catch (ClassNotFoundException | SchedulerException e) {
                        e.printStackTrace();
                    }
                }
        );
    }

    public static void shutdown() throws SchedulerException{
        MyScheduler.getScheduler().shutdown();
    }

    public static void main(String[] args) throws SchedulerException {
        MyScheduler.run();
    }
}
