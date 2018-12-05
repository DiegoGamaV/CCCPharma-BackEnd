package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.product;

import org.springframework.lang.NonNull;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.LoginAlreadyInUseException;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user.User;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user.UserCollection;

public class UserController {
	UserCollection users;
	
	public UserController() {
		users = new UserCollection();
	}
	
	public void addUser(@NonNull String name, @NonNull String login, @NonNull String password, @NonNull boolean isAdmin)
			throws LoginAlreadyInUseException {
		users.addUser(name, login, password, isAdmin);
	}
	
	public boolean checkRegistered(@NonNull String login) {
		return users.checkRegistered(login);
	}
	
	private User getUserByLogin(@NonNull String login) {
		return users.getUserByLogin(login);
	}
	
	private boolean checkPermission(@NonNull String login) {
		return users.checkPermission(login);
	}
}
