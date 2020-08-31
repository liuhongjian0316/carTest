package work.aijiu.service;

import work.aijiu.entity.Model;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ModelService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 21:30
 * @Version 1.0
 **/
public interface ModelService {

    /**
     * 查询所有车辆品牌
     */
    List<Model> selAll();
    /**
     * 统计数据
     */
    List<Map<String,Object>> selectDate();
}
