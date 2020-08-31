package work.aijiu.VO;

import lombok.Data;
import work.aijiu.entity.Carinfo;

/**
 * 车辆信息拓展类
 */
@Data
public class CarinfoVO  extends Carinfo {

    /**
     * 注册开始时间
     */
    private String regBeg;

    /**
     * 注册结束时间
     */
    private String regEnd;

    /**
     * 年检开始时间
     */
    private String exBeg;

    /**
     * 年检结束时间
     */
    private String exEnd;
}
