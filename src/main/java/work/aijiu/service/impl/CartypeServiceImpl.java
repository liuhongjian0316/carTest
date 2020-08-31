package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.CartypeDao;
import work.aijiu.entity.Cartype;
import work.aijiu.service.CartypeService;

import java.util.List;

/**
 * @ClassName CartypeServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 21:12
 * @Version 1.0
 **/
@Service
public class CartypeServiceImpl implements CartypeService {

    @Autowired
    private CartypeDao cartypeDao = SpringContextHolder.getBean(CartypeDao.class);

    @Override
    public List<Cartype> selAll() {
        return cartypeDao.selAll();
    }

    @Override
    public LayuiPageResult selPage(Integer page, Integer limit) {
        page = (page-1)*limit;
        List<Cartype> cartypes = cartypeDao.selAll();
        List<Cartype> pageinfo = cartypeDao.selCartypePage(page, limit);
        return new LayuiPageResult(cartypes.size(),pageinfo);
    }

    @Override
    public boolean checkNameIsRep(String vehicleClassname) {
        return cartypeDao.checkName(vehicleClassname)>0;
    }

    @Override
    public boolean checkNameIsRep2(String vehicleClassname, String oldvehicleClassname) {
        List<Cartype> cartypes = cartypeDao.selbyName(oldvehicleClassname);
        return cartypeDao.checkName2(vehicleClassname,cartypes.get(0).getCartypeId().toString())>0;
    }

    @Override
    public int addCartype(Cartype cartype) {
        return cartypeDao.addCartype(cartype.getVehicleClassname(),cartype.getCommentInfo(),cartype.getState());
    }

    @Override
    public List<Cartype> selById(String cartypeId) {
        return cartypeDao.selById(cartypeId);
    }

    @Override
    public int editCartype(Cartype cartype) {
        return cartypeDao.editCartype(cartype.getVehicleClassname(),cartype.getCommentInfo(),cartype.getState(),cartype.getCartypeId().toString());
    }

    @Override
    public int delCartypeById(String cartypeId) {
        return cartypeDao.delCartypeById(cartypeId);
    }
}
