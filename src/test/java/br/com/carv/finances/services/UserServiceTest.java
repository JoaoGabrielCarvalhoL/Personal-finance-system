package br.com.carv.finances.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.carv.finances.entities.User;
import br.com.carv.finances.exceptions.AuthenticationException;
import br.com.carv.finances.exceptions.BusinessRuleException;
import br.com.carv.finances.repositories.UserRepository;



@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("aplication-test.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserServiceTest {
	
	/*
	 * Steps:
	 * 
	 * 1. Create scenario
	 * 
	 * 2. Action and execution
	 * 
	 * 3. Verification
	 */
		
	@Autowired
	private UserRepository userRepository;
	
	/*
	 * Implementation With Mock 
	 * 
	 * public void setup() {
	 * 	userRepository = Mockito.mock(UserRepository.class); 
	 * 	userService = new UserServiceImpl(userRepository);
	 * 
	*/
	
	@Test
	public void emailValidate() {
		//Step 1: 
		userRepository.deleteAll();
		
		User user = new User(); 
		user.setNameUser("João Test");
		user.setEmailUser("27.joaogabrieltest@gmai.com");
		user.setPasswordUser("123456789");
		
		//userRepository.save(user);
		
		//Step 2: 
		UserService userService = new UserServiceImpl(userRepository);
		userService.emailValidate(user.getEmailUser());		
	
			
	}
	
	
	@Test
	public void checkIfEmailRegistered() throws BusinessRuleException{
		//Step 1: 
		userRepository.deleteAll();
		
		User user = new User(); 
		user.setNameUser("João Gabriel Carvalho");
		user.setEmailUser("27.joaogabriel@gmai.com");
		user.setPasswordUser("123456789");
		
		//userRepository.save(user);
		
		//Step 2: 
		UserService userService = new UserServiceImpl(userRepository);	
		userService.emailValidate(user.getEmailUser());
		
		
	}
	
	@Test
	public void mustAuthenticateUserTrue() {
		String emailUser = "27.joaogabriel@gmai.com"; 
		String passwordUser = "123456789"; 
		
		User user = new User(); 
		user.setNameUser("João Gabriel Carvalho");
		user.setEmailUser("27.joaogabriel@gmai.com");
		user.setPasswordUser("123456789");
		
		userRepository.save(user);
		
		UserService userService = new UserServiceImpl(userRepository);	
		Optional<User> userAuth = Optional.ofNullable(userService.authUser(emailUser, passwordUser));
		Assertions.assertThat(userAuth.isPresent()).isTrue();
	}
	
	@Test
	public void mustAuthenticateUserFalse() {
		String emailUser = "27.joaogabriel@gmai.com"; 
		String passwordUser = "123456789"; 
	
		UserService userService = new UserServiceImpl(userRepository);	
		
		try {
			Optional<User> userAuth = Optional.ofNullable(userService.authUser(emailUser, passwordUser));
			
			Assertions.assertThat(userAuth.isPresent()).isFalse();
		} catch (AuthenticationException err) {
			err.getMessage();
			err.printStackTrace();
		}
		
	
	
	}
	
	


	

}
