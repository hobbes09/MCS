package com.mcs.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.mcs.services.OtpSmsReportService;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("otp_generator")
public class OtpGeneratorController {
	
	@Autowired
	private OtpSmsReportService mOtpSmsReportService;
	
	private Map response;
	private String jsonResponse;

	// http://127.0.0.1:8080/otp_generator/sms?phone_number=7829459269
	@RequestMapping("/sms")
	public String generateOtpSms(HttpServletRequest request){
		Enumeration<String> enumString = request.getParameterNames();
		String key = enumString.nextElement(); 
		String phoneNumber = request.getParameter(key); 
		
		System.out.println("Phone number is : " + phoneNumber);
		
		if(phoneNumber.length() == 10){
			this.response = this.mOtpSmsReportService.createSmsReportAndSendOtp(phoneNumber);
		}else{
			this.response = new HashMap<String, String>();
			this.response.put("status", "FAILED");
			this.response.put("Reason", "Invalid phone number");
			this.response.put("sms_id", "");
		}
		
		// convert this.response to json string
		JSONObject jsonObjectResponse = new JSONObject();
		jsonObjectResponse.putAll(this.response);
		
		this.jsonResponse = jsonObjectResponse.toJSONString();
		
		return this.jsonResponse;
	}
	
	
}
