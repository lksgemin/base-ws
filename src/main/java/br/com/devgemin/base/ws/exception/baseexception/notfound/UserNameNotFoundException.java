package br.com.devgemin.base.ws.exception.baseexception.notfound;

import static br.com.devgemin.base.ws.message.MessageKey.USER_NAME_NOT_FOUND;

import br.com.devgemin.base.ws.exception.baseexception.NotFoundException;

public class UserNameNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public UserNameNotFoundException() {
		super(USER_NAME_NOT_FOUND);
	}
}
