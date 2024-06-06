package com.philips.shoppingcart.controller;

import com.philips.shoppingcart.config.security.JwtService;
import com.philips.shoppingcart.dto.user.LoginResponseDto;
import com.philips.shoppingcart.dto.user.LoginRequestUserDto;
import com.philips.shoppingcart.dto.user.RequestUserDto;
import com.philips.shoppingcart.dto.user.ResponseUserDto;
import com.philips.shoppingcart.model.User;
import com.philips.shoppingcart.service.auth.impl.AuthenticationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationServiceImpl authenticationServiceImpl;

    public AuthenticationController(JwtService jwtService, AuthenticationServiceImpl authenticationServiceImpl) {
        this.jwtService = jwtService;
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseUserDto> register(@RequestBody RequestUserDto registerUserDto) {
        ResponseUserDto registeredUser = authenticationServiceImpl.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginRequestUserDto loginRequestUserDto) {
        User authenticatedUser = authenticationServiceImpl.getUserObject(loginRequestUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}