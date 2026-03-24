package com.lucena.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lucena.workshopmongo.domain.User;
import com.lucena.workshopmongo.dto.UserDTO;
import com.lucena.workshopmongo.resources.Service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = service.findAll();
		//convertando cada objeto da lista origal para um DTO
		List<UserDTO> listDto = list.stream().map(x->new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}

}
