package com.mcs.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.Factory;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mcs.domains.OtpSmsReport;
import com.mcs.http.client.GetRequestManager;
import com.mcs.http.client.SmsAgent;
import com.mcs.repositories.OtpSmsReportRepository;

@Service
public class OtpSmsReportService {
	
	@Autowired
	OtpSmsReportRepository mOtpSmsReportRepository;
	
	@Autowired
	private OtpSmsReport mOtpSmsReport;
	
	@Autowired
	private SmsAgent mSmsAgent;
	
	@Autowired
	private GetRequestManager mGetRequestManager;
	
	private Map responseMap;
	private String responseJson;
	
	private String otpCode;
	
	public String getOtpCode() {
		return this.otpCode;
	}

	public Map createSmsReportAndSendOtp(String phoneNumber){
		
		this.responseMap = new HashMap<String, String>();  
		boolean otpSuccess = sendOtpSms(phoneNumber);
		System.out.println("##################"+otpSuccess);
		
		if(otpSuccess){
			this.mSmsAgent.setResponseJson(this.responseJson);
			String otpStatus = this.mSmsAgent.getStatusFromResponseJson(); 
			System.out.println("$$$$$$$$$ :" + otpStatus + "--"+"OK");
			if( otpStatus.equals("OK")){
				boolean flag = this.mSmsAgent.extractDetailsFromResponseJson();
				System.out.println("##################flag =="+flag);
				if(flag){
					this.mOtpSmsReport.setPhoneNumber(this.mSmsAgent.getPhoneNumber());
					this.mOtpSmsReport.setAgentSmsId(this.mSmsAgent.getSmsId());
					this.mOtpSmsReport.setAgentGroupId(this.mSmsAgent.getGroupId());
					this.mOtpSmsReport.setMessageStatus(this.mSmsAgent.getSmsStatus());
					this.mOtpSmsReport.setVerificationStatus("Not Verified");
					this.mOtpSmsReport.setOtpCode(this.getOtpCode());
					this.mOtpSmsReport.setCreatedAt(new Date());
					this.mOtpSmsReport.setUpdatedAt(null);
					this.mOtpSmsReport.setVerifiedAt(null);
					try{						
						this.mOtpSmsReportRepository.save(this.mOtpSmsReport);
					}catch(Exception e){
						e.printStackTrace();
					}
					
					this.responseMap.put("status", "OK");
					this.responseMap.put("Reason", "SMS successfully sent.");
					this.responseMap.put("sms_id", this.mSmsAgent.getSmsId().toString());
					
				}else{
					this.responseMap.put("status", "FAILED");
					this.responseMap.put("Reason", "SMS data parsing error. Agent Error");
					this.responseMap.put("sms_id", "");
				}
				
			}else{
				System.out.println("###########here#######");
				this.responseMap.put("status", "FAILED");
				this.responseMap.put("Reason", "SMS could not be sent. Check request details");
				this.responseMap.put("sms_id", "");
			}
		}else{
			System.out.println("###########here123#######");
			this.responseMap.put("status", "FAILED");
			this.responseMap.put("Reason", "OTP SMS could not be sent. Check request details");
			this.responseMap.put("sms_id", "");
		}
		
		return this.responseMap;
	}
	
	public boolean sendOtpSms(String phoneNumber){
		
		System.out.println("Sending SMS to phone : " + phoneNumber);
		
		String message = getOtpMessage();
		this.mSmsAgent.setPhoneNumber(phoneNumber);
		this.mSmsAgent.setMessage(message);
		
		URIBuilder uri = this.mSmsAgent.getSmsUri();
		
		System.out.println("URI : " + uri.toString());
		
		this.mGetRequestManager.setUri(uri.toString());
		Boolean success = this.mGetRequestManager.sendGetRequestFromUri();
		
		if(success){
			this.responseJson = this.mGetRequestManager.getResponseJson();
			return true;
		}
		return false;
	}

	private String getOtpMessage() {
		return "Test message for OTP - " + generateOTP().toString();
	}

	private String generateOTP() {
		this.otpCode = "123456";
		return this.getOtpCode();
	}
	
	
	
	
	

}
