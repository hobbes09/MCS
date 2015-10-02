package com.mcs.domains;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document
public class OtpSmsReport implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -779745911634648260L;
	
	@Id
	private String id;
	private String phoneNumber;
	private String agentSmsId;
	private String agentGroupId;
	private String messageStatus;
	private String verificationStatus;
	private String otpCode;
	private Date createdAt;
	private Date updatedAt;
	private Date verifiedAt;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAgentSmsId() {
		return agentSmsId;
	}

	public void setAgentSmsId(String agentSms_Id) {
		this.agentSmsId = agentSms_Id;
	}

	public String getAgentGroupId() {
		return agentGroupId;
	}

	public void setAgentGroupId(String agentGroupId) {
		this.agentGroupId = agentGroupId;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getOtpCode() {
		return otpCode;
	}

	public void setOtpCode(String otpCode) {
		this.otpCode = otpCode;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getVerifiedAt() {
		return verifiedAt;
	}

	public void setVerifiedAt(Date verifiedAt) {
		this.verifiedAt = verifiedAt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
