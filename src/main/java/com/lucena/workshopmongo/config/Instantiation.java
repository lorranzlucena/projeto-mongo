package com.lucena.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucena.workshopmongo.domain.User;
import com.lucena.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		
		User maria = new User(null, "Maria", "maria@gmail.com");
		User alex = new User(null, "Alex ", "alex@gmail.com");
		User bob = new User(null, "lorranz ", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
	}

}
