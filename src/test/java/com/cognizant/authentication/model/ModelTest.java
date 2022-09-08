package com.cognizant.authentication.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ModelTest {

	Users users = new Users();
	AuthenticationRequest authenticationRequest = new AuthenticationRequest();
	AuthenticationResponse authenticationResponse = new AuthenticationResponse();

	@Test
	void loadUserModelTest() {
		assertThat(users).isNotNull();
	}

	@Test
	void loadAuthRequestModelTest() {
		assertThat(authenticationRequest).isNotNull();
	}

	@Test
	void loadAuthResponseModelTest() {
		assertThat(authenticationResponse).isNotNull();
	}

	@Test
	void confirmUserDataTest() {
		users.setEmail("test@test.com");
		users.setLoginId(1);
		users.setName("Tester Test");
		users.setPassword("testedByZaid");
		users.setSecurityKey("Zaid Khan");
		users.setRoles("ROLE_ADMIN");
		users.setActive(true);

		assertEquals(users.getEmail(), "test@test.com");
		assertEquals(users.getLoginId(), 1);
		assertEquals(users.getName(), "Tester Test");
		assertEquals(users.getPassword(), "testedByZaid");
		assertEquals(users.getRoles(), "ROLE_ADMIN");
		assertEquals(users.isActive(), true);

	}

	@Test
	void confirmAuthenticationRequestDataTest() {
		authenticationRequest.setEmail("test@test.com");
		authenticationRequest.setPassword("testedByZaid");

		assertEquals(authenticationRequest.getEmail(), "test@test.com");
		assertEquals(authenticationRequest.getPassword(), "testedByZaid");

	}
	
	@Test
	void confirmAuthResponseDataTest() {
		authenticationResponse.setName("Zaid");
		authenticationResponse.setToken("Token");
		
		assertEquals(authenticationResponse.getName(), "Zaid");
		assertEquals(authenticationResponse.getToken(), "Token");
	}

}
