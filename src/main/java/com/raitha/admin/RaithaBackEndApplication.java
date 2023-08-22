package com.raitha.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.raitha.common.entity","com.raitha.admin.user"})
public class RaithaBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaithaBackEndApplication.class, args);
	}

}
