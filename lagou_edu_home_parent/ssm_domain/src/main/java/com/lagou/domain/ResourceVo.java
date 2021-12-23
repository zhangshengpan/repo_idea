package com.lagou.domain;

public class ResourceVo {

    private Integer currentPage;    //当前页
    private Integer pageSize;   //每页显示条数
    private String name;    //资源名称
    private Integer categoryId; //
    private String url;

    @Override
    public String toString() {
        return "ResourceVo{" +
                "currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", url='" + url + '\'' +
                '}';
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
