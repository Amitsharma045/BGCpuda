package com.bitGallon.complaintMgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@ServletComponentScan
@SpringBootApplication
public class BGcomplaintMgmtApplication {
	private static final Logger logger = LoggerFactory.getLogger(BGcomplaintMgmtApplication.class);	
	public static void main(String[] args) {
		logger.info("Starting App");
		SpringApplication.run(BGcomplaintMgmtApplication.class, args);
		logger.info("Application Started");
	}
}

