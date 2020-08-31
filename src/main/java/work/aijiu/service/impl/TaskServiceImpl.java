package work.aijiu.service.impl;

import work.aijiu.entity.Task;
import work.aijiu.dao.TaskDao;
import work.aijiu.service.TaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Task)表服务实现类
 *
 * @author makejava
 * @since 2020-08-29 16:38:33
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskDao taskDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Task queryById(Long id) {
        return this.taskDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Task> queryAllByLimit(int offset, int limit) {
        return this.taskDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Task task) {
        return this.taskDao.insert(task);
    }

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Task task) {
        return this.taskDao.update(task);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.taskDao.deleteById(id) > 0;
    }

    @Override
    public List<Task> selAll() {
        return taskDao.queryAll(null);
    }
}