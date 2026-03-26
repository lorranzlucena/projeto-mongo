package com.lucena.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucena.workshopmongo.domain.Post;
import com.lucena.workshopmongo.resources.service.PostService;

/*
 * a classe resouce recebe as requisições do front/ postman e se comunica com o service
 * que por sua vez se comunica com repository que acessa o banco.
 * 
 * POSTMAN/FRONTEND
      ↓ requisição HTTP
      
	RESOURCE/CONTROLLER
→ 	Recebe a requisição
→ 	Chama o Service
      ↓

SERVICE
→ Contém as regras de negócio
→ Chama o Repository
      ↓

REPOSITORY
→ Acessa o banco MongoDB
      ↓

MONGODB
→ Retorna os dados
 */
@RestController
@RequestMapping(value = "/posts")
public class PostResources {
	@Autowired
	private PostService service;



	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	

}
