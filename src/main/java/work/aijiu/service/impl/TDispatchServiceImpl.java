package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TDispatch;
import work.aijiu.dao.TDispatchDao;
import work.aijiu.service.TDispatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TDispatch)表服务实现类
 *
 * @author makejava
 * @since 2020-04-07 18:03:04
 */
@Service
public class TDispatchServiceImpl implements TDispatchService {
    @Autowired
    private TDispatchDao tDispatchDao;

    /**
     * 通过ID查询单条数据
     *
     * @param dispatchid 主键
     * @return 实例对象
     */
    @Override
    public TDispatch queryById(Integer dispatchid) {
        return this.tDispatchDao.queryById(dispatchid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TDispatch> queryAllByLimit(int offset, int limit) {
        return this.tDispatchDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tDispatch 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(TDispatch tDispatch) {
        return this.tDispatchDao.insert(tDispatch)>0;
    }

    /**
     * 修改数据
     *
     * @param tDispatch 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(TDispatch tDispatch) {
        return  this.tDispatchDao.update(tDispatch)>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param dispatchid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer dispatchid) {
        return this.tDispatchDao.deleteById(dispatchid) > 0;
    }

    @Override
    public List<TDispatch> selByName(String drivername) {
        return tDispatchDao.selByName(drivername);
    }

    @Override
    public List<TDispatch> selByState(String state) {
        return tDispatchDao.selByState(state);
    }

    @Override
    public List<TDispatch> selNamestate(String namestate) {
        return tDispatchDao.selByName(namestate);
    }

    @Override
    public LayuiPageResult unreturnPage(String drivername,Integer page, Integer limit) {
        return new LayuiPageResult(tDispatchDao.selAll().size(),tDispatchDao.unreturnPage(drivername,page,limit));
    }

    @Override
    public LayuiPageResult retuenMsg(String drivername,Integer page, Integer limit) {
        return new LayuiPageResult(tDispatchDao.selAll().size(),tDispatchDao.retuenMsg(drivername,page,limit));
    }

    @Override
    public LayuiPageResult retuenMsgAdmin(Integer page, Integer limit,String departmentId) {
        return new LayuiPageResult(tDispatchDao.selAll().size(),tDispatchDao.retuenMsgAdmin(page,limit,departmentId));
    }

    @Override
    public List<TDispatch> selById(Integer dispatchid) {
        return tDispatchDao.selById(dispatchid);
    }

    @Override
    public List<TDispatch> selAll() {
        return tDispatchDao.selAll();
    }

    @Override
    public boolean NameIsZero(String driverName) {
        return tDispatchDao.NameIsZero(driverName)>0;
    }
}