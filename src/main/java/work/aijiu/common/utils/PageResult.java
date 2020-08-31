package work.aijiu.common.utils;

import java.util.List;

/**
 * @ClassName PageResult
 * @Description 分页封装
 * @Author liuhongjian
 * @Date 2020/3/13 12:18
 * @Version 1.0
 **/
public class PageResult {

    private int page;			// 当前页数
    private Long total;			// 总页数
    private long records;		// 总记录数
    private List<?> rows;		// 每行显示的内容

    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public long getRecords() {
        return records;
    }
    public void setRecords(long records) {
        this.records = records;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
