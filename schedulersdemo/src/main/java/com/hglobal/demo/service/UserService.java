package com.hglobal.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hglobal.demo.Repository.LastJobRunDateRepo;
import com.hglobal.demo.Repository.LogRepository;
import com.hglobal.demo.scheduler.Scheduler;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	Scheduler scheduler;
	
	
	@Transactional
	public String sendEmail() {
		try {
			scheduler.scheduleBirthdayWishes();
		}catch (Exception e) {
			log.error("error inside UserService.sendEmail");
		}
		return "Success";
	}
	
}
