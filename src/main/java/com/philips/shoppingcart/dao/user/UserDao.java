package com.philips.shoppingcart.dao.user;

import com.philips.shoppingcart.dto.user.RequestUserDto;
import com.philips.shoppingcart.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User saveUser(User user);
    Optional<User> findByEmail(String email);

    List<User> getAllUsers();
}
