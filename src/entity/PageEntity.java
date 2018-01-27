package entity;

import java.util.List;

public class PageEntity<T> {
    private int currentPage = 1;
    private final int rowCount = 10;
    private int totalCount;
    private int totalPage;// 总页数 = 总记录数 / 每页显示的行数  (+ 1)
    private List<T> pageData;

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

    public int getTotalPage() {
        if (totalCount % rowCount == 0) {
            return totalPage = totalCount / rowCount;
        } else {
            return totalPage = totalCount / rowCount + 1;
        }
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getPageData() {
        return pageData;
    }

    public void setPageData(List<T> pageData) {
        this.pageData = pageData;
    }
}