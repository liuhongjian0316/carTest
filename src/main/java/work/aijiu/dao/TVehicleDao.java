package work.aijiu.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.TVehicle;

import java.util.List;

/**
 * @ClassName TVehicle
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/6 14:54
 * @Version 1.0
 **/
@Repository
public interface TVehicleDao {

    int insert(TVehicle tVehicle);

    @Select("select * from t_vehicle where state = '0' and username = #{username} limit #{page},#{limit}")
    List<TVehicle> layuiPage(@Param("username") String username, @Param("page") Integer page, @Param("limit") Integer limit);

    @Select("select * from t_vehicle")
    List<TVehicle> selAll();

    int update(TVehicle tVehicle);

    @Select("select * from t_vehicle where state = '1' limit #{page},#{limit}")
    List<TVehicle> unreturnMsglayuiPage(@Param("page") Integer page, @Param("limit") Integer limit);
}
