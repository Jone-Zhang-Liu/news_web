package com.hisoft.news.entity;

import java.util.List;


public class Page {
    private int currPageNo = 1; // 当前页码
    private int pageSize = 10;    // 页面大小，即每页显示记录数
    private int totalCount;          // 记录总数
    private int totalPageCount; // 总页数
    List<News> newsList;         // 每页新闻集合

    public int getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        if(currPageNo < 1){
            this.currPageNo = 1;
        }else if(currPageNo > totalPageCount){
            this.currPageNo = totalPageCount;
        }else{
            this.currPageNo = currPageNo;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if(totalCount > 0){
            this.totalCount = totalCount;
            //同时设置总页数
            this.totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        }
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currPageNo=" + currPageNo +
                ", pageSize=" + pageSize +
                ", totalCount=" + totalCount +
                ", totalPageCount=" + totalPageCount +
                ", newsList=" + newsList +
                '}';
    }
}
