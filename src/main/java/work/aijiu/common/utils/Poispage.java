package work.aijiu.common.utils;

import lombok.Data;
import work.aijiu.entity.Pois;

import java.util.List;
@Data
public class Poispage {
    private int status;
    private int size;
    private int total;
    private List<Pois> pois;

    public Poispage() {
    }

    public Poispage(int status, int size, int total, List<Pois> pois) {
        this.status = status;
        this.size = size;
        this.total = total;
        this.pois = pois;
    }
}
