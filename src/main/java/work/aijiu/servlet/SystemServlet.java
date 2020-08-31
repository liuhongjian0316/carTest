package work.aijiu.servlet;

import com.alibaba.fastjson.JSON;
import com.sun.management.OperatingSystemMXBean;
import lombok.SneakyThrows;
import work.aijiu.common.utils.CarJSONResult;
import work.aijiu.common.utils.SetHeader;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SystemServlet extends HttpServlet {
    private static OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    //cpu
    public static int cpuLoad() {
        double cpuLoad = osmxb.getSystemCpuLoad();
        int percentCpuLoad = (int) (cpuLoad * 100);
        return percentCpuLoad;

    }
    //内存
    public static int memoryLoad() {
        double totalvirtualMemory = osmxb.getTotalPhysicalMemorySize();
        double freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize();

        double value = freePhysicalMemorySize/totalvirtualMemory;
        int percentMemoryLoad = (int) ((1-value)*100);
        return percentMemoryLoad;

    }
    //cpu
    public static double getProcessCpuLoad() throws Exception {

        MBeanServer mbs    = ManagementFactory.getPlatformMBeanServer();
        ObjectName name    = ObjectName.getInstance("java.lang:type=OperatingSystem");
        AttributeList list = mbs.getAttributes(name, new String[]{ "ProcessCpuLoad" });

        if (list.isEmpty())    { return Double.NaN;}

        Attribute att = (Attribute)list.get(0);
        Double value  = (Double)att.getValue();

        // usually takes a couple of seconds before we get real values
        if (value == -1.0)      {return Double.NaN;}
        // returns a percentage value with 1 decimal point precision
        return ((int)(value * 1000) / 10.0);
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        SetHeader.setHeader(req,resp);
        //通过response对象获得输出流。返回数据用
        PrintWriter out = resp.getWriter();
        if("/car/system/getSystemCpu".equals(req.getRequestURI())){
            List<Map<String,Object>> list = new ArrayList<>();
            Map<String,Object> map =  new HashMap<>();
            map.put("value",getProcessCpuLoad());
            map.put("name","CPU使用率");
            list.add(map);
            out.println(JSON.toJSONString(CarJSONResult.ok(list)));
        }
        if("/car/system/getSystemMemory".equals(req.getRequestURI())){
            List<Map<String,Object>> list = new ArrayList<>();
            Map<String,Object> map =  new HashMap<>();
            map.put("value",memoryLoad());
            map.put("name","内存使用率");
            list.add(map);
            out.println(JSON.toJSONString(CarJSONResult.ok(list)));
        }
        if("/car/system/getSysinfo".equals(req.getRequestURI())){
            Map<String,Object> map = new HashMap<>();
            map.put("sysName","公务用车管理系统");
            map.put("sysVersion","v1.0");
            map.put("systemEnv",System.getProperty("os.name"));
            map.put("jdkVer",System.getProperty("java.specification.version"));
            out.println(JSON.toJSONString(CarJSONResult.ok(map)));
        }
    }
}
