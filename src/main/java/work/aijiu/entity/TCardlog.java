package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TCardlog)实体类
 *
 * @author makejava
 * @since 2020-04-10 14:00:17
 */
@Data
@TableName("t_cardlog")
public class TCardlog implements Serializable {
    private static final long serialVersionUID = 853896135247710833L;
    /**
    * 用油量id
    */
    @TableId("cardlogid")
    private Integer cardlogid;
    /**
    * 车牌号
    */
    private String carnumber;
    /**
    * 加油日期
    */
    private String addtime;
    /**
    * 加油量(单位：升)
    */
    private Double addnumber;
    /**
    * 剩余油量（单位：升）
    */
    private Double residue;
    /**
    * 耗油量（单位：升）
    */
    private Double consume;
    /**
    * 花费金额
    */
    private Double cost;


    public Integer getCardlogid() {
        return cardlogid;
    }

    public void setCardlogid(Integer cardlogid) {
        this.cardlogid = cardlogid;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public Double getAddnumber() {
        return addnumber;
    }

    public void setAddnumber(Double addnumber) {
        this.addnumber = addnumber;
    }

    public Double getResidue() {
        return residue;
    }

    public void setResidue(Double residue) {
        this.residue = residue;
    }

    public Double getConsume() {
        return consume;
    }

    public void setConsume(Double consume) {
        this.consume = consume;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

}