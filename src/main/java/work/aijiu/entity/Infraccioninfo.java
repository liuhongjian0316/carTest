package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 违章信息表(TInfraccioninfo)实体类
 *
 * @author makejava
 * @since 2020-04-04 21:28:03
 */
@Data
@TableName("t_infraccioninfo")
public class Infraccioninfo implements Serializable {
    private static final long serialVersionUID = -36971640927636135L;
    /**
    * 自动编号
    */
    private Integer infraccioninfoId;
    /**
    * 车牌号码
    */
    private String plateNumber;
    /**
    * 驾驶证号码
    */
    private String drivingLicenseNumber;
    /**
    * 违章日期
    */
    private String infraccionTime;
    /**
    * 违章地点
    */
    private String infraccionSite;
    /**
    * 违章信息明细
    */
    private String infraccionInfo;
    /**
    * 处理结果
    */
    private String resultsOfTransaction;
    /**
    * 违章费用
    */
    private Double infraccionCost;
    /**
    * 备注信息
    */
    private String commentInfo;


    public Integer getInfraccioninfoId() {
        return infraccioninfoId;
    }

    public void setInfraccioninfoId(Integer infraccioninfoId) {
        this.infraccioninfoId = infraccioninfoId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getInfraccionTime() {
        return infraccionTime;
    }

    public void setInfraccionTime(String infraccionTime) {
        this.infraccionTime = infraccionTime;
    }

    public String getInfraccionSite() {
        return infraccionSite;
    }

    public void setInfraccionSite(String infraccionSite) {
        this.infraccionSite = infraccionSite;
    }

    public String getInfraccionInfo() {
        return infraccionInfo;
    }

    public void setInfraccionInfo(String infraccionInfo) {
        this.infraccionInfo = infraccionInfo;
    }

    public String getResultsOfTransaction() {
        return resultsOfTransaction;
    }

    public void setResultsOfTransaction(String resultsOfTransaction) {
        this.resultsOfTransaction = resultsOfTransaction;
    }

    public Double getInfraccionCost() {
        return infraccionCost;
    }

    public void setInfraccionCost(Double infraccionCost) {
        this.infraccionCost = infraccionCost;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

}