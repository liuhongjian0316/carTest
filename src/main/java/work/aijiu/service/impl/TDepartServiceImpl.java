package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TDepart;
import work.aijiu.dao.TDepartDao;
import work.aijiu.service.TDepartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TDepart)表服务实现类
 *
 * @author makejava
 * @since 2020-04-05 19:24:40
 */
@Service
public class TDepartServiceImpl implements TDepartService {

    @Autowired
    private TDepartDao tDepartDao;

    /**
     * 通过ID查询单条数据
     *
     * @param departId 主键
     * @return 实例对象
     */
    @Override
    public TDepart queryById(Integer departId) {
        return this.tDepartDao.queryById(departId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TDepart> queryAllByLimit(int offset, int limit) {
        return this.tDepartDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tDepart 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(TDepart tDepart) {
        return this.tDepartDao.insert(tDepart);
    }

    /**
     * 修改数据
     *
     * @param tDepart 实例对象
     * @return 实例对象
     */
    @Override
    public int update(TDepart tDepart) {
        return this.tDepartDao.update(tDepart);
    }

    /**
     * 通过主键删除数据
     *
     * @param departId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer departId) {
        return this.tDepartDao.deleteById(departId) > 0;
    }

    @Override
    public LayuiPageResult departPage(Integer page, Integer limit) {
        return new LayuiPageResult(tDepartDao.selAll().size(),tDepartDao.queryAllByLimit(page,limit));
    }

    @Override
    public List<TDepart> selAll() {
        return tDepartDao.selAll();
    }

    @Override
    public boolean checkName(String departName) {
        return tDepartDao.checkName(departName)>0;
    }

    @Override
    public boolean checkName2(String departName, String olddepartName) {
        List<TDepart> tDeparts = tDepartDao.selName(olddepartName);
        return tDepartDao.checkName2(departName, tDeparts.get(0).getDepartId().toString())>0;
    }

    @Override
    public List<TDepart> selNameByid(String id) {
        return null;
    }
}