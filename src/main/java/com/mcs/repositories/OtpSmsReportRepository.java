package com.mcs.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mcs.domains.OtpSmsReport;

public interface OtpSmsReportRepository extends MongoRepository<OtpSmsReport, String>{
	
	public OtpSmsReport findByAgentSmsId(String agentSmsId);
	
}
