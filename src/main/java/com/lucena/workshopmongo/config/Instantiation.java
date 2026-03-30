package com.lucena.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lucena.workshopmongo.domain.Post;
import com.lucena.workshopmongo.domain.User;
import com.lucena.workshopmongo.dto.AuthorDTO;
import com.lucena.workshopmongo.dto.CommentDTO;
import com.lucena.workshopmongo.repository.PostRepository;
import com.lucena.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria", "maria@gmail.com");
		User alex = new User(null, "Isla ", "alex@gmail.com");
		User bob = new User(null, "lorranz ", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Post post1 = new Post(null,sdf.parse("21/03/2018"),"partiu curitiba"," CHEGAMOS EM CURITBA!!!!", new AuthorDTO(maria));
		Post post2 = new Post(null,sdf.parse("23/03/2018"),"bom dia","acordei feliz", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("boa viagem", sdf.parse("02/02/2020"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("aprovetei a viagem", sdf.parse("02/02/2020"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("tenha um otimo dia!", sdf.parse("02/02/2020"), new AuthorDTO(alex));

		post1.getComments().addAll(Arrays.asList(c1,c2));
		post1.getComments().add(c3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		
		maria.getPost().addAll(Arrays.asList(post1,post2));
		userRepository.save(maria);
		
		
		
	}

}
