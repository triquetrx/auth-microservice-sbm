package com.cognizant.authentication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public @Data @AllArgsConstructor @NoArgsConstructor class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer loginId;
	private String name;
	private String email;
	private String password;
	private String roles;
	private Long userId;
	private boolean active;
	private String securityKey;
}
