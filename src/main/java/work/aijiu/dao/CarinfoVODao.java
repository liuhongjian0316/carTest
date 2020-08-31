package work.aijiu.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import work.aijiu.VO.CarinfoVO;
import work.aijiu.entity.Carinfo;

import java.util.List;

@Repository
public interface CarinfoVODao {

    List<CarinfoVODao> searchCar(@Param("plateNumber") String plateNumber, @Param("vehicleClassname") String vehicleClassname, @Param("brandModel") String brandModel,
                              @Param("state") String state, @Param("regBeg") String regBeg, @Param("regEnd") String regEnd, @Param("exBeg") String exBeg, @Param("exEnd") String exEnd,
                              @Param("page") Integer page, @Param("limit") Integer limit);
}
