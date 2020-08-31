package work.aijiu.service;

import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TCard;
import java.util.List;

/**
 * (TCard)表服务接口
 *
 * @author makejava
 * @since 2020-04-08 20:41:54
 */
public interface TCardService {

    /**
     * 通过ID查询单条数据
     *
     * @param cardid 主键
     * @return 实例对象
     */
    TCard queryById(Integer cardid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TCard> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tCard 实例对象
     * @return 实例对象
     */
    boolean insert(TCard tCard);

    /**
     * 修改数据
     *
     * @param tCard 实例对象
     * @return 实例对象
     */
    boolean update(TCard tCard);

    /**
     * 通过主键删除数据
     *
     * @param cardid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cardid);

    /**
     * layui表格数据
     */
    LayuiPageResult cardpage(Integer page, Integer limit);

    /**
     * 检查卡号是否重复
     */
    boolean checkIsRep(String cardnumber);

    /**
     * 检查卡号是否重复(编辑)
     */
    boolean checkIsRep2(String cardnumber,String oldcardnumber);

    /**
     * 模糊查询 返回分页
     */
    LayuiPageResult searchPage(String cardnumber, Integer page, Integer limit);

    /**
     * 查询所有
     * @return
     */
    List<TCard> selAll();

}