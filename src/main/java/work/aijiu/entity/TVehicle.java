package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TVehicle dispatching)实体类
 *
 * @author makejava
 * @since 2020-04-06 14:53:17
 */
@Data
@TableName("t_vehicle")
public class TVehicle  implements Serializable {
    private static final long serialVersionUID = -72840562160306000L;
    /**
    * 自动编码
    */
    @TableId("id")
    private Integer id;
    /**
    * 姓名
    */
    private String username;
    /**
    * 车牌号
    */
    private String platenumber;
    /**
    * 开始时间
    */
    private String starttime;
    /**
    * 结束时间
    */
    private String endtime;
    /**
    * 状态（0 开始 1结束即还车）
    */
    private String state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlatenumber() {
        return platenumber;
    }

    public void setPlatenumber(String platenumber) {
        this.platenumber = platenumber;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}