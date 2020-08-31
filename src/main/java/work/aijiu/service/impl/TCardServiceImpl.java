package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TCard;
import work.aijiu.dao.TCardDao;
import work.aijiu.service.TCardService;
import java.util.List;

/**
 * (TCard)表服务实现类
 *
 * @author makejava
 * @since 2020-04-08 20:41:54
 */
@Service
public class TCardServiceImpl implements TCardService {

    @Autowired
    private TCardDao tCardDao;

    /**
     * 通过ID查询单条数据
     *
     * @param cardid 主键
     * @return 实例对象
     */
    @Override
    public TCard queryById(Integer cardid) {
        return this.tCardDao.queryById(cardid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TCard> queryAllByLimit(int offset, int limit) {
        return this.tCardDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tCard 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(TCard tCard) {
        return this.tCardDao.insert(tCard)>0;
    }

    /**
     * 修改数据
     *
     * @param tCard 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(TCard tCard) {
        return this.tCardDao.update(tCard)>0;
    }

    /**
     * 通过主键删除数据
     *
     * @param cardid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer cardid) {
        return this.tCardDao.deleteById(cardid) > 0;
    }

    @Override
    public LayuiPageResult cardpage(Integer page, Integer limit) {
        return new LayuiPageResult(tCardDao.selAll().size(),tCardDao.queryAllByLimit(page,limit));
    }

    @Override
    public boolean checkIsRep(String cardnumber) {
        return tCardDao.selByCardNumber(cardnumber)>0;
    }

    @Override
    public boolean checkIsRep2(String cardnumber, String oldcardnumber) {
        List<TCard> tCards = tCardDao.selNumber(oldcardnumber);
        return tCardDao.selByCardNumber2(cardnumber,tCards.get(0).getCardid().toString())>0;
    }

    @Override
    public LayuiPageResult searchPage(String cardnumber, Integer page, Integer limit) {
        return new LayuiPageResult(tCardDao.selAll().size(),tCardDao.queryAllPage(cardnumber,page,limit));
    }

    @Override
    public List<TCard> selAll() {
        return tCardDao.selAll();
    }
}