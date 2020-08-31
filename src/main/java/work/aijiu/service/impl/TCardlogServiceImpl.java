package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TCardlog;
import work.aijiu.dao.TCardlogDao;
import work.aijiu.service.TCardlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TCardlog)表服务实现类
 *
 * @author makejava
 * @since 2020-04-10 14:00:17
 */
@Service
public class TCardlogServiceImpl implements TCardlogService {

    @Autowired
    private TCardlogDao tCardlogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cardlogid 主键
     * @return 实例对象
     */
    @Override
    public TCardlog queryById(Integer cardlogid) {
        return this.tCardlogDao.queryById(cardlogid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TCardlog> queryAllByLimit(int offset, int limit) {
        return this.tCardlogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tCardlog 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(TCardlog tCardlog) {
        return this.tCardlogDao.insert(tCardlog)>0;
    }

    /**
     * 修改数据
     *
     * @param tCardlog 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(TCardlog tCardlog) {
        return this.tCardlogDao.update(tCardlog)>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param cardlogid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cardlogid) {
        return this.tCardlogDao.deleteById(cardlogid) > 0;
    }

    @Override
    public LayuiPageResult cardlogPage(Integer page, Integer limit) {
        return new LayuiPageResult(tCardlogDao.selAll().size(),tCardlogDao.queryAllByLimit(page,limit));
    }

    @Override
    public LayuiPageResult searchPage(String carnumber, String addtime, Integer page, Integer limit) {
        return new LayuiPageResult(tCardlogDao.selAll().size(),tCardlogDao.queryAll(carnumber,addtime,page,limit));
    }

    @Override
    public Double getCardCost() {
        return tCardlogDao.getCardCost();
    }
}