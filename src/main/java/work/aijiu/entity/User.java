package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/28 14:38
 * @Version 1.0
 **/

@Data
@TableName("t_carinfo")
public class User implements Serializable {

    @TableId("USERINFOID")
    private String userinfoid;

    private String userAccount;

    private String userName;

    private String pwd;

    private String sex;

    private String tel;

    private String addr;

    private String departName;

    private String roleid;


}

