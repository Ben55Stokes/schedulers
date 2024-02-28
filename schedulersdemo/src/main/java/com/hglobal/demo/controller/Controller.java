package com.hglobal.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hglobal.demo.service.UserService;

@RestController
public class Controller {
	@Autowired
	UserService service;

	@GetMapping("/wishes")
	public String sendWishes() {
		return service.sendEmail();

	}
}
