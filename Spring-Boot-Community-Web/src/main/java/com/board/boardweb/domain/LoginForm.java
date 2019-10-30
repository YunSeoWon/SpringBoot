package com.board.boardweb.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginForm {
    private String email;
    private String password;
    public LoginForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
