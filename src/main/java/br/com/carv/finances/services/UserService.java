package br.com.carv.finances.services;

import java.util.Optional;

import br.com.carv.finances.entities.User;

public interface UserService {
	
	User authUser(String emailUser, String passwordUser); 
	
	User saveUser(User user); 
	
	void emailValidate(String email); 
	
	Optional<User> findByIdUser(Long idUser); 
	
	

}
