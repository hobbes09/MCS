package com.mcs.domains;

// Dummy domain
public class OtpVerificationResponse {
	
	private String smsId;
	private boolean verified;
	
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}

}
