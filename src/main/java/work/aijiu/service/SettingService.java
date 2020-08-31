package work.aijiu.service;

import work.aijiu.entity.Setting;
import java.util.List;

/**
 * (Setting)表服务接口
 *
 * @author makejava
 * @since 2020-08-30 09:39:49
 */
public interface SettingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Setting queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Setting> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    Setting insert(Setting setting);

    /**
     * 修改数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    int update(Setting setting);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}