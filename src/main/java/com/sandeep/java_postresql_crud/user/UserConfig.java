package com.sandeep.java_postresql_crud.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class UserConfig {
	@Bean
	CommandLineRunner commandLineRunner(UserRepository repository) {
		return args -> {
			User sandeep = new User("Sandeep", "sandeep@gmail.com", LocalDate.of(1998, JANUARY, 1));
			User shekhar = new User("Shekhar", "shekhar@gmail.com", LocalDate.of(2000, JANUARY, 1));

			repository.saveAll(List.of(sandeep, shekhar));
		};
	}

}
