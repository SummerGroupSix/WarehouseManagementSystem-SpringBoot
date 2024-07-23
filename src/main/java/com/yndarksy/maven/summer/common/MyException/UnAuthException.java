package com.yndarksy.maven.summer.common.MyException;

public class UnAuthException extends RuntimeException{
    public UnAuthException() {
        super();
    }

    public UnAuthException(String message) {
        super(message);
    }
}
