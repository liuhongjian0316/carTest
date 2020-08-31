package work.aijiu.dao;

import work.aijiu.entity.Setting;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Setting)表数据库访问层
 *
 * @author makejava
 * @since 2020-08-30 09:39:49
 */
public interface SettingDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Setting queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Setting> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param setting 实例对象
     * @return 对象列表
     */
    List<Setting> queryAll(Setting setting);

    /**
     * 新增数据
     *
     * @param setting 实例对象
     * @return 影响行数
     */
    int insert(Setting setting);

    /**
     * 修改数据
     *
     * @param setting 实例对象
     * @return 影响行数
     */
    int update(Setting setting);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}