package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 驾驶员信息(TDriverinfo)实体类
 *
 * @author makejava
 * @since 2020-04-03 19:29:05
 */
@Data
@TableName("t_driverinfo")
public class Driver implements Serializable {
    private static final long serialVersionUID = 692764850150354111L;
    /**
    * 自动编号
    */
    @TableId("driverinfoId")
    private Integer driverinfoId;
    /**
    * 驾驶证号
    */
    private String drivinglicenseNumber;
    /**
    * 驾驶员姓名
    */
    private String driverName;
    /**
    * 性别（0男1女）
    */
    private String driverSex;
    /**
    * 年龄
    */
    private String driverAge;
    /**
    * 驾驶员编号
    */
    private String driverNumber;
    /**
    * 联系电话
    */
    private String telNumber;
    /**
    * 家庭住址
    */
    private String driverAddress;
    /**
    * 准驾车型
    */
    private String drivingType;
    /**
    * 状态（0无效 1有效
    */
    private String state;
    /**
    * 备注信息
    */
    private String commentInfo;


    public Integer getDriverinfoId() {
        return driverinfoId;
    }

    public void setDriverinfoId(Integer driverinfoId) {
        this.driverinfoId = driverinfoId;
    }

    public String getDrivinglicenseNumber() {
        return drivinglicenseNumber;
    }

    public void setDrivinglicenseNumber(String drivinglicenseNumber) {
        this.drivinglicenseNumber = drivinglicenseNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverSex() {
        return driverSex;
    }

    public void setDriverSex(String driverSex) {
        this.driverSex = driverSex;
    }

    public String getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(String driverAge) {
        this.driverAge = driverAge;
    }

    public String getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(String driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDrivingType() {
        return drivingType;
    }

    public void setDrivingType(String drivingType) {
        this.drivingType = drivingType;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

}