package work.aijiu.entity;

/**
 * @ClassName SigarInfoEntity
 * @Description
 * @Author Administrator
 * @Date 2020/4/13 14:16
 * @Version 1.0
 **/
public class SigarInfoEntity {
    private String value;
    private String name;
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public SigarInfoEntity(String value, String name) {
        super();
        this.value = value;
        this.name = name;
    }

    public SigarInfoEntity(){

    }
}
