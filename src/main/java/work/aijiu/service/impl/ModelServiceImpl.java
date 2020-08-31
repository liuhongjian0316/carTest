package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.common.utils.SpringContextHolder;
import work.aijiu.dao.ModelDao;
import work.aijiu.entity.Model;
import work.aijiu.service.ModelService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ModelServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 21:31
 * @Version 1.0
 **/
@Service
public class ModelServiceImpl  implements ModelService {

    @Autowired
    private ModelDao modelDao = SpringContextHolder.getBean(ModelDao.class);

    @Override
    public List<Model> selAll() {
        return modelDao.selAll();
    }

    @Override
    public List<Map<String, Object>> selectDate() {
        return modelDao.selectDate();
    }
}
