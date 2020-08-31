package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.ApproverDao;
import work.aijiu.entity.Approver;
import work.aijiu.entity.Carinfo;
import work.aijiu.redis.RedisService;
import work.aijiu.service.ApproverService;

import java.util.List;


/**
 * @ClassName ApproverServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/30 20:38
 * @Version 1.0
 **/
@Service
public class ApproverServiceImpl implements ApproverService {

    @Autowired
    private ApproverDao approverDao;

    private RedisService redisService = SpringContextHolder.getBean(RedisService.class);
    @Override
    public int applay(Approver approver) {

        return approverDao.insApp(approver.getApplicationNumber(),approver.getApplicationName(),approver.getTel(),
                                    approver.getDepartmentId(),approver.getLeaveTime(),approver.getReturnTime(),
                                    approver.getDestination(),approver.getReason(),approver.getDestinationTime()
                                    ,approver.getApproverComment(),approver.getApproverState(),approver.getIsRead());
    }

    @Override
    public LayuiPageResult UntreatedPage(Integer page, Integer limit,String departmentId) {

        page = (page-1)*limit;
        List<Approver> pageinfo = approverDao.selUnTreat(page, limit,departmentId);
        List<Approver> approvers = approverDao.selAll();
        return new LayuiPageResult(approvers.size(),pageinfo);
    }

    @Override
    public List<Approver> selById(String applicationid) {
        return approverDao.selById(applicationid);
    }

    @Override
    public int updateState(String approver_pid, String approver_id, String approverTime, String approverState, String approverComment, String applicationid) {
        return approverDao.updateState(approver_pid,approver_id,approverTime,approverState,approverComment,applicationid);
    }

    @Override
    public int updateStateById(String approver_pid, String approver_id, String approverTime, String approverState, String applicationid) {
        return approverDao.updateStateById(approver_pid,approver_id,approverTime,approverState,applicationid);
    }

    @Override
    public LayuiPageResult treatedPage(Integer page, Integer limit,String departmentId) {
        page = (page-1)*limit;
        List<Approver> pageinfo = approverDao.selTreat(page, limit,departmentId);
        List<Approver> approvers = approverDao.selAll();
        return new LayuiPageResult(approvers.size(),pageinfo);
    }

    @Override
    public int delApp(String approverState, String applicationid) {
        return approverDao.delApp(approverState,applicationid);
    }

    @Override
    public LayuiPageResult selIsReadPage(String applicationName,Integer page, Integer limit) {
        page = (page-1)*limit;
        List<Approver> pageinfo = approverDao.selIsRead(applicationName,page, limit);
        List<Approver> approvers = approverDao.selAll();
        return  new LayuiPageResult(approvers.size(),pageinfo);
    }

    @Override
    public int read(String isRead, String applicationid) {
        return approverDao.updateIsRead(isRead,applicationid);
    }

    @Override
    public List<Approver> selAll() {
        return approverDao.selAll();
    }

    @Override
    public List<Approver> selappName(String departmentId) {
        return approverDao.selName(departmentId);
    }

    @Override
    public int updateAppState4(String name) {
        return approverDao.updateAppState4(name);
    }

    @Override
    public boolean saveRedis(Approver approver) {
        //draft 草稿的意思
        //将申请单 以申请姓名+draft为key,value 为 approver实体类 存入缓存中 并设置超时时间 7天 60*60*24*7
        return redisService.set(approver.getApplicationName()+"draft",approver,60*60*24*7);
    }

    @Override
    public Approver getRedis(String name) {
        return (Approver) redisService.get(name+"draft");
    }


}
