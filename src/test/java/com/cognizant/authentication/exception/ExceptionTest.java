package com.cognizant.authentication.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExceptionTest {
	
	InvalidSecurityKey invalidSecurityKey;
	InvalidTokenException invalidTokenException;
	PasswordNotAMatchException passwordNotAMatchException;
	UserNotFoundException userNotFoundException;
	UsernameTakenException usernameTakenException;
	
	
	@Test
	void testLoadInvalidSecurityKey() {
		assertThat(invalidSecurityKey).isNull();
	}
	
	@Test
	void testLoadInvalidTokenException() {
		assertThat(invalidTokenException).isNull();
		
	}
	
	@Test
	void testLoadPasswordNotAMatchException() {
		assertThat(passwordNotAMatchException).isNull();
		
	}
	
	@Test
	void testLoadUserNotFoundException() {
		assertThat(userNotFoundException).isNull();
		
	}
	
	@Test
	void testLoadUsernameTakenException() {
		assertThat(usernameTakenException).isNull();
		
	}

}
