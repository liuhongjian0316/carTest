package work.aijiu.service;

import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.Cartype;

import java.util.List;

/**
 * @ClassName CartypeService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/1 21:12
 * @Version 1.0
 **/
public interface CartypeService {

    /**
     * 查询车辆类型
     */
    List<Cartype> selAll();

    /**
     * 车辆分分类信息
     * @param page
     * @param limit
     * @return
     */
    LayuiPageResult selPage(Integer page, Integer limit);

    /**
     * 检查名称是否重复
     */
    public boolean checkNameIsRep(String vehicleClassname);

    /**
     * 检查名称是否重复(编辑)
     */
    public boolean checkNameIsRep2(String vehicleClassname,String oldvehicleClassname);

    /**
     * 增加车辆类型
     */
    public int addCartype(Cartype cartype);

    /**
     * 根据id查询信息（编辑模态框赋值）
     */
    public List<Cartype> selById(String cartypeId);

    /**
     * 修改信息
     */
    public int editCartype(Cartype cartype);

    /**
     * 删除车辆类型
     */
    public int delCartypeById(String cartypeId);
}
