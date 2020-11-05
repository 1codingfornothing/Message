package pojo;

import java.util.List;

public class Page<T> {
    public static final Integer PAGE_SIZE = 10;
    //当前页
    private Integer pageNow;
    //总页
    private Integer pageTotal;
    //每页显示数量
    private Integer pagesize = PAGE_SIZE;
    //总记录数
    private Integer pageTotalSize;
    //每页显示数据
    private List<T> items;

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getPageTotalSize() {
        return pageTotalSize;
    }

    public void setPageTotalSize(Integer pageTotalSize) {
        this.pageTotalSize = pageTotalSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNow=" + pageNow +
                ", pageTotal=" + pageTotal +
                ", pagesize=" + pagesize +
                ", pageTotalSize=" + pageTotalSize +
                ", items=" + items +
                '}';
    }
}
