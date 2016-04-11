package com.midai.miya.order.model;

import java.util.Date;
import java.io.Serializable;
public class OrderResult implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private String resultId;
    /**
     * 合同ID
     */
    private String orderId;
    /**
     * 检测完成时间
     */
    private Date resultDate;
    private String resultDateStr;
    /**
     * 附件
     */
    private String resultAttachment;
    /**
     * 录入人
     */
    private String operatorId;
    /**
     * 录入时间
     */
    private Date createTime;
    
    private String resultNote;
    /**
     * 合同编号
     */
    private String orderNo;
	private String userDesc;
    private String userEmail;
    private String memberTel;
    private String materialName;
    private String materialBarcode;
    private String materialBrand;
    private String orderAddress;
    
    public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getMemberTel() {
		return memberTel;
	}
	public void setMemberTel(String memberTel) {
		this.memberTel = memberTel;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialBarcode() {
		return materialBarcode;
	}
	public void setMaterialBarcode(String materialBarcode) {
		this.materialBarcode = materialBarcode;
	}
	public String getMaterialBrand() {
		return materialBrand;
	}
	public void setMaterialBrand(String materialBrand) {
		this.materialBrand = materialBrand;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

    
    public String getResultId(){
        return resultId;
    }
    public void setResultId(String resultId){
        this.resultId=resultId;
    }
    public String getOrderId(){
        return orderId;
    }
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }
    public String getOrderNo(){
        return orderNo;
    }
    public void setOrderNo(String orderNo){
        this.orderNo=orderNo;
    }
    public Date getResultDate(){
        return resultDate;
    }
    public void setResultDate(Date resultDate){
        this.resultDate=resultDate;
    }
    public String getResultAttachment(){
        return resultAttachment;
    }
    public void setResultAttachment(String resultAttachment){
        this.resultAttachment=resultAttachment;
    }
    public String getOperatorId(){
        return operatorId;
    }
    public void setOperatorId(String operatorId){
        this.operatorId=operatorId;
    }
    public Date getCreateTime(){
        return createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime=createTime;
    }
    
	public String getResultDateStr() {
		return resultDateStr;
	}
	public void setResultDateStr(String resultDateStr) {
		this.resultDateStr = resultDateStr;
	}
	public String getResultNote() {
		return resultNote;
	}
	public void setResultNote(String resultNote) {
		this.resultNote = resultNote;
	}
    
    
}