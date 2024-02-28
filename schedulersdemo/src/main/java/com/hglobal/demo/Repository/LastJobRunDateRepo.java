package com.hglobal.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.hglobal.demo.entity.LastJobRunDate;

public interface LastJobRunDateRepo extends JpaRepository<LastJobRunDate,Integer> {
	@Modifying
	@Query(value="update birthdayLogs set last_job_run_date=CURDATE() where id=1", nativeQuery = true)
	void updateLastJobRunDate();
}
