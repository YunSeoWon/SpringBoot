package com.board.boardweb.domain.enums;

public enum RoleType {
    user("USER"),
    admin("ADMIN");

    private String value;
    RoleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
