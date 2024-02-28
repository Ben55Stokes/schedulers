package com.hglobal.demo.scheduler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hglobal.demo.Repository.LastJobRunDateRepo;
import com.hglobal.demo.Repository.LogRepository;
import com.hglobal.demo.Repository.UserBirthdayRepo;
import com.hglobal.demo.entity.BirthdayWishesEntity;
import com.hglobal.demo.entity.UserEmail;
import com.hglobal.demo.mail.EmailService;
import com.hglobal.demo.utility.Utility;

import lombok.extern.slf4j.Slf4j;

@Component
@EnableScheduling
@Slf4j
public class Scheduler {

	@Autowired
	UserBirthdayRepo birthdayRepo;
	@Autowired
	EmailService emailService;

	@Autowired
	LogRepository loggingRepo;

	@Autowired
	LastJobRunDateRepo lastJobRunDateRepo;
	
	@Scheduled(cron = Utility.WISHES_TIME)
	public void scheduleBirthdayWishes() {
		try {
			Optional<List<UserEmail>> emails = birthdayRepo.getUserDetails();
			for (UserEmail userEmail : emails.get()) {
				emailService.sendSimpleMessage(userEmail.getEmail(), Utility.EMAIL_SUBJECT,
						Utility.content(userEmail.getFullName(), Utility.ageCalculator(userEmail.getDateOfBirth())));
			}
			
			//saves records of the users who got emails
			saveBirthdayWishesEntities(emails.get());
			
			//if there's a breakdown today then the email will be sent tomorrow to the users who don't receive today
			lastJobRunDateRepo.updateLastJobRunDate();
			
			log.info("sent emails to "+ emails.get().stream().map(v->v.getFullName()));
		} catch (Exception e) {
			log.error("error inside Scheduler.scheduleBirthdayWishes", e.getMessage());
		}
	}
	
	public List<BirthdayWishesEntity> saveBirthdayWishesEntities(List<UserEmail> userEmails){
		List<BirthdayWishesEntity> entitiesRTRN=null;
		try {
			List<BirthdayWishesEntity> birthdayWishesEntities= new ArrayList<>();
			for (UserEmail email : userEmails) {
				BirthdayWishesEntity birthdayWishesEntity=new BirthdayWishesEntity();
				birthdayWishesEntity.setDate(LocalDate.now());
				birthdayWishesEntity.setUserEmail(email.getEmail());
				birthdayWishesEntities.add(birthdayWishesEntity);
				entitiesRTRN= loggingRepo.saveAll(birthdayWishesEntities);
			}
		}catch (Exception e) {
			log.error("error inside saveBirthdayWishesEntities ", e.getMessage());
			e.printStackTrace();
		}
		return entitiesRTRN;
	}
}
