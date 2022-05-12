package com.fundsaccess.ForeignExchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class ForeignExchangeApplication implements CommandLineRunner {

	@Value("${gar.location}")
	private String garLocation;
	
	public static void main(String[] args) {
		//System.out.println("GAR Location from environment variable(main) : " + garLocation);
		SpringApplication.run(ForeignExchangeApplication.class, args);
	}
	
	@Override
	public void run(String args[]) throws Exception
	{
	   System.out.println("GAR Location from environment variable(run) : " + garLocation);
	}
	

}
