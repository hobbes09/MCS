package com.mcs.domains;

// A Dummy Domain for getting POST requests
public class VerificationReport {

	private String otpCode;
	private String smsId;

	public String getOtpCode() {
		return otpCode;
	}
	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

}
