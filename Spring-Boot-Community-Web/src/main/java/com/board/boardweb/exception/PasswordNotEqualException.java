package com.board.boardweb.exception;

import org.springframework.security.core.AuthenticationException;

public class PasswordNotEqualException extends AuthenticationException {
    public PasswordNotEqualException(String msg) {
        super(msg);
    }
}
