package com.fundsaccess.ForeignExchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ForeignExchangeApplication implements CommandLineRunner {

	@Value("${gar.location}")
	private String garLocation;
	
	public static void main(String[] args) {
		system.out
		SpringApplication.run(ForeignExchangeApplication.class, args);
	}
	
	@Override
	public void run(String args[]) throws Exception
	{
	   System.out.println("Username from environment variable : " + garLocation);
	}
	

}
