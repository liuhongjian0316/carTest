package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.aijiu.dao.InfraccioninfoDao;
import work.aijiu.entity.Infraccioninfo;
import work.aijiu.service.InfraccioninfoService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName InfraccioninfoServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/4 21:46
 * @Version 1.0
 **/
@Service
public class InfraccioninfoServiceImpl implements InfraccioninfoService {

    @Autowired
    private InfraccioninfoDao infraccioninfoDao;

    @Override
    public List<Infraccioninfo> selAll() {
        return infraccioninfoDao.selAll();
    }

    @Override
    public List<Infraccioninfo> selPage(Integer page, Integer limit) {
        return infraccioninfoDao.selpage(page,limit);
    }

    @Override
    public int insert(Infraccioninfo infraccioninfo) {
        return infraccioninfoDao.addInfraccion(infraccioninfo.getPlateNumber(),infraccioninfo.getDrivingLicenseNumber(),infraccioninfo.getInfraccionTime(),
                infraccioninfo.getInfraccionSite(),infraccioninfo.getInfraccionInfo(),infraccioninfo.getResultsOfTransaction(),
                infraccioninfo.getInfraccionCost(),infraccioninfo.getCommentInfo());
    }

    @Override
    public List<Infraccioninfo> selById(String infraccioninfoId) {
        return infraccioninfoDao.selByid(infraccioninfoId);
    }

    @Override
    public int update(Infraccioninfo infraccioninfo) {
        return infraccioninfoDao.update(infraccioninfo.getPlateNumber(),infraccioninfo.getDrivingLicenseNumber(),infraccioninfo.getInfraccionTime(),
                infraccioninfo.getInfraccionSite(),infraccioninfo.getInfraccionInfo(),infraccioninfo.getResultsOfTransaction(),infraccioninfo.getInfraccionCost(),
                infraccioninfo.getCommentInfo(),infraccioninfo.getInfraccioninfoId().toString());
    }

    @Override
    public int delById(String infraccioninfoId) {
        return infraccioninfoDao.delInfraccionById(infraccioninfoId);
    }

    @Override
    public List<Infraccioninfo> search(String plateNumber, String drivingLicenseNumber, String infraccionTime,String begin, String end, Integer page, Integer limit) {
        return infraccioninfoDao.search(plateNumber, drivingLicenseNumber, infraccionTime, begin, end, page, limit);
    }

    @Override
    public Double getPenalty() {
        return infraccioninfoDao.getCost();
    }

    @Override
    public List<Map<String, Object>> selcetTicketAndTimeDate() {
        return infraccioninfoDao.selcetTicketAndTimeDate();
    }

    @Override
    public List<Map<String, Object>> selcetTicketCostAndTimeDate() {
        return infraccioninfoDao.selcetTicketCostAndTimeDate();
    }
}
