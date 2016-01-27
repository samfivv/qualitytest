package com.midai.miya.circle.model;

import java.io.Serializable;
public class RecommendCircle implements Serializable {

    private static final long serialVersionUID = 1L;
    private String recommendCircleId;
    private Integer recommendCircleSort;
    private String circleId;
    private String circleName;
    
    public String getRecommendCircleId(){
        return recommendCircleId;
    }
    public void setRecommendCircleId(String recommendCircleId){
        this.recommendCircleId=recommendCircleId;
    }
    public Integer getRecommendCircleSort(){
        return recommendCircleSort;
    }
    public void setRecommendCircleSort(Integer recommendCircleSort){
        this.recommendCircleSort=recommendCircleSort;
    }
    public String getCircleId(){
        return circleId;
    }
    public void setCircleId(String circleId){
        this.circleId=circleId;
    }
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
}