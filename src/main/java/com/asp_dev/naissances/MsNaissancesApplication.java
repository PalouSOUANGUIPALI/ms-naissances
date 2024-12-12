package com.asp_dev.naissances;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {ManagementWebSecurityAutoConfiguration.class,
		SecurityAutoConfiguration.class})
public class MsNaissancesApplication implements CommandLineRunner {



    public static void main(String[] args) {
		SpringApplication.run(MsNaissancesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");

	}

}
