package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * (TModel)实体类
 *
 * @author makejava
 * @since 2020-04-01 21:26:51
 */
@Data
@TableName("t_model")
public class Model implements Serializable {
    private static final long serialVersionUID = -84411282222127736L;

    @TableId("id")
    private Integer id;
    /**
    * 名字
    */
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}