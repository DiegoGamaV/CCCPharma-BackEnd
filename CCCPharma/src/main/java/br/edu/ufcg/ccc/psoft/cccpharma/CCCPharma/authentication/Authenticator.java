package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.authentication;

public interface Authenticator {

	public void userAuthenticate(String login, String maybePassword);
	public void adminAuthenticate(String login, String maybePassword);
}
