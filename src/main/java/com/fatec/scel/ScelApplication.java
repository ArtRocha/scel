package com.fatec.scel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.fatec.scel.controller"})
public class ScelApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScelApplication.class, args);
	}

}
