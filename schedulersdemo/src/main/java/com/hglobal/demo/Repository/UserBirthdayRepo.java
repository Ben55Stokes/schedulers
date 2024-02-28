package com.hglobal.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hglobal.demo.entity.UserEmail;

@Repository
public interface UserBirthdayRepo extends JpaRepository<UserEmail, Integer> {

	@Query(value = "select u.user_id,up.full_name,u.email,up.date_of_birth from user u inner join user_profile up on u.user_id=up.user_id  inner join birthdayLogs ljrd on\r\n"
			+ " DAY(up.date_of_birth) = DAY(CURDATE()) AND MONTH(up.date_of_birth) = MONTH(CURDATE())and 	Day(ljrd.last_job_run_date)< day(up.date_of_birth)\r\n"
			+ " and month(ljrd.last_job_run_date)<= month(up.date_of_birth)", nativeQuery = true)
	Optional<List<UserEmail>> getUserDetails();
}
