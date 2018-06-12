package br.com.devgemin.base.ws.exception.baseexception;

import br.com.devgemin.base.ws.exception.HandlerException;
import br.com.devgemin.base.ws.message.MessageKey;

public abstract class NotFoundException extends HandlerException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(MessageKey messageKey, String... param) {
		super(messageKey, param);
	}
}