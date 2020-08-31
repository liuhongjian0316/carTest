package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜地表
 */
@Data
public class Rm implements Serializable {

    /**
     * 角色id
     */
    private String roleid;

    /**
     * 菜单id
     */
    private String menuid;
}
