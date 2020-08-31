package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.dao.TVehicleDao;
import work.aijiu.entity.TVehicle;
import work.aijiu.service.TVehicleService;

/**
 * @ClassName TVehicleServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/6 15:15
 * @Version 1.0
 **/
@Service
public class TVehicleServiceImpl implements TVehicleService {

    @Autowired
    private TVehicleDao tVehicleDao;

    @Override
    public boolean insert(TVehicle tVehicle) {
        return tVehicleDao.insert(tVehicle)>0;
    }

    @Override
    public LayuiPageResult unreturnLayuiPage(String username, Integer page, Integer limit) {
        return new LayuiPageResult(tVehicleDao.selAll().size(),tVehicleDao.layuiPage(username,page,limit));
    }

    @Override
    public boolean returnCar(TVehicle tVehicle) {
        return tVehicleDao.update(tVehicle)>0;
    }

    @Override
    public LayuiPageResult returnMsg(Integer page, Integer limit) {
        return new LayuiPageResult(tVehicleDao.selAll().size(),tVehicleDao.unreturnMsglayuiPage(page,limit));
    }
}
