package br.com.devgemin.base.ws.exception.baseexception;

import br.com.devgemin.base.ws.exception.HandlerException;
import br.com.devgemin.base.ws.message.MessageKey;

public abstract class InternalServerErrorException extends HandlerException {

	private static final long serialVersionUID = 1L;

	private Exception exception;

	public InternalServerErrorException(Exception exception, MessageKey messageKey, String... param) {
		super(messageKey, param);
		this.exception = exception;
	}

	public Exception getException() {
		return exception;
	}
}