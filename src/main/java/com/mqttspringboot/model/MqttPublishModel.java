package com.mqttspringboot.model;

import org.springframework.lang.NonNull;

public class MqttPublishModel {

	    @NonNull
	    
	    private String topic;

	    @NonNull
	    
	    private String message;

	    @NonNull
	    private Boolean retained;

	    @NonNull
	    private Integer qos;
	   
	    @NonNull
       private String title;
	    @NonNull
       private String discription;
	    @NonNull
       private String imageUrl;
	    @NonNull
       private String type;
	    @NonNull
       private boolean isClickable;
	    @NonNull
       private String clickEvent;
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Boolean getRetained() {
			return retained;
		}
		public void setRetained(Boolean retained) {
			this.retained = retained;
		}
		public Integer getQos() {
			return qos;
		}
		public void setQos(Integer qos) {
			this.qos = qos;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDiscription() {
			return discription;
		}
		public void setDiscription(String discription) {
			this.discription = discription;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public boolean getIsClickable() {
			return isClickable;
		}
		public void setIsClickable(boolean isClickable) {
			this.isClickable = isClickable;
		}
		public String getClickEvent() {
			return clickEvent;
		}
		public void setClickEvent(String clickEvent) {
			this.clickEvent = clickEvent;
		}
       
	    
	    
}