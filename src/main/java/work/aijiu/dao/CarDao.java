package work.aijiu.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.Approver;
import work.aijiu.entity.Carinfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CarDao
 * @Description
 * @Author Administrator
 * @Date 2020/4/1 12:46
 * @Version 1.0
 **/
@Repository
public interface CarDao {

    @Select("select t.VEHICLE_CLASSNAME,c.* from t_carinfo c, t_cartype t where  c.VEHICLE_CLASSNAME = t.CARTYPE_ID limit  #{page} , #{limit}")
    List<Carinfo> selCarPage(@Param("page") Integer page, @Param("limit") Integer limit);

    @Select("select t.VEHICLE_CLASSNAME,c.* from t_carinfo c, t_cartype t where  c.VEHICLE_CLASSNAME = t.CARTYPE_ID")
    List<Carinfo> selAll();

    @Select("select * from t_carinfo where PLATE_NUMBER=#{plateNumber}")
    List<Carinfo> SelCount(@Param("plateNumber") String plateNumber);

    @Select("select * from t_carinfo where PLATE_NUMBER=#{plateNumber} and  CARINFO_ID != #{carinfoId}")
    List<Carinfo> SelCount2(@Param("plateNumber") String plateNumber,@Param("carinfoId") String carinfoId);

    int insCar(@Param("plateNumber") String plateNumber, @Param("vehicleClassname") String vehicleClassname, @Param("frameNumber") String frameNumber,
               @Param("factoryNumber") String factoryNumber, @Param("brandModel") String brandModel, @Param("regTime") String regTime,
               @Param("examinationTime") String examinationTime, @Param("ratedPassengers") String ratedPassengers, @Param("state") String state,
               @Param("commentInfo") String commentInfo);

    @Select("select c.* from t_carinfo c, t_cartype t where  c.VEHICLE_CLASSNAME = t.CARTYPE_ID and CARINFO_ID = #{carinfoId}")
    List<Carinfo> selCarById(@Param("carinfoId") String carinfoId);

    int updateCar(@Param("plateNumber") String plateNumber, @Param("vehicleClassname") String vehicleClassname, @Param("frameNumber") String frameNumber,
                  @Param("factoryNumber") String factoryNumber, @Param("brandModel") String brandModel, @Param("regTime") String regTime,
                  @Param("examinationTime") String examinationTime, @Param("ratedPassengers") String ratedPassengers, @Param("state") String state,
                  @Param("commentInfo") String commentInfo, @Param("carinfoId") String carinfoId);

    int delCar(@Param("carinfoId") String carinfoId);

    List<Carinfo> searchCar(@Param("plateNumber") String plateNumber, @Param("vehicleClassname") String vehicleClassname, @Param("brandModel") String brandModel,
                            @Param("state") String state,@Param("regTime")String regTime,@Param("examinationTime") String examinationTime, @Param("regbeg") String regbeg, @Param("regend") String regend,@Param("exbeg") String exbeg, @Param("exend") String exend,
                            @Param("page") Integer page, @Param("limit") Integer limit);
    @Select("select * from t_carinfo where STATE = #{state}")
    List<Carinfo> selByState(@Param("state") String state);

    int updateStateByNum(@Param("state") String state, @Param("plateNumber") String plateNumber);

    @Select("select count(*) from t_carinfo")
    int countCar();

    @Select("select count(*) from t_carinfo where STATE = '1' or STATE = '2'")
    int ableCar();

    List<Map<String,Object>>selectDate();

    List<Map<String,Object>>getCarStateDate();

    /**
     * 耗油率
     * @return
     */
    List<Map<String,Object>>getConsumption();

    /**
     * 维修列表
     */
    @Select("select t.VEHICLE_CLASSNAME,c.* from t_carinfo c, t_cartype t where  c.VEHICLE_CLASSNAME = t.CARTYPE_ID and c.STATE = '0' limit  #{page} , #{limit}")
    List<Carinfo> selRepairCarPage(@Param("page") Integer page, @Param("limit") Integer limit);

    int repairCar(@Param("carinfoId") String carinfoId);
}
