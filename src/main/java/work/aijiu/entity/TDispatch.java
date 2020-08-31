package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TDispatch)实体类
 *
 * @author makejava
 * @since 2020-04-07 18:03:04
 */
@Data
@TableName("t_dispatch")
public class TDispatch implements Serializable {
    private static final long serialVersionUID = -79403090743893684L;
    /**
    * 车辆调度id
    */
    @TableId("dispatchid")
    private Integer dispatchid;
    /**
    * 车牌号
    */
    private String carNumber;
    /**
    * 驾驶证号
    */
    private String driverlicensenumber;
    /**
    * 驾驶员姓名
    */
    private String drivername;
    /**
    * 用车时间
    */
    private String usingtime;
    /**
    * 实际出车时间
    */
    private String actualtime;
    /**
    * 申请表编号
    */
    private String applicationid;
    /**
    * 申请人编号
    */
    private String applicationnumber;
    /**
    * 行车路线
    */
    private String runroute;
    /**
    * 行驶前的里程数
    */
    private Double kmbefore;
    /**
    * 行驶后的里程数
    */
    private Double kmafter;
    /**
    * 过路费
    */
    private Double toll;
    /**
    * 入库时间
    */
    private String beintime;
    /**
    * 调度备注
    */
    private String dispatchcommment;

    /**
     * 状态
     */
    private String state;

    /**
     * 状态
     */
    private String namestate;

    public Integer getDispatchid() {
        return dispatchid;
    }

    public void setDispatchid(Integer dispatchid) {
        this.dispatchid = dispatchid;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getDriverlicensenumber() {
        return driverlicensenumber;
    }

    public void setDriverlicensenumber(String driverlicensenumber) {
        this.driverlicensenumber = driverlicensenumber;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getUsingtime() {
        return usingtime;
    }

    public void setUsingtime(String usingtime) {
        this.usingtime = usingtime;
    }

    public String getActualtime() {
        return actualtime;
    }

    public void setActualtime(String actualtime) {
        this.actualtime = actualtime;
    }

    public String getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
    }

    public String getApplicationnumber() {
        return applicationnumber;
    }

    public void setApplicationnumber(String applicationnumber) {
        this.applicationnumber = applicationnumber;
    }

    public String getRunroute() {
        return runroute;
    }

    public void setRunroute(String runroute) {
        this.runroute = runroute;
    }

    public Double getKmbefore() {
        return kmbefore;
    }

    public void setKmbefore(Double kmbefore) {
        this.kmbefore = kmbefore;
    }

    public Double getKmafter() {
        return kmafter;
    }

    public void setKmafter(Double kmafter) {
        this.kmafter = kmafter;
    }

    public Double getToll() {
        return toll;
    }

    public void setToll(Double toll) {
        this.toll = toll;
    }

    public String getBeintime() {
        return beintime;
    }

    public void setBeintime(String beintime) {
        this.beintime = beintime;
    }

    public String getDispatchcommment() {
        return dispatchcommment;
    }

    public void setDispatchcommment(String dispatchcommment) {
        this.dispatchcommment = dispatchcommment;
    }

}