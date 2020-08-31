package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Menu
 * @Description 菜单实体类
 * @Author Administrator
 * @Date 2020/3/30 14:47
 * @Version 1.0
 **/

@Data
@TableName("t_menu")
public class Menu implements Serializable {

    /**
     * 主键
     */
    @TableId("id")
    private String id;

    /**
     * 菜单id
     */
    private String mid;

    /**
     * 菜单名字
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     * 父级菜单
     */
    private String pid;

    /**
     * roleid
     */
    private String rloeid;
}
