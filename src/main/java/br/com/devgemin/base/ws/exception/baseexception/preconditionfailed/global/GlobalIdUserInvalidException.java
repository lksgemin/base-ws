package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_ID_USER_INVALID;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class GlobalIdUserInvalidException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public GlobalIdUserInvalidException() {
        super(GLOBAL_ID_USER_INVALID);
    }
}