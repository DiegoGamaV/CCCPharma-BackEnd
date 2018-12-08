package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.NonNull;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.exceptions.LoginAlreadyInUseException;

public class UserCollection {
	Map<String, User> users;
	
	public UserCollection() {
		users = new HashMap<>();
	}
	
	public void addUser(@NonNull String name, @NonNull String login, @NonNull String password, boolean isAdmin) 
			throws LoginAlreadyInUseException {
		if (users.containsKey(login)) {
			throw new LoginAlreadyInUseException(login);
		}
		users.put(login, new User(name, login, password, isAdmin));
	}
	
	public boolean checkRegistered(@NonNull String login) {
		return users.containsKey(login);
	}
	
	public User getUserByLogin(@NonNull String login) {
		return users.get(login);
	}
	
	public boolean checkPermission(@NonNull String login) {
		return getUserByLogin(login).isAdmin();
	}
}



