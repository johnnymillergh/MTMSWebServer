package entity;

import java.util.List;

public class PageEntity<T> {
    private int currentPage = 1; // 当前页, 默认显示第一页
    private int rowCount = 10;   // 每页显示的行数(查询返回的行数), 默认每页显示10行
    private int totalCount;      // 总记录数
    private int totalPage;       // 总页数 = 总记录数 / 每页显示的行数  (+ 1)
    private List<T> pageData;       // 分页查询到的数据

    // 返回总页数
    public int getTotalPage() {
        if (totalCount % rowCount == 0) {
            totalPage = totalCount / rowCount;
        } else {
            totalPage = totalCount / rowCount + 1;
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRowCount() {
        return rowCount;
    }

//    public void setPageCount(int rowCount) {
//        this.rowCount = rowCount;
//    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}