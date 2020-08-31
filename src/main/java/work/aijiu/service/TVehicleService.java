package work.aijiu.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.dao.TVehicleDao;
import work.aijiu.entity.TDispatch;
import work.aijiu.entity.TVehicle;

import java.util.List;

/**
 * @ClassName TVehicleService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/6 15:15
 * @Version 1.0
 **/
public interface TVehicleService {

    boolean insert(TVehicle tVehicle);

    LayuiPageResult unreturnLayuiPage(String username, Integer page, Integer limit);

    boolean returnCar(TVehicle tVehicle);

    LayuiPageResult returnMsg(Integer page, Integer limit);

}
