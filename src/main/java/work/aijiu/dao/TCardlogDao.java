package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.TCardlog;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TCardlog)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-10 14:00:17
 */
@Repository
public interface TCardlogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cardlogid 主键
     * @return 实例对象
     */
    TCardlog queryById(Integer cardlogid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TCardlog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param
     * @return 对象列表
     */
    List<TCardlog> queryAll(@Param("carnumber") String carnumber, @Param("addtime") String addtime, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 新增数据
     *
     * @param tCardlog 实例对象
     * @return 影响行数
     */
    int insert(TCardlog tCardlog);

    /**
     * 修改数据
     *
     * @param tCardlog 实例对象
     * @return 影响行数
     */
    int update(TCardlog tCardlog);

    /**
     * 通过主键删除数据
     *
     * @param cardlogid 主键
     * @return 影响行数
     */
    int deleteById(Integer cardlogid);

    /**
     * 查询全部
     * @return
     */
    @Select("select * from t_cardlog")
    List<TCardlog> selAll();

    /**
     * 统计总消费
     */
    @Select("select sum(cost) from t_cardlog")
    Double getCardCost();
}