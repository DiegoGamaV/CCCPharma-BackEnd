package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user;

import org.springframework.lang.NonNull;

public class User {
	private String name;
	private String login;
	private String password;
	private boolean isAdmin;
	
	public User(@NonNull String _name, @NonNull String _login, @NonNull String _password, boolean _isAdmin) {
		this.name = _name;
		this.login = _login;
		this.password = _password;
		this.isAdmin = _isAdmin;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLogin() {
		return login;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public boolean checkPassword(@NonNull String maybePassword) {
		return password.equals(maybePassword);
	}
	
	private void setPassword(@NonNull String newPassword) {
		password = newPassword;
	}
	
	public boolean changePassword(@NonNull String oldPassword, @NonNull String newPassword) {
		if (checkPassword(oldPassword)) {
			setPassword(newPassword);
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 73;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (null != other && other instanceof User) {
			User otherUser = (User) other;
			return login.equals(otherUser.login) && password.equals(otherUser.password);
		}
		return false;
	}
}
