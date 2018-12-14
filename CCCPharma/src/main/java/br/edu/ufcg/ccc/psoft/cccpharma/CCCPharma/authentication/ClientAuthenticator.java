package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.authentication;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400.BadRequest400Exception;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400.Forbidden403Exception;
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
			throw new BadRequest400Exception("Wrong passowrd");
	}

	@Override
	public void adminAuthenticate(String login, String maybePassword) throws BadRequest400Exception, Forbidden403Exception {
		User user = this.userDAO.findById(login).get();
		if (!user.checkPassword(maybePassword))
			throw new BadRequest400Exception("Wrong passowrd");
		if (!user.isAdmin())
			throw new Forbidden403Exception("User does not have enough privileges");
	}

}
