package com.lucena.workshopmongo.resources.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.lucena.workshopmongo.domain.User;
import com.lucena.workshopmongo.repository.UserRepository;

@Service
public class UserService {
	
		@Autowired
		private UserRepository repo;
		
		public List<User> findAll(){
			return repo.findAll();
		}
}
