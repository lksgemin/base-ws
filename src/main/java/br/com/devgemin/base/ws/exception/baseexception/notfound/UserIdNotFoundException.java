package br.com.devgemin.base.ws.exception.baseexception.notfound;

import static br.com.devgemin.base.ws.message.MessageKey.USER_ID_NOT_FOUND;

import br.com.devgemin.base.ws.exception.baseexception.NotFoundException;

public class UserIdNotFoundException extends NotFoundException {
	private static final long serialVersionUID = 1L;

	public UserIdNotFoundException() {
		super(USER_ID_NOT_FOUND);
	}
}
