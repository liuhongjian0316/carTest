package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 乘车类型(TCartype)实体类
 *
 * @author makejava
 * @since 2020-04-01 21:07:01
 */
@Data
@TableName("t_cartype")
public class Cartype implements Serializable {
    private static final long serialVersionUID = -76129316219100100L;
    /**
    * 自动编号
    */
    @TableId("cartypeId")
    private Integer cartypeId;
    /**
    * 车辆分类名称
    */
    private String vehicleClassname;
    /**
    * 备注信息
    */
    private String commentInfo;
    /**
    * 0损坏 1正常 2借出
    */
    private String state;


    public Integer getCartypeId() {
        return cartypeId;
    }

    public void setCartypeId(Integer cartypeId) {
        this.cartypeId = cartypeId;
    }

    public String getVehicleClassname() {
        return vehicleClassname;
    }

    public void setVehicleClassname(String vehicleClassname) {
        this.vehicleClassname = vehicleClassname;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}