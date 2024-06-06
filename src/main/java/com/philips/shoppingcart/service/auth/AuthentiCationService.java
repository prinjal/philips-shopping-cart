package com.philips.shoppingcart.service.auth;

import com.philips.shoppingcart.dto.user.LoginRequestUserDto;
import com.philips.shoppingcart.dto.user.RequestUserDto;
import com.philips.shoppingcart.dto.user.ResponseUserDto;
import com.philips.shoppingcart.model.User;

public interface AuthentiCationService {

    ResponseUserDto signup(RequestUserDto input);

    ResponseUserDto authenticate(LoginRequestUserDto input);

    User getUserObject(LoginRequestUserDto input);
}
