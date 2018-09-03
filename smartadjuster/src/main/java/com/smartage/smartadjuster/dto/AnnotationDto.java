package com.smartage.smartadjuster.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class AnnotationDto {

	@JsonProperty
	private String mid;
	@JsonProperty
	private Float Score;
	@JsonProperty
	private Float topicality;
	@JsonProperty
	private String description;
	
	
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	
	public Float getScore() {
		return Score;
	}
	public void setScore(Float score) {
		Score = score;
	}
	
	public Float getTopicality() {
		return topicality;
	}
	public void setTopicality(Float topicality) {
		this.topicality = topicality;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "AnnotationDto [mid=" + mid + ", Score=" + Score + ", topicality=" + topicality + ", description="
				+ description + "]";
	}
	
	
		
	

}
