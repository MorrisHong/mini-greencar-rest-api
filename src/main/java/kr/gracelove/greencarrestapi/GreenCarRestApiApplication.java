package kr.gracelove.greencarrestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GreenCarRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenCarRestApiApplication.class, args);
	}

}
