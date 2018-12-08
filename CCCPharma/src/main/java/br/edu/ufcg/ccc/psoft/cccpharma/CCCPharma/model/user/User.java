package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.DatatypeConverter;

import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	private String login;
	
	@Column(name = "name", nullable = false)
	@Size(max = 50)
	private String name;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "is_admin", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private boolean isAdmin;
	
	public User(@NonNull String _name, @NonNull String _login, @NonNull String _password, boolean _isAdmin) {
		this.name = _name;
		this.login = _login;
		this.isAdmin = _isAdmin;
		try {
			this.password = digestPassword(_password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
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
	
	private String digestPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(password.getBytes());
		byte[] digestedBytes = messageDigest.digest();
		String digestedPassword = DatatypeConverter.printHexBinary(digestedBytes).toUpperCase();
		return digestedPassword;
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
