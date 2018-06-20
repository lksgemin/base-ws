package br.com.devgemin.base.ws.exception.baseexception.preconditionfailed.global;

import static br.com.devgemin.base.ws.message.MessageKey.GLOBAL_INVALID_EMAIL;

import br.com.devgemin.base.ws.exception.baseexception.PreconditionFailedException;

public class GlobalInvalidEmailException extends PreconditionFailedException {

    private static final long serialVersionUID = 1L;

    public GlobalInvalidEmailException() {
        super(GLOBAL_INVALID_EMAIL);
    }
}