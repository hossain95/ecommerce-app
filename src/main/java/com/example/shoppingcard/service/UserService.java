package com.example.shoppingcard.service;

import com.example.shoppingcard.dto.ResponseDto;
import com.example.shoppingcard.dto.user.SignInDto;
import com.example.shoppingcard.dto.user.SignInResponseDto;
import com.example.shoppingcard.dto.user.SignUpDto;
import com.example.shoppingcard.exceptions.AuthenticationFailedException;
import com.example.shoppingcard.exceptions.CustomException;
import com.example.shoppingcard.model.AuthenticationToken;
import com.example.shoppingcard.model.User;
import com.example.shoppingcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService authenticationService;


    @Transactional
    public ResponseDto userSignUp(SignUpDto signUpDto){
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("user already present");
        }

        String password = signUpDto.getPassword();
//        System.out.println(password);
        password = new BCryptPasswordEncoder().encode(password);
//        System.out.println(password);
        User user = new User(signUpDto.getFirstName(), signUpDto.getLastName(), signUpDto.getEmail(), password);
        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        return new ResponseDto("success", "user is created");
    }

    public SignInResponseDto userSignIn(SignInDto signInDto){
        User user = userRepository.findByEmail(signInDto.getEmail());

        if(!Objects.nonNull(user)){
            throw new AuthenticationFailedException("user is not valid");
        }
//        System.out.println(user.getEmail() + " "+ user.getFirstName());
        String password = signInDto.getPassword();
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


        if(!bcrypt.matches(password, user.getPassword())){
            throw new AuthenticationFailedException("wrong password");
        }
//        System.out.println(user.getEmail() + " "+ user.getPassword());

        AuthenticationToken token = authenticationService.getAuthenticationToken(user);

        if (Objects.isNull(token)){
            throw new CustomException("token is not present");
        }

        return new SignInResponseDto("success", token.getToken());
    }
}
