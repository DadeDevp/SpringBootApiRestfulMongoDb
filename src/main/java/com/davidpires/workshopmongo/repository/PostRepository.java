package com.davidpires.workshopmongo.repository;

import java.util.Date;
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
	
	//$gte = greater than or equal 
	//$lte = less than or equal
	//Retorna uma lista de posts dada um intervalo de dadas e uma expressao(text) que contenha no titulo ou body ou nos comentarios
	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } },  { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
