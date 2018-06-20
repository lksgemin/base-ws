package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.UPDATE_USER_EXISTING_EMAIL;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class UpdateUserExistingEmailException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public UpdateUserExistingEmailException() {
        super(UPDATE_USER_EXISTING_EMAIL);
    }
}