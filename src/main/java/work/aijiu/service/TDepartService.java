package work.aijiu.service;

import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TDepart;
import java.util.List;

/**
 * (TDepart)表服务接口
 *
 * @author makejava
 * @since 2020-04-05 19:24:40
 */
public interface TDepartService {

    /**
     * 通过ID查询单条数据
     *
     * @param departId 主键
     * @return 实例对象
     */
    TDepart queryById(Integer departId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDepart> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tDepart 实例对象
     * @return 实例对象
     */
    int insert(TDepart tDepart);

    /**
     * 修改数据
     *
     * @param tDepart 实例对象
     * @return 实例对象
     */
    int update(TDepart tDepart);

    /**
     * 通过主键删除数据
     *
     * @param departId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer departId);

    /**
     * layui分页数据
     */
    LayuiPageResult departPage(Integer page, Integer limit);

    /**
     * 查询全部数据
     */
    List<TDepart> selAll();

    /**
     * 查重（部门名称）
     */
    boolean checkName(String departName);

    /**
     * 查重编辑（部门名称）
     */
    boolean checkName2(String departName, String olddepartName);

    /**
     * 根据id查询部门信息
     * @param id
     * @return
     */
    List<TDepart> selNameByid(String id);
}