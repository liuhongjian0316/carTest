package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TDepart)实体类
 *
 * @author makejava
 * @since 2020-04-05 19:24:40
 */
@Data
@TableName("t_depart")
public class TDepart implements Serializable {
    private static final long serialVersionUID = 213512636099250962L;
    /**
    * 部门ID
    */
    @TableId("departId")
    private Integer departId;
    /**
    * 部门名称
    */
    private String departName;
    /**
    * 父id
    */
    private Integer pid;


    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}