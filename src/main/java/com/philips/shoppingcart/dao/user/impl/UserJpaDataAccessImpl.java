package com.philips.shoppingcart.dao.user.impl;

import com.philips.shoppingcart.dao.user.UserDao;
import com.philips.shoppingcart.dto.user.RequestUserDto;
import com.philips.shoppingcart.model.User;
import com.philips.shoppingcart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("UserJpaDao")
@Primary
@RequiredArgsConstructor
public class UserJpaDataAccessImpl implements UserDao {
    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
