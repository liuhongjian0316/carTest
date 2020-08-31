package work.aijiu.entity;

import lombok.Data;

@Data
public class Log {
    private Object k;
    private Object v;

    public Log(Object k, Object v) {
        this.k = k;
        this.v = v;
    }
}
