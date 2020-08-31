package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.SneakyThrows;
import work.aijiu.common.utils.*;
import work.aijiu.entity.Task;
import work.aijiu.job.MyScheduler;
import work.aijiu.service.TaskService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 定时任务
 */
public class TaskServlet2 extends HttpServlet {

    private TaskService taskService = SpringContextHolder.getBean(TaskService.class);


    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/task/taskpage".equals(req.getRequestURI())){
            Integer page = new Integer(req.getParameter("page"));
            Integer limit = new Integer(req.getParameter("limit"));
            page = (page - 1) *limit;
            out.println(JSON.toJSONString(new LayuiPageResult(taskService.selAll().size(),taskService.queryAllByLimit(page,limit))));
        }

        if("/car/task/del".equals(req.getRequestURI())){
            if( taskService.deleteById((new Long(req.getParameter("id"))))){
                out.println(JSON.toJSONString(CarJSONResult.ok("删除成功")));
                MyScheduler.shutdown();
                MyScheduler.run();
            } else {
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("删除失败")));
                MyScheduler.shutdown();
                MyScheduler.run();
            }
        }
        if("/car/task/getone".equals(req.getRequestURI())){
            out.println(JSON.toJSONString(CarJSONResult.ok(taskService.queryById(new Long(req.getParameter("id"))))));
        }
        if("/car/task/updatestate".equals(req.getRequestURI())){
            Task task = new Task();
            task.setId(new Long(req.getParameter("id")));
            task.setJobStatus(req.getParameter("status"));
            if(taskService.update(task)>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("操作成功")));
                MyScheduler.shutdown();
                MyScheduler.run();
            }else {
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("操作失败")));
                MyScheduler.shutdown();
                MyScheduler.run();
            }
        }

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        /**
         * 添加任务
         */
        if("/car/task/add".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject taskJson = GetData.getJsonDatas(req, json);
            String jobName = taskJson.getString("jobName");
            String description = taskJson.getString("description");
            String cronExpression = taskJson.getString("cronExpression");
            String beanClass = taskJson.getString("beanClass");
            String jobStatus = taskJson.getString("jobStatus");
            String jobGroup = taskJson.getString("jobGroup");
            String createUser = taskJson.getString("createUser");
            Date createTime = taskJson.getDate("createTime");
            String updateUser = taskJson.getString("updateUser");
            Date updateTime = taskJson.getDate("updateTime");
            Task task = new Task(jobName,description,cronExpression,beanClass,jobStatus,jobGroup,createUser,createTime,updateUser,updateTime);

            if (taskService.insert(task) > 0) {
                out.println(JSON.toJSONString(CarJSONResult.ok("添加成功")));
                MyScheduler.shutdown();
                MyScheduler.run();
            } else {
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("添加失败")));
                MyScheduler.shutdown();
                MyScheduler.run();
            }
        }
        /**
         * 更新任务
         */
        if("/car/task/update".equals(req.getRequestURI())){
            //获取数据
            JSONObject json = new JSONObject();
            JSONObject taskJson = GetData.getJsonDatas(req, json);
            Long id = taskJson.getLong("id");
            String jobName = taskJson.getString("jobName");
            String description = taskJson.getString("description");
            String cronExpression = taskJson.getString("cronExpression");
            String beanClass = taskJson.getString("beanClass");
            String jobStatus = taskJson.getString("jobStatus");
            String jobGroup = taskJson.getString("jobGroup");
            String createUser = taskJson.getString("createUser");
            Date createTime = taskJson.getDate("createTime");
            String updateUser = taskJson.getString("updateUser");
            Date updateTime = taskJson.getDate("updateTime");
            Task task = new Task(id,jobName,description,cronExpression,beanClass,jobStatus,jobGroup,createUser,createTime,updateUser,updateTime);
            if(taskService.update(task)>0){
                out.println(JSON.toJSONString(CarJSONResult.ok("修改成功")));
                MyScheduler.shutdown();
                MyScheduler.run();
            }else {
                out.println(JSON.toJSONString(CarJSONResult.errorMsg("修改失败")));
                MyScheduler.shutdown();
                MyScheduler.run();
            }
        }

    }
}
