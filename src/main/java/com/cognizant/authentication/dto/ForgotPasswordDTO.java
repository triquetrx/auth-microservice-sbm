package com.cognizant.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public @AllArgsConstructor @Data @NoArgsConstructor class ForgotPasswordDTO {

	private String email;
	private String securityKey;
	private String newPassword;
	
}
