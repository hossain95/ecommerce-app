package com.example.shoppingcard.exceptions;

public class AuthenticationFailedException extends IllegalArgumentException {
    public AuthenticationFailedException(String msg){
        super(msg);
    }
}
