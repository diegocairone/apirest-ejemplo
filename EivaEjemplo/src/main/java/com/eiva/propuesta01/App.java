package com.eiva.propuesta01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App 
{
	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(App.class);
	}
	
    public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    }
}
