package work.aijiu.service;

import org.apache.ibatis.annotations.Param;
import work.aijiu.entity.Driver;
import java.util.List;

/**
 * 驾驶员信息(TDriverinfo)表服务接口
 *
 * @author makejava
 * @since 2020-04-03 19:29:05
 */
public interface DriverService {

    /**
     * 通过ID查询单条数据
     *
     * @param driverinfoId 主键
     * @return 实例对象
     */
    List<Driver> queryById(String driverinfoId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Driver> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param driver 实例对象
     * @return 实例对象
     */
    int insert(Driver driver);

    /**
     * 修改数据
     *
     * @param driver 实例对象
     * @return 实例对象
     */
    int update(Driver driver);

    /**
     * 通过主键删除数据
     *
     * @param driverinfoId 主键
     * @return 是否成功
     */
    int deleteById(String driverinfoId);

    /**
     * 查询所以数据
     * @return
     */
    List<Driver> selAll();

    /**
     * 查重
     */
    int checkIsRep(String drivinglicenseNumber);

    /**
     * 按条件查询
     */
    List<Driver> queryAll(String driverName, String driverSex, String drivinglicenseNumber,
                          String state, String drivingType, String telNumber,
                          Integer page, Integer limit);
    /**
     * 根据姓名查询驾驶证号
     */
    List<Driver> selPlateByName(String dirverName);

    /**
     * 统计驾驶员
     * @return
     */
    int countDriver();

    /**
     * 有效的驾驶员数量
     * @return
     */
    int countAbleDriver();

    /**
     * 编辑查重
     * @param drivinglicenseNumber
     * @param olddrivinglicenseNumber
     * @return
     */
    int checkIsRep2(String drivinglicenseNumber,String olddrivinglicenseNumber);
}