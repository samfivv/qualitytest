package com.midai.miya.production.model;

import java.util.Date;
import java.io.Serializable;
public class Production implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 作品Id
     */
    private String productionId;
    private String interestId;
    /**
     * 标题
     */
    private String productionTitle;
    /**
     * 标签
     */
    private String productionTag;
    /**
     * 材质
     */
    private String productionTexture;
    /**
     * 描述
     */
    private String productionDesc;
    /**
     * 定制次数
     */
    private Integer productionCount;
    /**
     * 单价
     */
    private Double productionPrice;
    private String productionUnit;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 浏览次数
     */
    private Integer browseCount;
    /**
     * 分数
     */
    private Double productionScore;
    /**
     * 参与评分人数
     */
    private Integer productionGradeCount;
    /**
     * 封面大图
     */
    private String productionBigImgUrl;
    /**
     * 封面小图
     */
    private String productionSamllImgUrl;
    /**
     * 1 草稿 2上架 3下架 4删除
     */
    private Integer productionState;
    private Integer productionType;
    private Integer productionPredictFinishDay;
    private String productionRule;
    public String getProductionId(){
        return productionId;
    }
    public void setProductionId(String productionId){
        this.productionId=productionId;
    }
    public String getInterestId(){
        return interestId;
    }
    public void setInterestId(String interestId){
        this.interestId=interestId;
    }
    public String getProductionTitle(){
        return productionTitle;
    }
    public void setProductionTitle(String productionTitle){
        this.productionTitle=productionTitle;
    }
    public String getProductionTag(){
        return productionTag;
    }
    public void setProductionTag(String productionTag){
        this.productionTag=productionTag;
    }
    public String getProductionTexture(){
        return productionTexture;
    }
    public void setProductionTexture(String productionTexture){
        this.productionTexture=productionTexture;
    }
    public String getProductionDesc(){
        return productionDesc;
    }
    public void setProductionDesc(String productionDesc){
        this.productionDesc=productionDesc;
    }
    public Integer getProductionCount(){
        return productionCount;
    }
    public void setProductionCount(Integer productionCount){
        this.productionCount=productionCount;
    }
    public String getProductionUnit(){
        return productionUnit;
    }
    public void setProductionUnit(String productionUnit){
        this.productionUnit=productionUnit;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime=updateTime;
    }
    public Integer getBrowseCount(){
        return browseCount;
    }
    public void setBrowseCount(Integer browseCount){
        this.browseCount=browseCount;
    }
    public Integer getProductionGradeCount(){
        return productionGradeCount;
    }
    public void setProductionGradeCount(Integer productionGradeCount){
        this.productionGradeCount=productionGradeCount;
    }
    public String getProductionBigImgUrl(){
        return productionBigImgUrl;
    }
    public void setProductionBigImgUrl(String productionBigImgUrl){
        this.productionBigImgUrl=productionBigImgUrl;
    }
    public String getProductionSamllImgUrl(){
        return productionSamllImgUrl;
    }
    public void setProductionSamllImgUrl(String productionSamllImgUrl){
        this.productionSamllImgUrl=productionSamllImgUrl;
    }
    public Integer getProductionState(){
        return productionState;
    }
    public void setProductionState(Integer productionState){
        this.productionState=productionState;
    }
    public Integer getProductionType(){
        return productionType;
    }
    public void setProductionType(Integer productionType){
        this.productionType=productionType;
    }
    public Integer getProductionPredictFinishDay(){
        return productionPredictFinishDay;
    }
    public void setProductionPredictFinishDay(Integer productionPredictFinishDay){
        this.productionPredictFinishDay=productionPredictFinishDay;
    }
    public String getProductionRule(){
        return productionRule;
    }
    public void setProductionRule(String productionRule){
        this.productionRule=productionRule;
    }
	public Double getProductionPrice() {
		return productionPrice;
	}
	public void setProductionPrice(Double productionPrice) {
		this.productionPrice = productionPrice;
	}
	public Double getProductionScore() {
		return productionScore;
	}
	public void setProductionScore(Double productionScore) {
		this.productionScore = productionScore;
	}
}