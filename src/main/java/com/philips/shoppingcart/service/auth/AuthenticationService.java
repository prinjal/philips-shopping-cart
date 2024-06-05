package com.philips.shoppingcart.service.auth;

import com.philips.shoppingcart.dao.user.UserDao;
import com.philips.shoppingcart.dto.user.RequestUserDto;
import com.philips.shoppingcart.dto.user.LoginRequestUserDto;
import com.philips.shoppingcart.model.User;
import com.philips.shoppingcart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User signup(RequestUserDto input) {
        User user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userDao.saveUser(user);
    }

    public User authenticate(LoginRequestUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userDao.findByEmail(input.getEmail())
                .orElseThrow();
    }
}