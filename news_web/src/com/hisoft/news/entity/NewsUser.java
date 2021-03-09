package com.hisoft.news.entity;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-02 16:32:40
 **/
public class NewsUser {
    private int uid;
    private String uname;
    private String upwd;

    public NewsUser(int uid, String uname, String upwd) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
    }

    public NewsUser(String uname, String upwd) {
        this.uname = uname;
        this.upwd = upwd;
    }

    public NewsUser() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    @Override
    public String toString() {
        return "NewsUser{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                '}';
    }
}
