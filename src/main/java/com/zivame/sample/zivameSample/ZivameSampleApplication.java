package com.zivame.sample.zivameSample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.zivame.sample.*" })
@EntityScan(basePackages = { "com.zivame.sample.model" })
public class ZivameSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZivameSampleApplication.class, args);
	}

}
