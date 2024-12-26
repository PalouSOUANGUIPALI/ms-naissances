package com.asp_dev.naissances;

import com.asp_dev.naissances.security.RsaKeys;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(RsaKeys.class)
@SpringBootApplication
public class MsNaissancesApplication implements CommandLineRunner {


    public static void main(String[] args) {
		SpringApplication.run(MsNaissancesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");

	}

}
