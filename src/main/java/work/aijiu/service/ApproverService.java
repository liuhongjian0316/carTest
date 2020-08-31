package work.aijiu.service;


import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.Approver;

import java.util.List;

/**
 * @ClassName ApproverService
 * @Description 申请用车
 * @Author Administrator
 * @Date 2020/3/30 20:37
 * @Version 1.0
 **/
public interface ApproverService {

    int applay(Approver approver);

    LayuiPageResult UntreatedPage(Integer page, Integer limit,String departmentId);

    List<Approver> selById(String applicationid);

    int updateState(String approver_pid, String approver_id, String approverTime,
                    String approverState, String approverComment, String applicationid);

    int updateStateById(String approver_pid, String approver_id, String approverTime,
                        String approverState, String applicationid);

    LayuiPageResult treatedPage(Integer page, Integer limit,String departmentId);

    int delApp(String approverState, String applicationid);

    LayuiPageResult selIsReadPage(String applicationName, Integer page, Integer limit);

    int read(String isRead, String applicationid);

    List<Approver> selAll();

    List<Approver> selappName(String departmentId);

    int updateAppState4(String name);

    /**
     * 草稿功能(写入缓存中)
     * @param approver
     * @return
     */
    boolean saveRedis(Approver approver);

    /**
     * congredis中获取草稿数据
     * @param name
     * @return
     */
    Approver getRedis(String name);
}
