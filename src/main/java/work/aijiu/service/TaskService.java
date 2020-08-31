package work.aijiu.service;

import work.aijiu.entity.Task;
import java.util.List;

/**
 * (Task)表服务接口
 *
 * @author makejava
 * @since 2020-08-29 16:38:32
 */
public interface TaskService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Task queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Task> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    int insert(Task task);

    /**
     * 修改数据
     *
     * @param task 实例对象
     * @return 实例对象
     */
    int update(Task task);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 查询所有
     * @return
     */
    List<Task> selAll();

}