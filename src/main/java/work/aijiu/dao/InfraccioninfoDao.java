package work.aijiu.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import work.aijiu.entity.Infraccioninfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Infraccioninfo
 * @Description TODO
 * @Author Administrator
 * @Date 2020/4/4 21:30
 * @Version 1.0
 **/
@Repository
public interface InfraccioninfoDao {
    /**
     * 查询所欲
     */
    @Select("select * from t_infraccioninfo")
    List<Infraccioninfo> selAll();
    /**
     * layui 分页查询
     */
    @Select("select * from t_infraccioninfo limit #{page},#{limit}")
    List<Infraccioninfo> selpage(@Param("page") Integer page, @Param("limit") Integer limit);

    /**
     * 录入罚单
     */
    int addInfraccion(@Param("plateNumber") String plateNumber, @Param("drivingLicenseNumber") String drivingLicenseNumber, @Param("infraccionTime") String infraccionTime,
                      @Param("infraccionSite") String infraccionSite, @Param("infraccionInfo") String infraccionInfo, @Param("resultsOfTransaction") String resultsOfTransaction,
                      @Param("infraccionCost") Double infraccionCost, @Param("commentInfo") String commentInfo);
    /**
     * 根据id查询罚单(编辑模态框赋值)
     */
    @Select("select * from t_infraccioninfo where INFRACCIONINFO_ID = #{infraccioninfoId}")
    List<Infraccioninfo> selByid(@Param("infraccioninfoId") String infraccioninfoId);

    /**
     * 修改罚单信息
     */
    int update(@Param("plateNumber") String plateNumber, @Param("drivingLicenseNumber") String drivingLicenseNumber, @Param("infraccionTime") String infraccionTime,
               @Param("infraccionSite") String infraccionSite, @Param("infraccionInfo") String infraccionInfo, @Param("resultsOfTransaction") String resultsOfTransaction,
               @Param("infraccionCost") Double infraccionCost, @Param("commentInfo") String commentInfo, @Param("infraccioninfoId") String infraccioninfoId);

    /**
     * 删除翻单
     */
    int delInfraccionById(@Param("infraccioninfoId") String infraccioninfoId);
    /**
     * 查询
     */
    List<Infraccioninfo> search(@Param("plateNumber") String plateNumber, @Param("drivingLicenseNumber") String drivingLicenseNumber,
                                @Param("infraccionTime") String infraccionTime,
                                @Param("begin") String beg,
                                @Param("end") String end,
                                @Param("page") Integer page, @Param("limit") Integer limit);

    @Select("select sum(INFRACCION_COST) from t_infraccioninfo")
    Double getCost();

    List<Map<String,Object>> selcetTicketAndTimeDate();

    List<Map<String,Object>> selcetTicketCostAndTimeDate();


}
