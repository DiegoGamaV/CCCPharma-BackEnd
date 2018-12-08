package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.user;

public class RegisterInformationUser {
	private String login;
	private String name;
	private String password;
	private boolean isAdmin;

	public RegisterInformationUser(String login, String name, String password) {
		super();
		this.login = login;
		this.name = name;
		this.password = password;
		this.isAdmin = false;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
