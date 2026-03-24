package com.lucena.workshopmongo.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lucena.workshopmongo.domain.User;

@Repository
public interface UserRepository  extends MongoRepository<User, String>{

}
