package com.lucena.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucena.workshopmongo.domain.Post;
import com.lucena.workshopmongo.resources.service.PostService;
import com.lucena.workshopmongo.resources.util.URL;

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

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {

		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);

		return ResponseEntity.ok().body(list);
	}

	
	
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullsearch(
			@RequestParam(value = "text", defaultValue = "") String text, 
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate){

		text = URL.decodeParam(text);
		
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(minDate, new Date());
				
		List<Post> list = service.fullSearch(text, min, max);
		
		return ResponseEntity.ok().body(list);
	}
	
}
