package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Track)实体类
 *
 * @author makejava
 * @since 2020-08-28 16:02:52
 */
@Data
public class Track implements Serializable {
    private static final long serialVersionUID = 463817400368462365L;

    private String status;
    private int total;
    private String message;
    private List<double[]> pois;
}