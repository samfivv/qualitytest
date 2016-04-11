package com.midai.miya.order.model;

import java.util.Date;
import java.io.Serializable;
public class ApprovalOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 审核日志
     */
    private String approvalOrderId;
    /**
     * 
     */
    private String orderId;
    /**
     * 
     */
    private String operatorId;
    /**
     * 审批结果  1审核通过 2 审核不通过
     */
    private Integer approvalState;
    /**
     * 
     */
    private String notPassReason;
    /**
     * 
     */
    private Date approvalTime;
    /**
     * 
     */
    private Date approvalCreateTime;
    
    private String userDesc;
    private String userEmail;
    private String orderNo;
    private Date requireDate;
    private Double testingAmout;
    private String memberTel;
    private String materialName;
    private String materialComponents;
    private String materialBarcode;
    private String materialSpec;
    private String materialBrand;
    private Integer orderState;
    private Date orderApproveDate;
    private String orderAddress;
    
    private Date createTimeBegin;
    private Date createTimeEnd;
    private String createTimeBeginStr;
    private String createTimeEndStr;
    

	private Date approvalTimeBegin;
    private Date approvalTimeEnd;
	private String approvalTimeBeginStr;
    private String approvalTimeEndStr;
    
    
    
	public Date getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(Date createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Date getApprovalTimeEnd() {
		return approvalTimeEnd;
	}
	public void setApprovalTimeEnd(Date approvalTimeEnd) {
		this.approvalTimeEnd = approvalTimeEnd;
	}
	public String getApprovalOrderId(){
        return approvalOrderId;
    }
    public void setApprovalOrderId(String approvalOrderId){
        this.approvalOrderId=approvalOrderId;
    }
    public String getOrderId(){
        return orderId;
    }
    public void setOrderId(String orderId){
        this.orderId=orderId;
    }
    public String getOperatorId(){
        return operatorId;
    }
    public void setOperatorId(String operatorId){
        this.operatorId=operatorId;
    }
    public Integer getApprovalState(){
        return approvalState;
    }
    public void setApprovalState(Integer approvalState){
        this.approvalState=approvalState;
    }
    public String getNotPassReason(){
        return notPassReason;
    }
    public void setNotPassReason(String notPassReason){
        this.notPassReason=notPassReason;
    }
    public Date getApprovalTime(){
        return approvalTime;
    }
    public void setApprovalTime(Date approvalTime){
        this.approvalTime=approvalTime;
    }
    public Date getApprovalCreateTime(){
        return approvalCreateTime;
    }
    public void setApprovalCreateTime(Date approvalCreateTime){
        this.approvalCreateTime=approvalCreateTime;
    }
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getRequireDate() {
		return requireDate;
	}
	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}
	public Double getTestingAmout() {
		return testingAmout;
	}
	public void setTestingAmout(Double testingAmout) {
		this.testingAmout = testingAmout;
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
	public String getMaterialComponents() {
		return materialComponents;
	}
	public void setMaterialComponents(String materialComponents) {
		this.materialComponents = materialComponents;
	}
	public String getMaterialBarcode() {
		return materialBarcode;
	}
	public void setMaterialBarcode(String materialBarcode) {
		this.materialBarcode = materialBarcode;
	}
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	public String getMaterialBrand() {
		return materialBrand;
	}
	public void setMaterialBrand(String materialBrand) {
		this.materialBrand = materialBrand;
	}
	public Integer getOrderState() {
		return orderState;
	}
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	public Date getOrderApproveDate() {
		return orderApproveDate;
	}
	public void setOrderApproveDate(Date orderApproveDate) {
		this.orderApproveDate = orderApproveDate;
	}
    public Date getApprovalTimeBegin() {
		return approvalTimeBegin;
	}
	public void setApprovalTimeBegin(Date approvalTimeBegin) {
		this.approvalTimeBegin = approvalTimeBegin;
	} 
	
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getCreateTimeEndStr() {
		return createTimeEndStr;
	}
	public void setCreateTimeEndStr(String createTimeEndStr) {
		this.createTimeEndStr = createTimeEndStr;
	}
	public String getCreateTimeBeginStr() {
		return createTimeBeginStr;
	}
	public void setCreateTimeBeginStr(String createTimeBeginStr) {
		this.createTimeBeginStr = createTimeBeginStr;
	}
	public String getApprovalTimeBeginStr() {
		return approvalTimeBeginStr;
	}
	public void setApprovalTimeBeginStr(String approvalTimeBeginStr) {
		this.approvalTimeBeginStr = approvalTimeBeginStr;
	}
	public String getApprovalTimeEndStr() {
		return approvalTimeEndStr;
	}
	public void setApprovalTimeEndStr(String approvalTimeEndStr) {
		this.approvalTimeEndStr = approvalTimeEndStr;
	}	
	
	
}