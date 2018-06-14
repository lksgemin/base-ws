package br.com.devgemin.base.ws.exception.baseexception.unauthorized;

import static br.com.devgemin.base.ws.message.MessageKey.SIGN_UP_EXISTING_USER_EMAIL;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class SignUpExistingUserEmailException extends PreconditionFailedException {
	private static final long serialVersionUID = 1L;

	public SignUpExistingUserEmailException() {
		super(SIGN_UP_EXISTING_USER_EMAIL);
	}
}
