package com.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Referentni sajt je bio www.concretepage.com/spring-boot/spring-boot-jdbc-example
// ali iz nekog razloga voltDB baca greske kada se radi sa jdbcTemplate

@SpringBootApplication
@ComponentScan({"com.work.dao", "com.work.dao.impl", "com.work.controller",
	"com.work.config", "com.work.service"})
public class ZadatakApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZadatakApplication.class, args);
	}
}
