package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.Model;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ModelDao
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 21:28
 * @Version 1.0
 **/
@Repository
public interface ModelDao {

    @Select("select * from t_model")
    List<Model> selAll();

    List<Map<String,Object>> selectDate();
}
