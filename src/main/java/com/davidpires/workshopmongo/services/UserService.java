package com.davidpires.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidpires.workshopmongo.domain.User;
import com.davidpires.workshopmongo.dto.UserDTO;
import com.davidpires.workshopmongo.repository.UserRepository;
import com.davidpires.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
	
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));	
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		//chama o método findById para pesquisar o obj primeiro e se for o caso já lançar a excecao
		findById(id);
		repo.deleteById(id);
	}
	
	//Converter um UserDTO para um User
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail() );
	}
	

}
