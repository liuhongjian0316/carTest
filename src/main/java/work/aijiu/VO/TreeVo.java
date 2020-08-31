package work.aijiu.VO;

import lombok.Data;

import java.util.List;

@Data
public class TreeVo {
    private String title;
    private List<TreeVo> childern;
}
