package com.adiener.java_househelper_capstone_backend.Security;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequest {	
	private String username;	
	private String password;
}
