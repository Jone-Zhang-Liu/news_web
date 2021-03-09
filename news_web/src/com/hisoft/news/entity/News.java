package com.hisoft.news.entity;

import java.util.Date;
import java.util.List;

/**
 * @program: project001
 * @description:
 * @author: wlg
 * @create: 2021-02-03 14:17:01
 **/
public class News {
    private int nid;
    private int ntid;
    private String ntitle;
    private String nauthor;
    private Date ncreateDate;
    private String npicPath;
    private String ncontent;
    private Date nmodifyDate;
    private String nsummary;

    //评论列表
    private List<Comments> commentsList;

    public News(int nid, int ntid, String ntitle, String nauthor, Date ncreateDate, String npicPath, String ncontent, Date nmodifyDate, String nsummary) {
        this.nid = nid;
        this.ntid = ntid;
        this.ntitle = ntitle;
        this.nauthor = nauthor;
        this.ncreateDate = ncreateDate;
        this.npicPath = npicPath;
        this.ncontent = ncontent;
        this.nmodifyDate = nmodifyDate;
        this.nsummary = nsummary;
    }

    public News(int nid, String ntitle, String nauthor) {
        this.nid = nid;
        this.ntitle = ntitle;
        this.nauthor = nauthor;
    }

    public News() {
    }

    public News(int nid, String ntitle, String nauthor, Date ncreateDate) {
        this(nid,ntitle,nauthor);
        this.ncreateDate = ncreateDate;
    }

    public News(int nid, String ntitle, String nauthor, Date ncreateDate, String ncontent) {
        this(nid,ntitle,nauthor,ncreateDate);
        this.ncontent = ncontent;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getNtid() {
        return ntid;
    }

    public void setNtid(int ntid) {
        this.ntid = ntid;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public String getNauthor() {
        return nauthor;
    }

    public void setNauthor(String nauthor) {
        this.nauthor = nauthor;
    }

    public Date getNcreateDate() {
        return ncreateDate;
    }

    public void setNcreateDate(Date ncreateDate) {
        this.ncreateDate = ncreateDate;
    }

    public String getNpicPath() {
        return npicPath;
    }

    public void setNpicPath(String npicPath) {
        this.npicPath = npicPath;
    }

    public String getNcontent() {
        return ncontent;
    }

    public void setNcontent(String ncontent) {
        this.ncontent = ncontent;
    }

    public Date getNmodifyDate() {
        return nmodifyDate;
    }

    public void setNmodifyDate(Date nmodifyDate) {
        this.nmodifyDate = nmodifyDate;
    }

    public String getNsummary() {
        return nsummary;
    }

    public void setNsummary(String nsummary) {
        this.nsummary = nsummary;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public String toString() {
        return "News{" +
                "nid=" + nid +
                ", ntid=" + ntid +
                ", ntitle='" + ntitle + '\'' +
                ", nauthor='" + nauthor + '\'' +
                ", ncreateDate=" + ncreateDate +
                ", npicPath='" + npicPath + '\'' +
                ", ncontent='" + ncontent + '\'' +
                ", nmodifyDate=" + nmodifyDate +
                ", nsummary='" + nsummary + '\'' +
                '}';
    }
}
