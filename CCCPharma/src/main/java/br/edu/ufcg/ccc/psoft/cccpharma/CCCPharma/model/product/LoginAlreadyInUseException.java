package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product;

import org.springframework.lang.NonNull;

public class LoginAlreadyInUseException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String login;
	public LoginAlreadyInUseException(@NonNull String _login) {
		this.login = _login;
	}
	
	public String getLogin() {
		return login;
	}
}
