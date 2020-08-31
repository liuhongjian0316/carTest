package work.aijiu.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import work.aijiu.entity.Menu;
import java.util.List;

@Data
public class MenuVo extends Menu {
    private String title;
    private List<MenuVo> children;
    /**
     * 主键
     */
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
    private String pId;

    /**
     * roleid
     */
    private String rloeid;

}
