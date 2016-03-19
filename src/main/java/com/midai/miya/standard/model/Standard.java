package com.midai.miya.standard.model;

import java.util.Date;
import java.io.Serializable;
public class Standard implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 标准Id
     */
    private String standardId;
    /**
     * 标准标题
     */
    private String standardTitle;
    /**
     * 标准内容
     */
    private String standardText;
    /**
     * 标准排序
     */
    private Integer standardSort;
    /**
     * 状态 1正常 2取消
     */
    private Integer standardState;
    /**
     * 封面图片
     */
    private String standardImgUrl;
    /**
     * 录入人
     */
    private String opratorId;
    /**
     * 创建时间
     */
    private Date createTime;
    public String getStandardId(){
        return standardId;
    }
    public void setStandardId(String standardId){
        this.standardId=standardId;
    }
    public String getStandardTitle(){
        return standardTitle;
    }
    public void setStandardTitle(String standardTitle){
        this.standardTitle=standardTitle;
    }
    public String getStandardText(){
        return standardText;
    }
    public void setStandardText(String standardText){
        this.standardText=standardText;
    }
    public Integer getStandardSort(){
        return standardSort;
    }
    public void setStandardSort(Integer standardSort){
        this.standardSort=standardSort;
    }
    public Integer getStandardState(){
        return standardState;
    }
    public void setStandardState(Integer standardState){
        this.standardState=standardState;
    }
    public String getStandardImgUrl(){
        return standardImgUrl;
    }
    public void setStandardImgUrl(String standardImgUrl){
        this.standardImgUrl=standardImgUrl;
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