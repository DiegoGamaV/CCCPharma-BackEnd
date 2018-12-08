package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.user;

import java.util.HashMap;

import org.springframework.lang.NonNull;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.exceptions.LoginAlreadyInUseException;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user.User;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user.UserCollection;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.UserRepository;

public class UserController {
	UserCollection users;
	UserRepository userDAO;
	
	public UserController(UserRepository userDAO) {
		this.userDAO = userDAO;
		this.users = new UserCollection(loadUsers());
	}
	
	public void addUser(@NonNull String name, @NonNull String login, @NonNull String password, @NonNull boolean isAdmin)
			throws LoginAlreadyInUseException {
		User user = new User(name, login, password, isAdmin);
		this.users.addUser(user);
		this.userDAO.save(user);
	}
	
	public boolean checkRegistered(@NonNull String login) {
		return users.checkRegistered(login);
	}
	
	public User getUserByLogin(@NonNull String login) {
		return users.getUserByLogin(login);
	}
	
	private HashMap<String, User> loadUsers(){
		Iterable<User> iterableUsers = this.userDAO.findAll();
		HashMap<String, User> mappedUsers = new HashMap<String, User>();
		for (User user : iterableUsers) {
			mappedUsers.put(user.getLogin(), user);
		}
		return mappedUsers;
	}
}
