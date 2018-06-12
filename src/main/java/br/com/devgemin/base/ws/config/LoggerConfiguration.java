package br.com.devgemin.base.ws.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfiguration {

	@Bean
	public Logger consoleLogger() {
		return LoggerFactory.getLogger("ConsoleLogger");
	}

	@Bean
	public Logger serviceLogger() {
		return LoggerFactory.getLogger("ServiceLogger");
	}
}