package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.Driver;
import work.aijiu.dao.DriverDao;
import work.aijiu.service.DriverService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 驾驶员信息(TDriverinfo)表服务实现类
 *
 * @author makejava
 * @since 2020-04-03 19:29:05
 */
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    private DriverDao driverDao;

    /**
     * 通过ID查询单条数据
     *
     * @param driverinfoId 主键
     * @return 实例对象
     */
    @Override
    public List<Driver> queryById(String driverinfoId) {
        return this.driverDao.queryById(driverinfoId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Driver> queryAllByLimit(int offset, int limit) {
        return this.driverDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param driver 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(Driver driver) {
        return this.driverDao.insert(driver);
    }

    /**
     * 修改数据
     *
     * @param driver 实例对象
     * @return 影响行数
     */
    @Override
    public int update(Driver driver) {
        return this.driverDao.update(driver);
    }

    /**
     * 通过主键删除数据
     *
     * @param driverinfoId 主键
     * @return 是否成功
     */
    @Override
    public int deleteById(String driverinfoId) {
        return this.driverDao.deleteById(driverinfoId);
    }

    @Override
    public List<Driver> selAll() {
        return this.driverDao.selAll();
    }

    @Override
    public int checkIsRep(String drivinglicenseNumber) {
        return driverDao.checkIsRep(drivinglicenseNumber);
    }

    @Override
    public List<Driver> queryAll(String driverName, String driverSex, String drivinglicenseNumber, String state, String drivingType, String telNumber, Integer page, Integer limit) {
        return driverDao.queryAll(driverName,driverSex,drivinglicenseNumber,state,drivingType,telNumber,page,limit);
    }

    @Override
    public List<Driver> selPlateByName(String dirverName) {
        return driverDao.selPlateByName(dirverName);
    }

    @Override
    public int countDriver() {
        return driverDao.countDriver();
    }

    @Override
    public int countAbleDriver() {
        return driverDao.countAbleDriver();
    }

    @Override
    public int checkIsRep2(String drivinglicenseNumber, String olddrivinglicenseNumber) {
        List<Driver> drivers = driverDao.selByDNum(olddrivinglicenseNumber);

        return driverDao.checkIsRep2(drivinglicenseNumber,drivers.get(0).getDriverinfoId().toString());
    }
}