package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.HttpStatusCodeException;

public class Unauthorized401Exception extends HttpStatusCodeException {
	private static final long serialVersionUID = 1L;
	public Unauthorized401Exception(String message) {
		super();
		this.message = message;
	}
}
