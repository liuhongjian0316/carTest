package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TCard)实体类
 *
 * @author makejava
 * @since 2020-04-08 20:41:54
 */
@Data
@TableName("t_card")
public class TCard implements Serializable {
    private static final long serialVersionUID = 424485478562545519L;
    /**
    * 加油卡id
    */
    @TableId("cardid")
    private Integer cardid;
    /**
    * 加油卡编号
    */
    private String cardnumber;
    /**
    * 余额
    */
    private Double balance;
    /**
    * 车牌号
    */
    private String carnumber;


}