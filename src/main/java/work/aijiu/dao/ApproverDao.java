package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.Approver;

import java.util.List;

/**
 * @ClassName ApproverDao
 * @Description 申请dao
 * @Author Administrator
 * @Date 2020/3/30 20:41
 * @Version 1.0
 **/
@Repository
public interface ApproverDao extends BaseMapper<Approver> {

    int insApp(@Param("applicationNumber") String applicationNumber, @Param("applicationName") String applicationName,
               @Param("tel") String tel, @Param("departmentId") String departmentId,
               @Param("leaveTime") String leaveTime, @Param("returnTime") String returnTime,
               @Param("destination") String destination, @Param("reason") String reason,
               @Param("destinationTime") String destinationTime, @Param("approverComment") String approverComment,
               @Param("approverState") String approverState, @Param("isRead") String isRead);

    @Select("select * from t_approver  where  departmentId = #{departmentId} and approverState = '1' limit #{page} , #{limit}")
    List<Approver> selUnTreat(@Param("page") Integer page, @Param("limit") Integer limit,@Param("departmentId")String departmentId);

    @Select("select * from t_approver")
    List<Approver> selAll();

    @Select("select * from t_approver where applicationid = #{applicationid}")
    List<Approver> selById(@Param("applicationid") String applicationid);

    int updateState(@Param("approver_pid") String approver_pid, @Param("approver_id") String approver_id,
                    @Param("approverTime") String approverTime, @Param("approverState") String approverState,
                    @Param("approverComment") String approverComment, @Param("applicationid") String applicationid);

    int updateStateById(@Param("approver_pid") String approver_pid, @Param("approver_id") String approver_id,
                        @Param("approverTime") String approverTime, @Param("approverState") String approverState,
                        @Param("applicationid") String applicationid);

    @Select("select * from t_approver  where departmentId = #{departmentId} and approverState = '2' or approverState = '3' or approverState = '4' limit #{page} , #{limit}")
    List<Approver> selTreat(@Param("page") Integer page, @Param("limit") Integer limit,@Param("departmentId")String departmentId);

    int delApp(@Param("approverState") String approverState, @Param("applicationid") String applicationid);

    @Select("select * from t_approver  where applicationName = #{applicationName}  and approverState = 2 or approverState = '3' limit #{page} , #{limit}")
    List<Approver> selIsRead(@Param("applicationName")String applicationName,@Param("page") Integer page, @Param("limit") Integer limit);

    int updateIsRead(@Param("isRead") String isRead, @Param("applicationid") String applicationid);

    @Select("select * from t_approver  where  departmentId = #{departmentId} and approverState = 2")
    List<Approver> selName(@Param("departmentId") String departmentId);

    @Update("UPDATE t_approver SET approverState = '4' where applicationid = (SELECT a.applicationid from (SELECT  DISTINCT applicationid from t_approver where applicationName = #{name} order by applicationid desc LIMIT 1) a)")
    int updateAppState4(String name);

}