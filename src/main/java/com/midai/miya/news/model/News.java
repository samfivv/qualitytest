package com.midai.miya.news.model;

import java.util.Date;
import java.io.Serializable;
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 新闻Id
     */
    private String newsId;
    /**
     * 新闻标题
     */
    private String newsTitle;
    /**
     * 新闻内容
     */
    private String newsText;
    /**
     * 新闻排序
     */
    private Integer newsSort;
    /**
     * 状态 1正常 2取消
     */
    private Integer newsState;
    /**
     * 封面图片
     */
    private String newsImgUrl;
    /**
     * 新闻来源
     */
    private String newsSourceFrom;
    /**
     * 录入人
     */
    private String opratorId;
    /**
     * 创建时间
     */
    private Date createTime;
    public String getNewsId(){
        return newsId;
    }
    public void setNewsId(String newsId){
        this.newsId=newsId;
    }
    public String getNewsTitle(){
        return newsTitle;
    }
    public void setNewsTitle(String newsTitle){
        this.newsTitle=newsTitle;
    }
    public String getNewsText(){
        return newsText;
    }
    public void setNewsText(String newsText){
        this.newsText=newsText;
    }
    public Integer getNewsSort(){
        return newsSort;
    }
    public void setNewsSort(Integer newsSort){
        this.newsSort=newsSort;
    }
    public Integer getNewsState(){
        return newsState;
    }
    public void setNewsState(Integer newsState){
        this.newsState=newsState;
    }
    public String getNewsImgUrl(){
        return newsImgUrl;
    }
    public void setNewsImgUrl(String newsImgUrl){
        this.newsImgUrl=newsImgUrl;
    }
    public String getNewsSourceFrom(){
        return newsSourceFrom;
    }
    public void setNewsSourceFrom(String newsSourceFrom){
        this.newsSourceFrom=newsSourceFrom;
    }
    public String getOpratorId(){
        return opratorId;
    }
    public void setOpratorId(String opratorId){
        this.opratorId=opratorId;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
}