package com.lucena.workshopmongo.resources.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.lucena.workshopmongo.Repository.UserRepository;
import com.lucena.workshopmongo.domain.User;

@Service
public class UserService {
	
		@Autowired
		private UserRepository repo;
		
		public List<User> findAll(){
			return repo.findAll();
		}
}
