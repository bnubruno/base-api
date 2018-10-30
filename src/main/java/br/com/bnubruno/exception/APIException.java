package br.com.bnubruno.exception;

import lombok.Getter;

@Getter
public abstract class APIException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String errorKey;

	protected APIException(String message, String errorKey) {
		super(message);
		this.errorKey = errorKey;
	}
}
