package com.midai.miya.sys.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.Serializable;
public class Partner implements Serializable {
SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final long serialVersionUID = 1L;
    /**
     * 合作伙伴Id
     */
    private String partnerId;
    /**
     * 合作伙伴名称
     */
    private String partnerName;
    /**
     * 合作伙伴链接
     */
    private String partnerUrl;
    /**
     * 合作伙伴图片地址
     */
    private String partnerImgUrl;
    /**
     * 合作伙伴类型 1文字链接  2图片链接
     */
    private Integer partnerType;
    private String partnerTypeStr;
    /**
     * 创建时间
     */
    private Date partnerCreateTime;
    private String partnerCreateTimeStr;
    
    private String queryTimeBegin;
    private String queryTimeEnd;
    public String getPartnerId(){
        return partnerId;
    }
    public void setPartnerId(String partnerId){
        this.partnerId=partnerId;
    }
    public String getPartnerName(){
        return partnerName;
    }
    public void setPartnerName(String partnerName){
        this.partnerName=partnerName;
    }
    public String getPartnerUrl(){
        return partnerUrl;
    }
    public void setPartnerUrl(String partnerUrl){
        this.partnerUrl=partnerUrl;
    }
    public String getPartnerImgUrl(){
        return partnerImgUrl;
    }
    public void setPartnerImgUrl(String partnerImgUrl){
        this.partnerImgUrl=partnerImgUrl;
    }
    public Integer getPartnerType(){
        return partnerType;
    }
    public void setPartnerType(Integer partnerType){
        this.partnerType=partnerType;
    }
    public Date getPartnerCreateTime(){
        return partnerCreateTime;
    }
    public void setPartnerCreateTime(Date partnerCreateTime){
        this.partnerCreateTime=partnerCreateTime;
    }
	public String getQueryTimeBegin() {
		return queryTimeBegin;
	}
	public void setQueryTimeBegin(String queryTimeBegin) {
		this.queryTimeBegin = queryTimeBegin;
	}
	public String getQueryTimeEnd() {
		return queryTimeEnd;
	}
	public void setQueryTimeEnd(String queryTimeEnd) {
		this.queryTimeEnd = queryTimeEnd;
	}
	public String getPartnerTypeStr() {
		 if(partnerType!=null){
			 if(partnerType==1){
					return "文字链接";
				}else{
					return "图片链接";
				}
		 }else{
			 return "";
		 }
	}
	public void setPartnerTypeStr(String partnerTypeStr) {
		this.partnerTypeStr = partnerTypeStr;
	}
	public String getPartnerCreateTimeStr() {
		if(partnerCreateTime!=null){
			return sdf.format(partnerCreateTime);
		}else{
			return null;
		}
	}
	public void setPartnerCreateTimeStr(String partnerCreateTimeStr) {
		this.partnerCreateTimeStr = partnerCreateTimeStr;
	}
}