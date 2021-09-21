package br.com.carv.finances.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.finances.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmailUser(String emailUser);
	
	Boolean existsByEmailUser(String emailUser);
	

}
