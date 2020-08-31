package work.aijiu.common.utils;

import lombok.Data;
import work.aijiu.entity.Menu;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description 树封装
 * @Author liuhongjian
 * @Date 2020/3/13 12:18
 * @Version 1.0
 **/
@Data
public class TreeData {

    private int code;			// 0 成功
    private String msg;			// 信息
    private List<?> data;		// 数据


    public TreeData() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public TreeData(int code, String msg, List<?> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
