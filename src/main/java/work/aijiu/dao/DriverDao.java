package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.Driver;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 驾驶员信息(TDriverinfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-04-03 19:29:05
 */
@Repository
public interface DriverDao {

    /**
     * 通过ID查询单条数据
     *
     * @param driverinfoId 主键
     * @return 实例对象数组
     */
    List<Driver> queryById(String driverinfoId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Driver> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);



    /**
     * 新增数据
     *
     * @param driver 实例对象
     * @return 影响行数
     */
    int insert(Driver driver);

    /**
     * 修改数据
     *
     * @param driver 实例对象
     * @return 影响行数
     */
    int update(Driver driver);

    /**
     * 通过主键删除数据
     *
     * @param driverinfoId 主键
     * @return 影响行数
     */
    int deleteById(String driverinfoId);


    /**
     * 查询所有
     * @return
     */
    @Select("select * from t_driverinfo")
    List<Driver> selAll();

    /**
     * 查重
     */
    @Select("select count(*) from t_driverinfo where DRIVINGLICENSE_NUMBER = #{drivinglicenseNumber}")
    int checkIsRep(@Param("drivinglicenseNumber") String drivinglicenseNumber);

    /**
     * 编辑查重
     */
    @Select("select count(*) from t_driverinfo where DRIVINGLICENSE_NUMBER = #{drivinglicenseNumber} and DRIVERINFO_ID != #{id}")
    int checkIsRep2(@Param("drivinglicenseNumber") String drivinglicenseNumber, @Param("id") String id );

    @Select("select * from t_driverinfo where DRIVINGLICENSE_NUMBER = #{drivinglicenseNumber}")
    List<Driver> selByDNum(String drivinglicenseNumber);

    /**
     * 按条件查询
     */
    List<Driver> queryAll(@Param("driverName") String driverName, @Param("driverSex") String driverSex, @Param("drivinglicenseNumber") String drivinglicenseNumber,
                          @Param("state") String state, @Param("drivingType") String drivingType, @Param("telNumber") String telNumber,
                          @Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 根据姓名查询驾驶证号
     */
    @Select("select * from t_driverinfo where DRIVER_NAME = #{driverName}")
    List<Driver> selPlateByName(String driverName);

    /**
     * 查询所有驾驶员
     */
    @Select("select count(*) from t_driverinfo")
    int countDriver();

    /**
     * 查询有效驾驶员
     */
    @Select("select count(*) from t_driverinfo where state = '1' ")
    int countAbleDriver();
}