package br.com.devgemin.base.ws.exception.baseexception;

import br.com.devgemin.base.ws.exception.HandlerException;
import br.com.devgemin.base.ws.message.MessageKey;

public abstract class PreconditionFailedException extends HandlerException {

	private static final long serialVersionUID = 1L;

	public PreconditionFailedException(MessageKey messageKey, String... param) {
		super(messageKey, param);
	}

	public PreconditionFailedException(String code, String message) {
		super(code, message);
	}
}