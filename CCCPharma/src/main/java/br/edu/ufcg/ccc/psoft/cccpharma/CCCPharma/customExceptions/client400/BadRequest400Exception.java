package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.HttpStatusCodeException;

public class BadRequest400Exception extends HttpStatusCodeException {
	private static final long serialVersionUID = 1L;
	public BadRequest400Exception(String message) {
		super();
		this.message = message;
	}
}
