package work.aijiu.service.impl;

import work.aijiu.entity.Setting;
import work.aijiu.dao.SettingDao;
import work.aijiu.service.SettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Setting)表服务实现类
 *
 * @author makejava
 * @since 2020-08-30 09:39:49
 */
@Service("settingService")
public class SettingServiceImpl implements SettingService {
    @Resource
    private SettingDao settingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Setting queryById(Integer id) {
        return this.settingDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Setting> queryAllByLimit(int offset, int limit) {
        return this.settingDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @Override
    public Setting insert(Setting setting) {
        this.settingDao.insert(setting);
        return setting;
    }

    /**
     * 修改数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @Override
    public int update(Setting setting) {
        return  this.settingDao.update(setting);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.settingDao.deleteById(id) > 0;
    }
}