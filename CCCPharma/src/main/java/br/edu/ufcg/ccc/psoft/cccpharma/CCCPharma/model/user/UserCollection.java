package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.NonNull;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400.BadRequest400Exception;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400.Conflict409Exception;

public class UserCollection {
	Map<String, User> users;
	
	public UserCollection(HashMap<String, User> users) {
		this.users = users;
	}
	
	public void addUser(User user) throws Conflict409Exception {
		if (users.containsKey(user.getLogin())) {
			throw new Conflict409Exception("Login already in use: " + user.getLogin());
		}
		users.put(user.getLogin(), user);
	}
	
	public boolean checkRegistered(@NonNull String login) {
		return users.containsKey(login);
	}
	
	public User getUserByLogin(@NonNull String login) throws BadRequest400Exception {
		User user = users.get(login);
		if (user != null)
			return user;
		else
			throw new BadRequest400Exception("User is not registered");
	}
	
	public boolean checkPermission(@NonNull String login) throws BadRequest400Exception {
		return getUserByLogin(login).isAdmin();
	}
}



