package com.arnab.webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WebserviceApplication {

	static final int TIMEOUT = 2000;
	public static void main(String[] args) {

		SpringApplication.run(WebserviceApplication.class, args);

	}
	@Bean
	public RestTemplate restTemplate(){

		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(TIMEOUT);
		requestFactory.setReadTimeout(TIMEOUT);

		return new RestTemplate(requestFactory);
	}
}
