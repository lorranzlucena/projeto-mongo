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
		
		
		public void delete(String id) {
			findById(id);
			repo.deleteById(id);
		}
		
		public User fromDTO(UserDTO objDto) {
			
			return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
		}
		
		
		public User update(User obj) {
				Optional<User> newObj = repo.findById(obj.getId());
				updateData(newObj, obj);
				return repo.save(newObj.get());//.get extra o User do optional
		}


		private void updateData(Optional<User> newObj, User obj) {

				//O Optional<User> é uma caixa — você não acessa os métodos diretamente nela, precisa abrir a caixa primeiro com .get()!
				newObj.get().setName(obj.getName());
				newObj.get().setEmail(obj.getEmail());
			
			
		}
}
