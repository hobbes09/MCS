package com.mcs.http.client;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.client.utils.URIBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

import com.mcs.global.GlobalConstants;

@Component
public class SmsAgent {
	
	private String responseJson;
	private JSONParser parser = new JSONParser();
	
	private String phoneNumber;
	private String message;
	private String status;
	private String groupId;
	private String smsId;
	private String smsStatus;
	
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getSmsId() {
		return this.smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getSmsStatus() {
		return this.smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public String getResponseJson() {
		return this.responseJson;
	}

	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	
	public JSONParser getParser() {
		return this.parser;
	}

	public void setParser(JSONParser parser) {
		this.parser = parser;
	}

	public String getStatusFromResponseJson(){
		String status = null;
		try{
			Object objectResponseJson = this.parser.parse(this.responseJson);
			JSONObject joResponseJson = (JSONObject)objectResponseJson;
			status = joResponseJson.get("status").toString();
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@" + status);
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
	} 
	
	public boolean extractDetailsFromResponseJson(){
		try{
			Object objectResponseJson = this.parser.parse(this.responseJson);
			JSONObject joResponseJson = (JSONObject)objectResponseJson;
			this.status = joResponseJson.get("status").toString();
			this.message = joResponseJson.get("message").toString();
			
			String data = joResponseJson.get("data").toString();
			objectResponseJson = this.parser.parse(data);
			joResponseJson = (JSONObject)objectResponseJson;
			this.groupId = joResponseJson.get("group_id").toString();
			
			String details = joResponseJson.get("0").toString();
			objectResponseJson = this.parser.parse(details);
			joResponseJson = (JSONObject)objectResponseJson;
			this.smsId = joResponseJson.get("id").toString();
			this.phoneNumber = joResponseJson.get("mobile").toString();
			this.smsStatus = joResponseJson.get("status").toString();
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	public URIBuilder getSmsUri(){
		
		if(this.phoneNumber == null || this.message == null){
			return null;
		}
		
		try {
			this.message = URLEncoder.encode(this.message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		URIBuilder uri = new URIBuilder()
							.setScheme(GlobalConstants.smsBaseScheme)
							.setHost(GlobalConstants.smsBaseUrl)
							.setParameter("api_key", GlobalConstants.smsApiKey)
							.setParameter("sender", GlobalConstants.smsSender)
							.setParameter("to", this.phoneNumber)
							.setParameter("message", this.message)
							.setParameter("method", GlobalConstants.smsMethod);
		
		return uri;
	}

}
