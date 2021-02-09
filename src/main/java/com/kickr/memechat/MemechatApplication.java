package com.kickr.memechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemechatApplication {
	private static final Logger logger = LoggerFactory.getLogger(MemechatApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(MemechatApplication.class, args);
	}

}
