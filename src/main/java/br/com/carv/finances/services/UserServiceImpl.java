package br.com.carv.finances.services;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.carv.finances.entities.User;
import br.com.carv.finances.exceptions.AuthenticationException;
import br.com.carv.finances.exceptions.BusinessRuleException;
import br.com.carv.finances.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public User authUser(String emailUser, String passwordUser) {
		Optional<User> user = userRepository.findByEmailUser(emailUser);
		
		if (!user.isPresent()) {
			throw new AuthenticationException("User not found!");	
		}
		
		if (!user.get().getPasswordUser().equals(passwordUser)) {
			throw new AuthenticationException("Invalid email or password!");
		}
		
		return user.get();
	}

	@Override
	@Transactional
	public User saveUser(User user) {
		emailValidate(user.getEmailUser());
		return userRepository.save(user);
	}

	@Override
	public void emailValidate(String emailUser) {
		Boolean isExistEmailUser =  userRepository.existsByEmailUser(emailUser);
		if (isExistEmailUser) {
			throw new BusinessRuleException("There is already a registered user with this email.");
		}
	}
	

}
