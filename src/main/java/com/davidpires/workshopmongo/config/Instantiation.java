package com.davidpires.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.davidpires.workshopmongo.domain.User;
import com.davidpires.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRespository;
	@Override
	public void run(String... args) throws Exception {
		
		//Limpa os dados das colecoes do banco
		userRespository.deleteAll();
		
		//Cria os dados para inserir na colecao
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRespository.saveAll(Arrays.asList(maria,alex,bob));

	}

}
