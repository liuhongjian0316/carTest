package work.aijiu.service;


import org.apache.ibatis.annotations.*;
import work.aijiu.VO.MenuVo;
import work.aijiu.VO.MenuVo2;
import work.aijiu.VO.TreeVo;
import work.aijiu.entity.Menu;
import work.aijiu.entity.Rm;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description 菜单
 * @Author Administrator
 * @Date 2020/3/30 14:51
 * @Version 1.0
 **/
public interface MenuService {
    List<Menu> showMenu(String roleid);

    List<MenuVo> getmenu(String userinfoid);

    List<Menu> getmenuList();

    int insert(Menu menu);

    int update(Menu menu);

    int delete(String id);

    List<Menu> getByID(String id);

    List<MenuVo2> getroleList(String roleid);

    int rmadd( String roleid, String menuid);

    int rmdel(String roleid,String menuid);

    List<Rm> getRmByRm(String roleid, String menuid);

    /**
     *
     * @param roleid 改的值
     * @param menuid 改的值
     * @param roleid2 原来的值
     * @param menuid2 原来的值
     * @return
     */
    int rmupdate(String roleid, String menuid, String roleid2, String menuid2);
}
