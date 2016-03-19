package com.midai.miya.promotion.model;

import java.util.Date;
import java.io.Serializable;
public class Promotion implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 促销Id
     */
    private String promotionId;
    /**
     * 促销标题
     */
    private String promotionTitle;
    /**
     * 促销内容
     */
    private String promotionText;
    /**
     * 促销排序
     */
    private Integer promotionSort;
    /**
     * 状态 1正常 2取消
     */
    private Integer promotionState;
    /**
     * 封面图片
     */
    private String promotionImgUrl;
    /**
     * 开始日期
     */
    private Date promotionStartTime;
    /**
     * 结束日期
     */
    private Date promotionEffTime;
    /**
     * 录入人
     */
    private String opratorId;
    /**
     * 创建时间
     */
    private Date createTime;
    public String getPromotionId(){
        return promotionId;
    }
    public void setPromotionId(String promotionId){
        this.promotionId=promotionId;
    }
    public String getPromotionTitle(){
        return promotionTitle;
    }
    public void setPromotionTitle(String promotionTitle){
        this.promotionTitle=promotionTitle;
    }
    public String getPromotionText(){
        return promotionText;
    }
    public void setPromotionText(String promotionText){
        this.promotionText=promotionText;
    }
    public Integer getPromotionSort(){
        return promotionSort;
    }
    public void setPromotionSort(Integer promotionSort){
        this.promotionSort=promotionSort;
    }
    public Integer getPromotionState(){
        return promotionState;
    }
    public void setPromotionState(Integer promotionState){
        this.promotionState=promotionState;
    }
    public String getPromotionImgUrl(){
        return promotionImgUrl;
    }
    public void setPromotionImgUrl(String promotionImgUrl){
        this.promotionImgUrl=promotionImgUrl;
    }
    public Date getPromotionStartTime(){
        return promotionStartTime;
    }
    public void setPromotionStartTime(Date promotionStartTime){
        this.promotionStartTime=promotionStartTime;
    }
    public Date getPromotionEffTime(){
        return promotionEffTime;
    }
    public void setPromotionEffTime(Date promotionEffTime){
        this.promotionEffTime=promotionEffTime;
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