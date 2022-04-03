package com.example.shoppingcard.controller;

import com.example.shoppingcard.dto.ResponseDto;
import com.example.shoppingcard.dto.user.SignInDto;
import com.example.shoppingcard.dto.user.SignInResponseDto;
import com.example.shoppingcard.dto.user.SignUpDto;
import com.example.shoppingcard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseDto userSignUp(@RequestBody SignUpDto signUpDto){
        return userService.userSignUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto userSignIn(@RequestBody SignInDto signInDto){
        return userService.userSignIn(signInDto);
    }
}
