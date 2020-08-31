package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.TDepart;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TDepart)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-05 19:24:40
 */
@Repository
public interface TDepartDao {

    /**
     * 通过ID查询单条数据
     *
     * @param departId 主键
     * @return 实例对象
     */
    TDepart queryById(Integer departId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDepart> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDepart 实例对象
     * @return 对象列表
     */
    List<TDepart> queryAll(TDepart tDepart);

    /**
     * 新增数据
     *
     * @param tDepart 实例对象
     * @return 影响行数
     */
    int insert(TDepart tDepart);

    /**
     * 修改数据
     *
     * @param tDepart 实例对象
     * @return 影响行数
     */
    int update(TDepart tDepart);

    /**
     * 通过主键删除数据
     *
     * @param departId 主键
     * @return 影响行数
     */
    int deleteById(Integer departId);

    /**
     * 查询所有数据
     */
    @Select("select * from t_depart")
    List<TDepart> selAll();

    /**
     * 查重
     */
    @Select("select count(*) from t_depart where DEPART_NAME=#{departName}")
    int checkName(@Param("departName") String departName);

    /**
     * 查重(编辑)
     */
    @Select("select count(*) from t_depart where DEPART_NAME=#{departName} and DEPART_ID != #{id}")
    int checkName2(@Param("departName") String departName,@Param("id")String id);

    @Select("select * from t_depart where DEPART_NAME=#{departName}")
    List<TDepart> selName(String departName);

    @Select("select * from t_depart where DEPART_ID=#{id}")
    List<TDepart> selNameByid(@Param("id") String id);
}