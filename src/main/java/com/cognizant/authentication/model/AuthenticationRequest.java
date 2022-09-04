package com.cognizant.authentication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

public @Data @NoArgsConstructor class AuthenticationRequest {

	private String email;
	private String password;

	public AuthenticationRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

}
