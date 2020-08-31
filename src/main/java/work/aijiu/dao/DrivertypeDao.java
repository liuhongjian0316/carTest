package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.Drivertype;

import java.util.List;

/**
 * @ClassName Drivertype
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/4 10:30
 * @Version 1.0
 **/
@Repository
public interface DrivertypeDao {

    /**
     * 查询准假车型
     */
    @Select("select * from t_drivertype")
    List<Drivertype> selAll();
}
