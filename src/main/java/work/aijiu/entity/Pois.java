package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Pois)实体类
 *
 * @author makejava
 * @since 2020-08-28 16:01:50
 */
@Data
public class Pois implements Serializable {
    private static final long serialVersionUID = -82623402877882250L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 市
    */
    private String city;
    /**
    * 创建时间
    */
    private String createTime;
    /**
    * 区
    */
    private String district;
    /**
    * 时间戳
    */
    private String locTime;
    /**
    * 位置经纬度（二维数组）
    */
    private String location;
    /**
    * 修改时间
    */
    private String modifyTime;
    /**
    * 省
    */
    private String province;
    /**
    * 标题
    */
    private String title;
    /**
    * 通俗的讲是车牌号
    */
    private String trackName;
    /**
    * 城市id
    */
    private String cityId;
    /**
    * 轨迹id 后面统计轨迹的id
    */
    private String trackId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocTime() {
        return locTime;
    }

    public void setLocTime(String locTime) {
        this.locTime = locTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

}