package web.charmi.entity;

import java.util.List;

public class Pagination {
    private List<?> Data;
    private Long DataCount;
    private Integer TotalPage;

    public List<?> getData() {
        return Data;
    }

    public void setData(List<?> data) {
        Data=data;
    }

    public Long getDataCount() {
        return DataCount;
    }

    public void setDataCount(Long dataCount) {
        DataCount=dataCount;
    }

    public Integer getTotalPage() {
        return TotalPage;
    }

    public void setTotalPage(Integer totalPage) {
        TotalPage=totalPage;
    }
}
