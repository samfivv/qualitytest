package com.midai.miya.order.model;

import java.io.Serializable;
import java.util.Date;
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    private String orderId;
    private String orderNo;
    private Date orderDate;
    private String orderNature;
    private String memberId;
    private String orderOffice;
    private String orderAddress;
    private String orderReportDelivery;
    private String memberTel;
    private String memberFax;
    private String testingInstitution;
    private String materialComponents;
    private String materialProducers;
    private String materialName;
    private String materialBarcode;
    private String materialSpec;
    private String materialSubcontract;
    private Date madeDate;
    private String materialLotNo;
    private String materialBrand;
    private String materialQuantity;
    private String materialState;
    private String materialGrade;
    private String materialDispose;
    private String materialAttachment;
    private String testingGist;
    private Date requireDate;
    private Double testingAmout;
    private Integer reportCopies;
    private String orderNote;
    private Integer orderPriority;
    private Integer orderState;
    private Boolean orderClosed;
    private Date orderApproveDate;
    private Date orderPayingDate;
    private Date orderDeliveryDate;
    private Date orderReceivedDate;
    private Date orderTestedDate;
    private Date orderReturnDate;
    private Date orderCloseDate;
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
    public Date getOrderDate(){
        return orderDate;
    }
    public void setOrderDate(Date orderDate){
        this.orderDate=orderDate;
    }
    public String getOrderNature(){
        return orderNature;
    }
    public void setOrderNature(String orderNature){
        this.orderNature=orderNature;
    }
    public String getMemberId(){
        return memberId;
    }
    public void setMemberId(String memberId){
        this.memberId=memberId;
    }
    public String getOrderOffice(){
        return orderOffice;
    }
    public void setOrderOffice(String orderOffice){
        this.orderOffice=orderOffice;
    }
    public String getOrderAddress(){
        return orderAddress;
    }
    public void setOrderAddress(String orderAddress){
        this.orderAddress=orderAddress;
    }
    public String getOrderReportDelivery(){
        return orderReportDelivery;
    }
    public void setOrderReportDelivery(String orderReportDelivery){
        this.orderReportDelivery=orderReportDelivery;
    }
    public String getMemberTel(){
        return memberTel;
    }
    public void setMemberTel(String memberTel){
        this.memberTel=memberTel;
    }
    public String getMemberFax(){
        return memberFax;
    }
    public void setMemberFax(String memberFax){
        this.memberFax=memberFax;
    }
    public String getTestingInstitution(){
        return testingInstitution;
    }
    public void setTestingInstitution(String testingInstitution){
        this.testingInstitution=testingInstitution;
    }
    public String getMaterialComponents(){
        return materialComponents;
    }
    public void setMaterialComponents(String materialComponents){
        this.materialComponents=materialComponents;
    }
    public String getMaterialProducers(){
        return materialProducers;
    }
    public void setMaterialProducers(String materialProducers){
        this.materialProducers=materialProducers;
    }
    public String getMaterialName(){
        return materialName;
    }
    public void setMaterialName(String materialName){
        this.materialName=materialName;
    }
    public String getMaterialBarcode(){
        return materialBarcode;
    }
    public void setMaterialBarcode(String materialBarcode){
        this.materialBarcode=materialBarcode;
    }
    public String getMaterialSpec(){
        return materialSpec;
    }
    public void setMaterialSpec(String materialSpec){
        this.materialSpec=materialSpec;
    }
    public String getMaterialSubcontract(){
        return materialSubcontract;
    }
    public void setMaterialSubcontract(String materialSubcontract){
        this.materialSubcontract=materialSubcontract;
    }
    public Date getMadeDate(){
        return madeDate;
    }
    public void setMadeDate(Date madeDate){
        this.madeDate=madeDate;
    }
    public String getMaterialLotNo(){
        return materialLotNo;
    }
    public void setMaterialLotNo(String materialLotNo){
        this.materialLotNo=materialLotNo;
    }
    public String getMaterialBrand(){
        return materialBrand;
    }
    public void setMaterialBrand(String materialBrand){
        this.materialBrand=materialBrand;
    }
    public String getMaterialQuantity(){
        return materialQuantity;
    }
    public void setMaterialQuantity(String materialQuantity){
        this.materialQuantity=materialQuantity;
    }
    public String getMaterialState(){
        return materialState;
    }
    public void setMaterialState(String materialState){
        this.materialState=materialState;
    }
    public String getMaterialGrade(){
        return materialGrade;
    }
    public void setMaterialGrade(String materialGrade){
        this.materialGrade=materialGrade;
    }
    public String getMaterialDispose(){
        return materialDispose;
    }
    public void setMaterialDispose(String materialDispose){
        this.materialDispose=materialDispose;
    }
    public String getMaterialAttachment(){
        return materialAttachment;
    }
    public void setMaterialAttachment(String materialAttachment){
        this.materialAttachment=materialAttachment;
    }
    public String getTestingGist(){
        return testingGist;
    }
    public void setTestingGist(String testingGist){
        this.testingGist=testingGist;
    }
    public Date getRequireDate(){
        return requireDate;
    }
    public void setRequireDate(Date requireDate){
        this.requireDate=requireDate;
    }
    public Double getTestingAmout(){
        return testingAmout;
    }
    public void setTestingAmout(Double testingAmout){
        this.testingAmout=testingAmout;
    }
    public Integer getReportCopies(){
        return reportCopies;
    }
    public void setReportCopies(Integer reportCopies){
        this.reportCopies=reportCopies;
    }
    public String getOrderNote(){
        return orderNote;
    }
    public void setOrderNote(String orderNote){
        this.orderNote=orderNote;
    }
    public Integer getOrderPriority(){
        return orderPriority;
    }
    public void setOrderPriority(Integer orderPriority){
        this.orderPriority=orderPriority;
    }
    public Integer getOrderState(){
        return orderState;
    }
    public void setOrderState(Integer orderState){
        this.orderState=orderState;
    }
    public Boolean getOrderClosed(){
        return orderClosed;
    }
    public void setOrderClosed(Boolean orderClosed){
        this.orderClosed=orderClosed;
    }
    public Date getOrderApproveDate(){
        return orderApproveDate;
    }
    public void setOrderApproveDate(Date orderApproveDate){
        this.orderApproveDate=orderApproveDate;
    }
    public Date getOrderPayingDate(){
        return orderPayingDate;
    }
    public void setOrderPayingDate(Date orderPayingDate){
        this.orderPayingDate=orderPayingDate;
    }
    public Date getOrderDeliveryDate(){
        return orderDeliveryDate;
    }
    public void setOrderDeliveryDate(Date orderDeliveryDate){
        this.orderDeliveryDate=orderDeliveryDate;
    }
    public Date getOrderReceivedDate(){
        return orderReceivedDate;
    }
    public void setOrderReceivedDate(Date orderReceivedDate){
        this.orderReceivedDate=orderReceivedDate;
    }
    public Date getOrderTestedDate(){
        return orderTestedDate;
    }
    public void setOrderTestedDate(Date orderTestedDate){
        this.orderTestedDate=orderTestedDate;
    }
    public Date getOrderReturnDate(){
        return orderReturnDate;
    }
    public void setOrderReturnDate(Date orderReturnDate){
        this.orderReturnDate=orderReturnDate;
    }
    public Date getOrderCloseDate(){
        return orderCloseDate;
    }
    public void setOrderCloseDate(Date orderCloseDate){
        this.orderCloseDate=orderCloseDate;
    }
}