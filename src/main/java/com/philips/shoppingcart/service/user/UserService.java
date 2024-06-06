package com.philips.shoppingcart.service.user;

import com.philips.shoppingcart.dto.user.ResponseUserDto;

import java.util.List;

public interface UserService {

    List<ResponseUserDto> getAllUsers();
}
