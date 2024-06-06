package com.philips.shoppingcart.dto.user;

import com.philips.shoppingcart.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseUserDto {
    private String fullName;
    private String email;
    private String password;
    private Date createdAt;
    private Long shoppingCartId;
    private Date updatedAt;
}
