package com.shop24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.shop24.util.DataSeeder;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Shop24ApiApplication {

	
	  @Autowired
	    private DataSeeder dataSeeder;
	public static void main(String[] args) {
		SpringApplication.run(Shop24ApiApplication.class, args);
	}

	
	 @PostConstruct
	    public void init() {
	        dataSeeder.seedData();
	    }
}
