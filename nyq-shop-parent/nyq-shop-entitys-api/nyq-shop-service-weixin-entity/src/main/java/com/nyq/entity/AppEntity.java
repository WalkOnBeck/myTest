package com.nyq.entity;

/**
 * 
 * @author 微信应用实体类
 *
 */

public class AppEntity {
	public AppEntity() {
		
	}
	public AppEntity(String appId, String appName) {
		super();
		this.appId = appId;
		this.appName = appName;
	}
	private String appId;
	private String appName;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
}
