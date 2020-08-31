package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.TCard;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TCard)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-08 20:41:54
 */
@Repository
public interface TCardDao {

    /**
     * 通过ID查询单条数据
     *
     * @param cardid 主键
     * @return 实例对象
     */
    TCard queryById(Integer cardid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TCard> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tCard 实例对象
     * @return 对象列表
     */
    List<TCard> queryAll(TCard tCard);

    /**
     * 新增数据
     *
     * @param tCard 实例对象
     * @return 影响行数
     */
    int insert(TCard tCard);

    /**
     * 修改数据
     *
     * @param tCard 实例对象
     * @return 影响行数
     */
    int update(TCard tCard);

    /**
     * 通过主键删除数据
     *
     * @param cardid 主键
     * @return 影响行数
     */
    int deleteById(Integer cardid);

    /**
     * 查询全部
     */
    @Select("select * from t_card")
    List<TCard> selAll();

    /**
     * 根据卡号查询
     */
    @Select("select count(*) from t_card where cardNumber = #{cardnumber}")
    int selByCardNumber(@Param("cardnumber") String cardnumber);


    @Select("select count(*) from t_card where cardNumber = #{cardnumber} and cardid != #{id}")
    int selByCardNumber2(@Param("cardnumber") String cardnumber,@Param("id")String id);

    @Select("select * from t_card where cardNumber = #{cardnumber}")
    List<TCard> selNumber(@Param("cardnumber") String cardnumber);

    List<TCard> queryAllPage(@Param("cardnumber") String cardnumber, @Param("page") Integer page, @Param("limit") Integer limit);
}