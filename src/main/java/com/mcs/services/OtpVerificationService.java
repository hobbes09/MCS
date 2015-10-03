package com.mcs.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcs.domains.OtpSmsReport;
import com.mcs.domains.OtpVerificationResponse;
import com.mcs.repositories.OtpSmsReportRepository;

@Service
public class OtpVerificationService {
	
	@Autowired
	OtpSmsReportRepository mOtpSmsReportRepository;
	
	public OtpVerificationResponse verifyOtpAgainstSmsId( String otpCode, String smsId){
		
		if(otpCode == null || smsId == null){
			return null;
		}
		
		OtpSmsReport mOtpSmsReport = mOtpSmsReportRepository.findByAgentSmsId(smsId).get(0);
		
		OtpVerificationResponse mOtpVerificationResponse = new OtpVerificationResponse(); 
		mOtpVerificationResponse.setSmsId(smsId);
		
		if(mOtpSmsReport.getOtpCode().toString().equals(otpCode)){
			mOtpVerificationResponse.setVerified(true);
			mOtpSmsReport.setVerificationStatus("verified");
			mOtpSmsReport.setVerifiedAt(new Date());
			mOtpSmsReportRepository.save(mOtpSmsReport);
		}else{
			mOtpVerificationResponse.setVerified(false);
		}
		
		return mOtpVerificationResponse;
	}

}
