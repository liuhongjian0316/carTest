package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import work.aijiu.VO.MenuVo;
import work.aijiu.VO.MenuVo2;
import work.aijiu.VO.TreeVo;
import work.aijiu.entity.Menu;
import work.aijiu.entity.Rm;

import java.util.List;

/**
 * @ClassName MenuDao
 * @Description 菜单
 * @Author Administrator
 * @Date 2020/3/30 14:55
 * @Version 1.0
 **/
@Repository
public interface MenuDao{

    List<Menu> selMenu(@Param("roleid") String roleid);

    @Select("SELECT DISTINCT  m.name  , m.mid id, m.pid pId from t_userinfo u , t_role_menu rm ,t_menu m  where u.USERINFOID = #{userinfoid} and  u.role_id = rm.roleid and rm.menuid = m.mid ")
    List<MenuVo> getmenu(@Param("userinfoid") String userinfoid);

    @Select("SELECT  id , mid , name ,url, pid from t_menu")
    List<Menu> getmenuList();

    int insert(Menu menu);

    int update(Menu menu);

    int delete(@Param("id") String id);

    @Select("SELECT  id , mid , name ,url, pid from t_menu where id = #{id}")
    List<Menu> getByID(@Param("id") String id);

    @Select("SELECT DISTINCT  r.id, r.name rolename ,rm.menuid , m.name name , m.mid, m.pid from t_role r , t_role_menu rm ,t_menu m  where r.id = #{roleid} and   r.id = rm.roleid and rm.menuid = m.mid ")
    List<MenuVo2> getroleList(@Param("roleid") String roleid);

    @Insert("insert into t_role_menu (roleid, menuid) values  ( #{roleid} , #{menuid} )")
    int rmadd(@Param("roleid") String roleid,@Param("menuid") String menuid);

    @Delete("delete from t_role_menu where roleid = #{roleid} and  menuid = #{menuid}")
    int rmdel(@Param("roleid") String roleid,@Param("menuid") String menuid);

    /**
     * 修改的赋值
     * @param roleid
     * @param menuid
     * @return
     */
    @Select("select * from t_role_menu where roleid = #{roleid} and  menuid = #{menuid}")
    List<Rm> getRmByRm(@Param("roleid") String roleid,@Param("menuid") String menuid);

    /**
     *
     * @param roleid 改的值
     * @param menuid 改的值
     * @param roleid2 原来的值
     * @param menuid2 原来的值
     * @return
     */
    @Update("update t_role_menu set roleid = #{roleid},menuid = #{menuid} where roleid = #{roleid2} and menuid = #{menuid2}")
    int rmupdate(@Param("roleid") String roleid,@Param("menuid") String menuid,@Param("roleid2") String roleid2,@Param("menuid2") String menuid2);
}
