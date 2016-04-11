package com.midai.miya.brand.model;

import java.util.Date;
import java.io.Serializable;
public class Brand implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 品牌Id
     */
    private String brandId;
    /**
     * 品牌标题
     */
    private String brandTitle;
    /**
     * 品牌内容
     */
    private String brandText;
    /**
     * 品牌排序
     */
    private Integer brandSort;
    /**
     * 状态 1正常 2取消
     */
    private Integer brandState;
    /**
     * 封面图片
     */
    private String brandImgUrl;
    /**
     * 生产企业
     */
    private String brandEnterprise;
    /**
     * 会员ID
     */
    private String userId;
    /**
     * 录入人
     */
    private String opratorId;
    /**
     * 创建时间
     */
    private Date createTime;
 
    //会员名称
    private String userDesc;
    
    public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getBrandId(){
        return brandId;
    }
    public void setBrandId(String brandId){
        this.brandId=brandId;
    }
    public String getBrandTitle(){
        return brandTitle;
    }
    public void setBrandTitle(String brandTitle){
        this.brandTitle=brandTitle;
    }
    public String getBrandText(){
        return brandText;
    }
    public void setBrandText(String brandText){
        this.brandText=brandText;
    }
    public Integer getBrandSort(){
        return brandSort;
    }
    public void setBrandSort(Integer brandSort){
        this.brandSort=brandSort;
    }
    public Integer getBrandState(){
        return brandState;
    }
    public void setBrandState(Integer brandState){
        this.brandState=brandState;
    }
    public String getBrandImgUrl(){
        return brandImgUrl;
    }
    public void setBrandImgUrl(String brandImgUrl){
        this.brandImgUrl=brandImgUrl;
    }
    public String getBrandEnterprise(){
        return brandEnterprise;
    }
    public void setBrandEnterprise(String brandEnterprise){
        this.brandEnterprise=brandEnterprise;
    }
    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId=userId;
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