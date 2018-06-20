package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.UPDATE_USER_BLOCKED;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class UpdateUserBlockedException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public UpdateUserBlockedException() {
        super(UPDATE_USER_BLOCKED);
    }
}