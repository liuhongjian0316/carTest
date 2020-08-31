package work.aijiu.service;

import work.aijiu.VO.CarinfoVO;
import work.aijiu.common.utils.LayuiPageResult;
import work.aijiu.entity.Carinfo;

public interface CarinfoVOService {
    /**
     * 搜索信息
     */
    LayuiPageResult searchCar(CarinfoVO carinfoVO, Integer page, Integer limit);

}
