package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Approver
 * @Description 申请表
 * @Author Administrator
 * @Date 2020/3/30 18:17
 * @Version 1.0
 **/
@Data
@TableName("t_approver")
public class Approver implements Serializable {

    @TableId("applicationid")
    private String applicationid;

    private String applicationNumber;

    private String applicationName;

    private String tel;

    private String departmentId;

    private String leaveTime;

    private String returnTime;

    private String destination;

    private String reason;

    private String destinationTime;

    private String approver_pid;

    private String approver_id;

    private String approverTime;

    private String approverState;

    private String approverComment;

    private String isRead;
}
