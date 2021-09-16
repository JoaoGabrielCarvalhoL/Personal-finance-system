package br.com.carv.finances.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.carv.finances.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
