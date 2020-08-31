package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.TDispatch;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TDispatch)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-07 18:03:04
 */

@Repository
public interface TDispatchDao {

    /**
     * 通过ID查询单条数据
     *
     * @param dispatchid 主键
     * @return 实例对象
     */
    TDispatch queryById(Integer dispatchid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDispatch> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDispatch 实例对象
     * @return 对象列表
     */
    List<TDispatch> queryAll(TDispatch tDispatch);

    /**
     * 新增数据
     *
     * @param tDispatch 实例对象
     * @return 影响行数
     */
    int insert(TDispatch tDispatch);

    /**
     * 修改数据
     *
     * @param tDispatch 实例对象
     * @return 影响行数
     */
    int update(TDispatch tDispatch);

    /**
     * 通过主键删除数据
     *
     * @param dispatchid 主键
     * @return 影响行数
     */
    int deleteById(Integer dispatchid);

    /**
     * 根据申请人姓名查询调度id
     */
    @Select("select * from t_dispatch where driverName = #{drivername} and state = '0'")
    List<TDispatch> selByName(String drivername);

    /**
     * 查询所有
     */
    @Select("select * from t_dispatch")
    List<TDispatch> selAll();
    /**
     * 根据状态查询调度
     */
    @Select("select * from t_dispatch where state = #{state}")
    List<TDispatch> selByState(@Param("state") String state);
    /**
     * 下拉框赋值（namestate=0）
     */
    @Select("select * from t_dispatch where namestate = #{namestate}")
    List<TDispatch> selNamestate(@Param("namestate") String namestate);
//
    @Select("select  * from t_dispatch d, (SELECT * from t_userinfo where DEPART_NAME = (select  u.DEPART_NAME from t_userinfo u where USERNAME = #{drivername})) z where d.state = '0' and driverName = z.USERNAME  limit #{page},#{limit}")
    List<TDispatch> unreturnPage(@Param("drivername") String drivername, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 归还车记录(客户端)
     */
    @Select("select * from t_dispatch where state = '1'  and driverName = #{drivername} limit #{page},#{limit}")
    List<TDispatch> retuenMsg(@Param("drivername") String drivername, @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 归还车记录(后台管理员)
     */
    @Select("SELECT * from  t_dispatch h where h.state = 1 and driverName in (SELECT distinct applicationName from t_approver  where departmentId = #{departmentId} )  limit #{page},#{limit}")
    List<TDispatch> retuenMsgAdmin(@Param("page") Integer page, @Param("limit") Integer limit,@Param("departmentId") String departmentId);

    /**
     * 根据id 查询
     */
    @Select("select * from t_dispatch where dispatchid = #{dispatchid}")
    List<TDispatch> selById(@Param("dispatchid") Integer dispatchid);


    @Select("select count(*) from t_dispatch where  driverName = #{driverName} and state = '0'")
    int NameIsZero(@Param("driverName") String driverName);
}