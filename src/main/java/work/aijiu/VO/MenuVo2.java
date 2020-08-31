package work.aijiu.VO;

import lombok.Data;
import work.aijiu.entity.Menu;

import java.util.List;

@Data
public class MenuVo2 extends Menu {
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
    private String pid;

    /**
     * roleid
     */
    private String rloeid;

    private String rolename;

    private String menuid;
}
