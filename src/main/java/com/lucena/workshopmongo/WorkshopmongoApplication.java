package com.lucena.workshopmongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lucena.workshopmongo.domain.User;
import com.lucena.workshopmongo.repository.UserRepository;

@SpringBootApplication
public class WorkshopmongoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkshopmongoApplication.class, args);
    }
}