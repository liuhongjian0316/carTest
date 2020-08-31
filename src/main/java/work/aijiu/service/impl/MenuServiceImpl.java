package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Service;
import work.aijiu.VO.MenuVo;
import work.aijiu.VO.MenuVo2;
import work.aijiu.VO.TreeVo;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.MenuDao;
import work.aijiu.entity.Menu;
import work.aijiu.entity.Rm;
import work.aijiu.service.MenuService;

import java.util.List;

/**
 * @ClassName MenuServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/30 14:54
 * @Version 1.0
 **/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao = SpringContextHolder.getBean(MenuDao.class);

    @Override
    public List<Menu> showMenu(String roleid) {
        return menuDao.selMenu(roleid);
    }

    @Override
    public List<MenuVo> getmenu(String userinfoid) {
        return menuDao.getmenu(userinfoid);
    }

    @Override
    public List<Menu> getmenuList() {
        return menuDao.getmenuList();
    }

    @Override
    public int insert(Menu menu) {
        return menuDao.insert(menu);
    }

    @Override
    public int update(Menu menu) {
        return menuDao.update(menu);
    }

    @Override
    public int delete(String id) {
        return menuDao.delete(id);
    }

    @Override
    public List<Menu> getByID(String id) {
        return menuDao.getByID(id);
    }

    @Override
    public List<MenuVo2> getroleList(String roleid) {
        return menuDao.getroleList(roleid);
    }

    @Override
    public int rmadd(String roleid, String menuid) {
        return menuDao.rmadd(roleid,menuid);
    }

    @Override
    public int rmdel(String roleid, String menuid) {
        return menuDao.rmdel(roleid,menuid);
    }

    @Override
    public List<Rm> getRmByRm(String roleid, String menuid) {
        return menuDao.getRmByRm(roleid, menuid);
    }

    @Override
    public int rmupdate(String roleid, String menuid, String roleid2, String menuid2) {
        return menuDao.rmupdate(roleid,menuid,roleid2,menuid2);
    }
}
