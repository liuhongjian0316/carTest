package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.dao.DrivertypeDao;
import work.aijiu.entity.Drivertype;
import work.aijiu.service.DrivertypeService;

import java.util.List;

/**
 * @ClassName DrivertypeServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/4 10:33
 * @Version 1.0
 **/
@Service
public class DrivertypeServiceImpl implements DrivertypeService {

    @Autowired
    private DrivertypeDao drivertypeDao;

    @Override
    public List<Drivertype> selAll() {
        return drivertypeDao.selAll();
    }
}
