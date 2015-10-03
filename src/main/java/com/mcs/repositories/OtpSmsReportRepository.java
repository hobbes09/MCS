package com.mcs.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mcs.domains.OtpSmsReport;

public interface OtpSmsReportRepository extends MongoRepository<OtpSmsReport, String>{
	
	public List<OtpSmsReport> findByAgentSmsId(String agentSmsId);
	
}
