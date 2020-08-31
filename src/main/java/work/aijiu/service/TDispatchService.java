package work.aijiu.service;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.TDispatch;
import java.util.List;

/**
 * (TDispatch)表服务接口
 *
 * @author makejava
 * @since 2020-04-07 18:03:04
 */
public interface TDispatchService {

    /**
     * 通过ID查询单条数据
     *
     * @param dispatchid 主键
     * @return 实例对象
     */
    TDispatch queryById(Integer dispatchid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDispatch> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tDispatch 实例对象
     * @return 实例对象
     */
    boolean insert(TDispatch tDispatch);

    /**
     * 修改数据
     *
     * @param tDispatch 实例对象
     * @return 实例对象
     */
    boolean update(TDispatch tDispatch);

    /**
     * 通过主键删除数据
     *
     * @param dispatchid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer dispatchid);

    /**
     * 根据name 查询 调度id
     */
    List<TDispatch> selByName(String drivername);

    /**
     * 根据状态查询调度（派车是姓名下拉框的赋值）
     * @param state
     * @return
     */
    List<TDispatch> selByState(String state);

    /**
     * 根据状态查询调度（派车是姓名下拉框的赋值）
     * @param namestate
     * @return
     */
    List<TDispatch> selNamestate(String namestate);

    /**
     * 未还车记录
     */
    LayuiPageResult unreturnPage(String drivername, Integer page, Integer limit);

    /**
     * 还车记录
     */
    LayuiPageResult retuenMsg(String drivername, Integer page, Integer limit);

    /**
     * 还车记录(管理员)
     */
    LayuiPageResult retuenMsgAdmin(Integer page, Integer limit, String departmentId);

    /**
     * 根据id获取调度信息
     */
    List<TDispatch> selById(Integer dispatchid);

    /**
     * 查询所有
     * @return
     */
    List<TDispatch> selAll();

    /**
     * 改name的状态是否为0 是的话，不允许删除该驾驶员，反之可以删除
     * @param driverName
     * @return
     */
    boolean NameIsZero(String driverName);
}