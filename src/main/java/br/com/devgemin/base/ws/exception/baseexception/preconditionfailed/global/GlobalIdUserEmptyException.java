package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_ID_USER_EMPTY;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class GlobalIdUserEmptyException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public GlobalIdUserEmptyException() {
        super(GLOBAL_ID_USER_EMPTY);
    }
}