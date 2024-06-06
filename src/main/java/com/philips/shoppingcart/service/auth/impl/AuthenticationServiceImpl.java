package com.philips.shoppingcart.service.auth.impl;

import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.dao.user.UserDao;
import com.philips.shoppingcart.dto.user.RequestUserDto;
import com.philips.shoppingcart.dto.user.LoginRequestUserDto;
import com.philips.shoppingcart.dto.user.ResponseUserDto;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.model.User;
import com.philips.shoppingcart.service.auth.AuthentiCationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthentiCationService {
    private final UserDao userDao;
    private final ShoppingCartDao shoppingCartDao;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public ResponseUserDto signup(RequestUserDto input) {
        User user = new User();
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setUser(user);
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setShoppingCart(shoppingCart);

        userDao.saveUser(user);

        return new ResponseUserDto(
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getShoppingCart().getId(),
                user.getUpdatedAt()
        );
    }

    public ResponseUserDto authenticate(LoginRequestUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        Optional<User> existsUser=userDao.findByEmail(input.getEmail());

        if (existsUser.isPresent()){
            User getUser=existsUser.get();
            return new ResponseUserDto(
                    getUser.getFullName(),
                    getUser.getEmail(),
                    getUser.getPassword(),
                    getUser.getCreatedAt(),
                    getUser.getShoppingCart().getId(),
                    getUser.getUpdatedAt()
            );
        }
        else{
            throw new ResourceNotFound("User not found");
        }

    }

    @Override
    public User getUserObject(LoginRequestUserDto input) {
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