package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.user;

public interface Authenticator {

	public void userAuthenticate(String login, String maybePassword);
	public void adminAuthenticate(String login, String maybePassword);
}
