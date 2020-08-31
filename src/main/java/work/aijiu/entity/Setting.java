package work.aijiu.entity;

import java.io.Serializable;

/**
 * (Setting)实体类
 *
 * @author makejava
 * @since 2020-08-30 09:39:49
 */
public class Setting implements Serializable {
    private static final long serialVersionUID = 589576723370183728L;
    /**
    *  
    */
    private Integer id;
    /**
    * 日志保存本地位置
    */
    private String saveurl;
    /**
    * 从redis读取日志保存到本地的位置
    */
    private String serveurl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSaveurl() {
        return saveurl;
    }

    public void setSaveurl(String saveurl) {
        this.saveurl = saveurl;
    }

    public String getServeurl() {
        return serveurl;
    }

    public void setServeurl(String serveurl) {
        this.serveurl = serveurl;
    }

}