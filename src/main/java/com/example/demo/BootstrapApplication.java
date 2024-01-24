package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//(exclude=DataSourceAutoConfiguration.class) 집이랑 학원 왔다갔다 넣다뺐다 하기
@SpringBootApplication
@EnableJpaAuditing
public class BootstrapApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootstrapApplication.class, args);
	}

}
