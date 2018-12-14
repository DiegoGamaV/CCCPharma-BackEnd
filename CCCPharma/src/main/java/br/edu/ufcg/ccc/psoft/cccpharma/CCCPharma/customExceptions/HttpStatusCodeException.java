package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions;

public abstract class HttpStatusCodeException extends RuntimeException {
	protected static final long serialVersionUID = 1L;
	protected String message;
}
