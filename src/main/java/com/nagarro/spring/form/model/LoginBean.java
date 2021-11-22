package com.nagarro.spring.form.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
@Component
public class LoginBean {

	@NotEmpty
    private String name;
     
    @NotEmpty
    private String password;
    
 
    private boolean loginStatus;

    private String authFailure;
	
    
	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthFailure() {
		return authFailure;
	}

	public void setAuthFailure(String authFailure) {
		this.authFailure = authFailure;
	}
     
    
    
	
}
