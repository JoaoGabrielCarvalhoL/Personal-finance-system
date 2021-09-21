package br.com.carv.finances.repositories;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.carv.finances.entities.User;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("aplication-test.properties")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

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
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	public void verificateUserEmail() {

		User user = new User();
		user.setNameUser("João Gabriel Carvalho Lopes da Cruz");
		user.setEmailUser("27.joaogabriel@gmail.com");
		user.setPasswordUser("123456789");
		
		testEntityManager.persist(user);
		
		boolean result = userRepository.existsByEmailUser("27.joaogabriel@gmail.com");
		
		Assertions.assertThat(result).isTrue();

	}
	
	@Test
	public void returnFalseWhenNoRegisteredUser() {
		 
		boolean result = userRepository.existsByEmailUser("27.joaogabriel@gmai.com");
		 
		Assertions.assertThat(result).isFalse();
	}
	
	@Test
	public void persistUser() {
		
		User user = new User();
		user.setNameUser("João Gabriel Carvalho Test");
		user.setEmailUser("test.joaogabriel@gmail.com"); 
		user.setPasswordUser("123456");
		
		userRepository.save(user);
		
		Assertions.assertThat(user.getIdUser()).isNotNull();
	}
	
	@Test
	public void findByEmailUser() {
		
		User user = new User();
		user.setNameUser("João Gabriel Carvalho Test Test");
		user.setEmailUser("test.joaotest@gmail.com"); 
		user.setPasswordUser("123test123");
		
		testEntityManager.persist(user);
		
		
		Optional<User> userSearch =	userRepository.findByEmailUser("test.joaotest@gmail.com");
		
		Assertions.assertThat(userSearch.isPresent()).isTrue();
	}



	/*
			First implementation: 
	
			@Test
			public void verificateUserEmail() {
		
				//Step 1:
				User user = new User();
				user.setNameUser("João Gabriel Carvalho Lopes da Cruz");
				user.setEmailUser("27.joaogabriel@gmail.com");
				user.setPasswordUser("123456789");
				
				userRepository.save(user);
				
				//Step 2:
				boolean result = userRepository.existsByEmailUser("27.joaogabriel@gmail.com");
				
				//Step 3:
				Assertions.assertThat(result).isTrue();
		
			}
			
			@Test
			public void returnFalseWhenNoRegisteredUser() {
				//Step 1: 
				userRepository.deleteAll();
				
				//Step 2: 
				boolean result = userRepository.existsByEmailUser("27.joaogabriel@gmai.com");
				
				//Step 3: 
				Assertions.assertThat(result).isFalse();
			}

	 */
}
