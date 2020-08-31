package work.aijiu.service;

import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TCardlog;
import java.util.List;

/**
 * (TCardlog)表服务接口
 *
 * @author makejava
 * @since 2020-04-10 14:00:17
 */
public interface TCardlogService {

    /**
     * 通过ID查询单条数据
     *
     * @param cardlogid 主键
     * @return 实例对象
     */
    TCardlog queryById(Integer cardlogid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TCardlog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tCardlog 实例对象
     * @return 实例对象
     */
    boolean insert(TCardlog tCardlog);

    /**
     * 修改数据
     *
     * @param tCardlog 实例对象
     * @return 实例对象
     */
    boolean update(TCardlog tCardlog);

    /**
     * 通过主键删除数据
     *
     * @param cardlogid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer cardlogid);

    LayuiPageResult cardlogPage(Integer page, Integer limit);


    LayuiPageResult searchPage(String carnumber, String addtime, Integer page, Integer limit);


    /**
     * 统计总消费
     */
    Double getCardCost();
}