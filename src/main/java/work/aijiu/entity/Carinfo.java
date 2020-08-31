package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TCarinfo)实体类
 *
 * @author makejava
 * @since 2020-04-01 11:53:34
 */
@Data
@TableName("t_carinfo")
public class Carinfo implements Serializable {
    private static final long serialVersionUID = -88202308604399264L;
    /**
    * 自动编号
    */
    @TableId("carinfoId")
    private Integer carinfoId;
    /**
    * 车牌号码
    */
    private String plateNumber;
    /**
    * 车辆类型
    */
    private String vehicleClassname;
    /**
    * 车架号
    */
    private String frameNumber;
    /**
    * 出厂编号
    */
    private String factoryNumber;
    /**
    * 品牌型号
    */
    private String brandModel;
    /**
    * 注册时间
    */
    private String regTime;
    /**
    * 年检时间
    */
    private String examinationTime;
    /**
    * 载客定员
    */
    private Integer ratedPassengers;
    /**
    * 状态（0无效，1有效）
    */
    private String state;
    /**
    * 备注信息
    */
    private String commentInfo;


    public Integer getCarinfoId() {
        return carinfoId;
    }

    public void setCarinfoId(Integer carinfoId) {
        this.carinfoId = carinfoId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getVehicleClassname() {
        return vehicleClassname;
    }

    public void setVehicleClassname(String vehicleClassname) {
        this.vehicleClassname = vehicleClassname;
    }

    public String getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(String frameNumber) {
        this.frameNumber = frameNumber;
    }

    public String getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(String factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getBrandModel() {
        return brandModel;
    }

    public void setBrandModel(String brandModel) {
        this.brandModel = brandModel;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(String examinationTime) {
        this.examinationTime = examinationTime;
    }

    public Integer getRatedPassengers() {
        return ratedPassengers;
    }

    public void setRatedPassengers(Integer ratedPassengers) {
        this.ratedPassengers = ratedPassengers;
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

    public Carinfo(Integer carinfoId, String plateNumber, String vehicleClassname, String frameNumber, String factoryNumber, String brandModel, String regTime, String examinationTime, Integer ratedPassengers, String state, String commentInfo) {
        this.carinfoId = carinfoId;
        this.plateNumber = plateNumber;
        this.vehicleClassname = vehicleClassname;
        this.frameNumber = frameNumber;
        this.factoryNumber = factoryNumber;
        this.brandModel = brandModel;
        this.regTime = regTime;
        this.examinationTime = examinationTime;
        this.ratedPassengers = ratedPassengers;
        this.state = state;
        this.commentInfo = commentInfo;
    }

    public Carinfo() {
    }
}