package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.HttpStatusCodeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFound404Exception extends HttpStatusCodeException {
	private static final long serialVersionUID = 1L;
	public NotFound404Exception(String message) {
		super();
		this.message = message;
	}
}
