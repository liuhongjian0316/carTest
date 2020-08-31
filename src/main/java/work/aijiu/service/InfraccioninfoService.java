package work.aijiu.service;


import org.apache.ibatis.annotations.Param;
import work.aijiu.entity.Infraccioninfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InfraccioninfoService
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/4 21:45
 * @Version 1.0
 **/
public interface InfraccioninfoService {

    List<Infraccioninfo> selAll();

    List<Infraccioninfo> selPage(Integer page, Integer limit);

    int insert(Infraccioninfo infraccioninfo);

    List<Infraccioninfo> selById(String infraccioninfoId);

    int update(Infraccioninfo infraccioninfo);

    int delById(String infraccioninfoId);

    List<Infraccioninfo> search(String plateNumber, String drivingLicenseNumber, String infraccionTime,
                                String begin, String end,
                                Integer page, Integer limit);

    Double getPenalty();

    List<Map<String,Object>> selcetTicketAndTimeDate();

    List<Map<String,Object>> selcetTicketCostAndTimeDate();

}
