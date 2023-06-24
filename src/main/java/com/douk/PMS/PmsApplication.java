package com.douk.PMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PmsApplication {

	@GetMapping("api/auth/greeting")
	public String greeting(){
		return "Hello from KK";
	}

	public static void main(String[] args) {
		SpringApplication.run(PmsApplication.class, args);
	}

}
