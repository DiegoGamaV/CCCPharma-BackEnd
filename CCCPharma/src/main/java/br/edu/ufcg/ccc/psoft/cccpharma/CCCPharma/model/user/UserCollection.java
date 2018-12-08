package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.NonNull;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.exceptions.LoginAlreadyInUseException;

public class UserCollection {
	Map<String, User> users;
	
	public UserCollection(HashMap<String, User> users) {
		this.users = users;
	}
	
	public void addUser(User user) throws LoginAlreadyInUseException {
		if (users.containsKey(user.getLogin())) {
			throw new LoginAlreadyInUseException(user.getLogin());
		}
		users.put(user.getLogin(), user);
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



