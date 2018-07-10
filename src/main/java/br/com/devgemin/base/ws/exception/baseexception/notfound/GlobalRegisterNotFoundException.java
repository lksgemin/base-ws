package br.com.devgemin.base.ws.exception.baseexception.notfound;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_REGISTER_NOT_FOUND;

import br.com.devgemin.base.ws.exception.baseexception.NotFoundException;

public class GlobalRegisterNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public GlobalRegisterNotFoundException() {
		super(GLOBAL_REGISTER_NOT_FOUND);
	}
}
