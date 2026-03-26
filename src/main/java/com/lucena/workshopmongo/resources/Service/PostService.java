package com.lucena.workshopmongo.resources.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucena.workshopmongo.domain.Post;
import com.lucena.workshopmongo.repository.PostRepository;
import com.lucena.workshopmongo.resources.service.exception.ObjectNotFoundException;

/**
 * classe responsavel por chamar a interface repository para poder acessar o banco
 */
@Service
public class PostService {
	
		@Autowired
		private PostRepository repo;

		public Post findById(String id) {
			
			Optional<Post> user = repo.findById(id);
			
			if(!user.isPresent()) {
				throw new ObjectNotFoundException("Objeto não encontrado");
			}
			return user.get();
		}
		
	
}
