package work.aijiu.service;

import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.Carinfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName CarService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 12:51
 * @Version 1.0
 **/
public interface CarService {

    LayuiPageResult carPage(Integer page, Integer limit);

    /**
     * 车牌号是否重复（true 重复，false 不重复）
     * @param plateNumber
     * @return
     */
    boolean checkCarNum(String plateNumber);

    /**
     * 添加车辆
     */
    int insCar(Carinfo carinfo);

    /**
     * 修改模态框赋值
     */
    List<Carinfo> getCarById(String carinfoId);

    /**
     * 修改车辆信息
     * @param carinfo
     * @return
     */
    int updateCar(Carinfo carinfo);

    /**
     * 删除车辆信息
     */
    int delCar(String carinfoId);

    /**
     * 搜索信息
     */
    LayuiPageResult searchCar(Carinfo carinfo, String regbeg,String regend,String exbeg,String exend,Integer page, Integer limit);

    /**
     * 下拉框赋值
     */
    List<Carinfo> selAll();

    /**
     * 根据状态查询测含量
     */
    List<Carinfo> selByState(String state);

    /**
     * 根据车牌号修改车辆状态
     */
    boolean updateStateByNumber(String state, String plateNumber);

    /**
     * 统计总车辆
     * @return
     */
    int countCar();

    /**
     * 统计有效车辆
     */
    int ableCar();

    /**
     * echarts 数据统计
     */
    List<Map<String,Object>> selectDate();


    /**
     * echarts 车辆状况分布情况
     */
    List<Map<String,Object>> getCarStateDate();

    /**
     * echarts 耗油率
     * @return
     */
    List<Map<String,Object>> getConsumption();

    /**
     * 需要维修table 分页
     */
    LayuiPageResult selRepairCarPage(Integer page, Integer limit);

    /**
     * 修车
     */
    boolean repairCar(String carinfoId);

    /**
     * 修改时的查重
     */
    boolean checkCarNum2(String plateNumber,String oldplateNumber);
}
