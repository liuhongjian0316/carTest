package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.CarDao;
import work.aijiu.entity.Carinfo;
import work.aijiu.service.CarService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CarServiceImpl
 * @Description CarService实现类
 * @Author Administrator
 * @Date 2020/4/1 12:52
 * @Version 1.0
 **/
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao = SpringContextHolder.getBean(CarDao.class);


    @Override
    public LayuiPageResult carPage(Integer page, Integer limit) {

        page = (page-1)*limit;
        List<Carinfo> pageinfo = carDao.selCarPage(page, limit);
        List<Carinfo> allCar = carDao.selAll();
        return new LayuiPageResult(allCar.size(),pageinfo);
    }

    @Override
    public boolean checkCarNum(String plateNumber) {
        return (carDao.SelCount(plateNumber)).size()>0;
    }

    @Override
    public int insCar(Carinfo carinfo) {
        return carDao.insCar(carinfo.getPlateNumber(),carinfo.getVehicleClassname(),carinfo.getFrameNumber(),carinfo.getFactoryNumber(),carinfo.getBrandModel(),
                carinfo.getRegTime(),carinfo.getExaminationTime(),carinfo.getRatedPassengers().toString(),carinfo.getState(),carinfo.getCommentInfo());
    }

    @Override
    public List<Carinfo> getCarById(String carinfoId) {
        return carDao.selCarById(carinfoId);
    }

    @Override
    public int updateCar(Carinfo carinfo) {
        return carDao.updateCar(carinfo.getPlateNumber(),carinfo.getVehicleClassname(),carinfo.getFrameNumber(),carinfo.getFactoryNumber(),
                carinfo.getBrandModel(),carinfo.getRegTime(),carinfo.getExaminationTime(),carinfo.getRatedPassengers().toString(),carinfo.getState(),
                carinfo.getCommentInfo(),carinfo.getCarinfoId().toString());
    }

    @Override
    public int delCar(String carinfoId) {
        return carDao.delCar(carinfoId);
    }

    @Override
    public LayuiPageResult searchCar(Carinfo carinfo,String regbeg,String regend,String exbeg,String exend, Integer page, Integer limit) {

        page = (page-1)*limit;
        List<Carinfo> searchList = carDao.searchCar(carinfo.getPlateNumber(), carinfo.getVehicleClassname(),carinfo.getBrandModel(), carinfo.getState(),carinfo.getRegTime(),carinfo.getExaminationTime(),
                regbeg,regend,exbeg,exend, page, limit);
        List<Carinfo> allCar = carDao.selAll();
        return new LayuiPageResult(allCar.size(),searchList);
    }

    @Override
    public List<Carinfo> selAll() {
        return carDao.selAll();
    }

    @Override
    public List<Carinfo> selByState(String state) {
        return carDao.selByState(state);
    }

    @Override
    public boolean updateStateByNumber(String state, String plateNumber) {
        return carDao.updateStateByNum(state,plateNumber)>0;
    }

    @Override
    public int countCar() {
        return carDao.countCar();
    }

    @Override
    public int ableCar() {
        return carDao.ableCar();
    }

    @Override
    public List<Map<String,Object>> selectDate() {
        return carDao.selectDate();
    }

    @Override
    public List<Map<String, Object>> getCarStateDate() {
        return carDao.getCarStateDate();
    }

    @Override
    public List<Map<String, Object>> getConsumption() {
        return carDao.getConsumption();
    }

    @Override
    public LayuiPageResult selRepairCarPage(Integer page, Integer limit) {
        return new LayuiPageResult(carDao.selAll().size(),carDao.selRepairCarPage(page,limit));
    }

    @Override
    public boolean repairCar(String carinfoId) {
        return carDao.repairCar(carinfoId)>0;
    }

    @Override
    public boolean checkCarNum2(String plateNumber,String oldplateNumber) {
        List<Carinfo> carinfos = carDao.SelCount(oldplateNumber);
        Integer carinfoId1 = carinfos.get(0).getCarinfoId();
        return carDao.SelCount2(plateNumber,String.valueOf(carinfoId1)).size()>0;
    }
}
