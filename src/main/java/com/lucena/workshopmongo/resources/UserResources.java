package com.lucena.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucena.workshopmongo.domain.User;
import com.lucena.workshopmongo.dto.UserDTO;
import com.lucena.workshopmongo.resources.service.UserService;

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
@RequestMapping(value = "/users")
public class UserResources {
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = service.findAll();
		// convertando cada objeto da lista origal para um DTO
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	/**
	 * Inserindo no banco com DTO "dados selecionados para movimentação"
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {

		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto,@PathVariable String id) {

		User obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	
	

}
