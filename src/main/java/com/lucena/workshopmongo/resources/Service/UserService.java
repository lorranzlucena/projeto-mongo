package com.lucena.workshopmongo.resources.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucena.workshopmongo.domain.User;
import com.lucena.workshopmongo.dto.UserDTO;
import com.lucena.workshopmongo.repository.UserRepository;
import com.lucena.workshopmongo.resources.service.exception.ObjectNotFoundException;

/**
 * classe responsavel por chamar a interface repository para poder acessar o banco
 */
@Service
public class UserService {
	
		@Autowired
		private UserRepository repo;
		
		public List<User> findAll(){
			return repo.findAll();
		}
		
		
		public User findById(String id) {
			
			Optional<User> user = repo.findById(id);
			
			if(!user.isPresent()) {
				throw new ObjectNotFoundException("Objeto não encontrado");
			}
			return user.get();
		}
		
		
		public User insert(User obj) {
			return repo.insert(obj);
		}
		
		
		public User fromDTO(UserDTO objDto) {
			
			return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
		}
		
}
