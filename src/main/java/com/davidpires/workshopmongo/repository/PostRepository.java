package com.davidpires.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.davidpires.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	//https://www.mongodb.com/docs/manual/reference/operator/query/regex/
	//{ <field>: { $regex: /pattern/, $options: '<options>' } }
	// ?0 = text é o primeiro parametro do método
	// i = ignorar case sensitive
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
	//Usando query methods
	//Retorna uma lista de Posts onde o atributo title contém o text enviado como paramentro
	List<Post> findByTitleContainingIgnoreCase(String text);
}
