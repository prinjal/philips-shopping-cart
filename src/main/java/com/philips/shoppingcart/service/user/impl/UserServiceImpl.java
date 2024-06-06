package com.philips.shoppingcart.service.user.impl;

import com.philips.shoppingcart.dao.user.UserDao;
import com.philips.shoppingcart.dto.user.ResponseUserDto;
import com.philips.shoppingcart.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public List<ResponseUserDto> getAllUsers() {
        return userDao.getAllUsers().stream().map(user -> new ResponseUserDto(
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getShoppingCart().getId(),
                user.getUpdatedAt()
        )).collect(Collectors.toList());
    }
}
