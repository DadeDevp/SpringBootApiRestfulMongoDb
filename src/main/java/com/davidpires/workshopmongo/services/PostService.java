package com.davidpires.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidpires.workshopmongo.domain.Post;
import com.davidpires.workshopmongo.repository.PostRepository;
import com.davidpires.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	//Fazendo uso dos QueryMethods https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/ 
	public List<Post> findByTitle(String text){
		return repo.searchTitle(text);
		//return repo.findByTitleContainingIgnoreCase(text); //segunda opcao para fazer a mesma pesquisa
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		//A nossa data é um instancia, as operacoes abaixo é para acrescentar um dia na nossa pesquisa
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 *1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
	
	

}
