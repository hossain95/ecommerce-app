package com.example.shoppingcard.service;

import com.example.shoppingcard.model.AuthenticationToken;
import com.example.shoppingcard.model.User;
import com.example.shoppingcard.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getAuthenticationToken(User user){
        return tokenRepository.getTokenByUserId(user);
    }
}
