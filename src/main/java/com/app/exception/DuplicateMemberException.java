package com.app.exception;

public class DuplicateMemberException extends RuntimeException {
    public DuplicateMemberException() {
        super("A member with the same name and surname in this dance class already exists.");
    }
}
