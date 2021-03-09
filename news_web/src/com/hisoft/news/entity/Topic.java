package com.hisoft.news.entity;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-03 17:07:55
 **/
public class Topic {
    private int tid;
    private String tname;

    public Topic() {
    }

    public Topic(int tid, String tname) {
        this.tid = tid;
        this.tname = tname;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
}
