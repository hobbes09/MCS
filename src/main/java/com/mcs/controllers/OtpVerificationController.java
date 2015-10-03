package com.mcs.controllers;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcs.domains.VerificationReport;
import com.mcs.services.OtpVerificationService;
import com.mcs.domains.OtpVerificationResponse;

@RestController
@RequestMapping("otp_verify")
public class OtpVerificationController {
	
	@Autowired
	private OtpVerificationService mOtpVerificationService;
	
	private OtpVerificationResponse mOtpVerificationResponse;
	
	@RequestMapping(value = "/details", method = RequestMethod.POST, produces = {"application/json","application/xml"})
	public OtpVerificationResponse verifyOtpDetails(@RequestBody VerificationReport mVerificationReport){
				
		mOtpVerificationResponse = mOtpVerificationService.verifyOtpAgainstSmsId(mVerificationReport.getOtpCode(), mVerificationReport.getSmsId());
		
		return mOtpVerificationResponse;
	
	}
	
}
