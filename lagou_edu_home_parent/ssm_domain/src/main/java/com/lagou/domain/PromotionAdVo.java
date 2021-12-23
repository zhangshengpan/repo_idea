package com.lagou.domain;

public class PromotionAdVo {

    private Integer currentPage;    //当前页
    private Integer pageSize;       //每页显示的条数


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
