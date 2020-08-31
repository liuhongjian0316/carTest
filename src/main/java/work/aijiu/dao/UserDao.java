package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Administrator
 * @Date 2020/3/28 16:53
 * @Version 1.0
 **/
@Repository
public interface UserDao extends BaseMapper<User> {

    List<User> selUserByAccoundAndPwd(@Param("userAccount") String account, @Param("pwd") String pwd);

    List<User> userPage(@Param("page") Integer page, @Param("limit") Integer limit);

    @Select("select * from t_userinfo")
    List<User> selAll();

    int insertUser(User user);

    @Select("select count(*) from t_userinfo where USERACCOUNT = #{userAccount}")
    int checkAccount(@Param("userAccount") String userAccount);

    @Select("select count(*) from t_userinfo where USERACCOUNT = #{userAccount} and USERINFOID != #{id}")
    int checkAccount2(@Param("userAccount") String userAccount, @Param("id") String id);


    @Select("select count(*) from t_userinfo where USERNAME = #{userName}")
    int checkName(@Param("userName") String userName);

    @Select("select count(*) from t_userinfo where TEL = #{tel}")
    int checkTel(@Param("tel") String tel);

    @Select("select * from t_userinfo where  USERINFOID = #{userinfoid}")
    List<User> selById(@Param("userinfoid") String userinfoid);

    int update(User user);

    int delete(String userinfoid);

    List<User> search(@Param("userAccount") String userAccount, @Param("tel") String tel, @Param("departName") String departName,
                      @Param("page") Integer page, @Param("limit") Integer limit);

    int updateRole(@Param("roleid") String roleid, @Param("userinfoid") String userinfoid);

    @Select("select count(*) from t_userinfo")
    int countUser();

    @Select("select count(*) from t_userinfo where role_id = '2'")
    int countOrdUser();

    @Select("select count(*) from t_userinfo where role_id = '3'")
    int countAdmin();

    List<Map<String,Object>> selectRoleDate();

    List<Map<String,Object>> selectSexDate();

    List<Map<String,Object>> selectDeaprtmentDate();

    @Select("select *from t_userinfo where USERACCOUNT = #{account}")
    List<User> selByAccount(@Param("account") String account);
}
