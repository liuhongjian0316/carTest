package work.aijiu.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.Cartype;

import java.util.List;

/**
 * @ClassName CartypeDao
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 21:09
 * @Version 1.0
 **/
@Repository
public interface CartypeDao {

    @Select("select * from t_cartype")
    List<Cartype> selAll();

    @Select("select * from t_cartype limit #{page},#{limit}")
    List<Cartype> selCartypePage(@Param("page") Integer page, @Param("limit") Integer limit);

    @Select("select count(*) from t_cartype where VEHICLE_CLASSNAME=#{vehicleClassname}")
    int checkName(@Param("vehicleClassname") String vehicleClassname);

    @Select("select * from t_cartype where VEHICLE_CLASSNAME=#{vehicleClassname}")
    List<Cartype> selbyName(String vehicleClassname);

    @Select("select count(*) from t_cartype where VEHICLE_CLASSNAME=#{vehicleClassname} and CARTYPE_ID != #{cartypeid}")
    int checkName2(@Param("vehicleClassname") String vehicleClassname,@Param("cartypeid") String cartypeid);

    int addCartype(@Param("vehicleClassname") String vehicleClassname, @Param("commentInfo") String commentInfo, @Param("state") String state);

    @Select("select * from t_cartype where CARTYPE_ID = #{cartypeId}")
    List<Cartype> selById(@Param("cartypeId") String cartypeId);

    int editCartype(@Param("vehicleClassname") String vehicleClassname, @Param("commentInfo") String commentInfo,
                    @Param("state") String state, @Param("cartypeId") String cartypeId);
    int delCartypeById(@Param("cartypeId") String cartypeId);
}
