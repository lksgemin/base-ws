package br.com.devgemin.base.ws.exception.baseexception.unauthorized;

import static br.com.devgemin.base.ws.message.MessageKey.SIGN_UP_EXISTING_USERNAME;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class SignUpExistingUsernameException extends PreconditionFailedException {
	private static final long serialVersionUID = 1L;

	public SignUpExistingUsernameException() {
		super(SIGN_UP_EXISTING_USERNAME);
	}
}
