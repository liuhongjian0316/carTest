package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TDrivertype)实体类
 *
 * @author makejava
 * @since 2020-04-04 10:26:50
 */
@Data
@TableName("t_drivertype")
public class Drivertype implements Serializable {
    private static final long serialVersionUID = -54349275363786194L;
    /**
    * 主键
    */
    @TableId("id")
    private Integer id;
    /**
    * 准假车型
    */
    private String drivertypename;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrivertypename() {
        return drivertypename;
    }

    public void setDrivertypename(String drivertypename) {
        this.drivertypename = drivertypename;
    }

}