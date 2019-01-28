package com.bitGallon.complaintMgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;


@ServletComponentScan
@SpringBootApplication
@EnableScheduling
public class BGcomplaintMgmtApplication {
	private static final Logger logger = LoggerFactory.getLogger(BGcomplaintMgmtApplication.class);	
	public static void main(String[] args) { 
		logger.info("Starting App- by RPSingh 1");
		SpringApplication.run(BGcomplaintMgmtApplication.class, args);
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory(); 
        factory.setBufferRequestBody(false); 
		logger.info("Application Started by rp");
	}
	
}

