package com.hglobal.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hglobal.demo.entity.BirthdayWishesEntity;

@Repository
public interface LogRepository extends JpaRepository<BirthdayWishesEntity, Integer> {

}