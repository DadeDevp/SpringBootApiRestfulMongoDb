package com.davidpires.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.davidpires.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//Retorna uma lista de Posts onde o atributo title contém o text enviado como paramentro
	List<Post> findByTitleContainingIgnoreCase(String text);
}
