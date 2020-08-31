package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.VO.CarinfoVO;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.CarDao;
import work.aijiu.dao.CarinfoVODao;
import work.aijiu.entity.Carinfo;
import work.aijiu.service.CarinfoVOService;

import java.util.List;

@Service
public class CarinfoVOServiceImpl implements CarinfoVOService {

    @Autowired
    private CarinfoVODao carinfoVODao = SpringContextHolder.getBean(CarinfoVODao.class);
    @Autowired
    private CarDao carDao = SpringContextHolder.getBean(CarDao.class);

    @Override
    public LayuiPageResult searchCar(CarinfoVO carinfoVO, Integer page, Integer limit) {
        page = (page-1)*limit;
        List<CarinfoVODao> carinfoVODaos = carinfoVODao.searchCar(carinfoVO.getPlateNumber(), carinfoVO.getVehicleClassname(), carinfoVO.getBrandModel(), carinfoVO.getState(),
                carinfoVO.getRegBeg(), carinfoVO.getRegEnd(), carinfoVO.getExBeg(), carinfoVO.getExEnd(), page, limit);
        List<Carinfo> allCar = carDao.selAll();
        System.out.println(carinfoVODaos);
        System.out.println(allCar.size());
        return new LayuiPageResult(allCar.size(),carinfoVODaos);
    }
}
