package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.authentication;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user.User;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.UserRepository;

public class ClientAuthenticator implements Authenticator {
	
	private UserRepository userDAO;
	
	public ClientAuthenticator(UserRepository userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public void userAuthenticate(String login, String maybePassword) {
		User user = this.userDAO.findById(login).get();
		if (!user.checkPassword(maybePassword))
			throw new IllegalArgumentException();
	}

	@Override
	public void adminAuthenticate(String login, String maybePassword) {
		User user = this.userDAO.findById(login).get();
		if (!user.checkPassword(maybePassword))
			throw new IllegalArgumentException();
		if (!user.isAdmin())
			throw new IllegalArgumentException();
	}

}
