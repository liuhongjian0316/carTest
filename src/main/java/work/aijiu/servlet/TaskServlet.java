package work.aijiu.servlet;

import lombok.SneakyThrows;
import work.aijiu.job.MyScheduler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 定时任务
 */
public class TaskServlet extends HttpServlet {

    @SneakyThrows
    @Override
    public void init() throws ServletException {
        MyScheduler.run();
    }
}
