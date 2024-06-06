package com.philips.shoppingcart.service.user;

import com.philips.shoppingcart.dao.user.UserDao;
import com.philips.shoppingcart.dto.user.ResponseUserDto;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.model.User;
import com.philips.shoppingcart.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserDao userDao;

    private UserServiceImpl userService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userDao);  // Manually instantiate and inject mock
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllUsers() {
        // Given
        ShoppingCart shoppingCart1 = new ShoppingCart();
        shoppingCart1.setId(1L);
        User user1 = new User();
        user1.setFullName("John Doe");
        user1.setEmail("john.doe@example.com");
        user1.setPassword("password");
        user1.setCreatedAt(new Date());
        user1.setShoppingCart(shoppingCart1);
        user1.setUpdatedAt(new Date());

        ShoppingCart shoppingCart2 = new ShoppingCart();
        shoppingCart2.setId(2L);
        User user2 = new User();
        user2.setFullName("Jane Doe");
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("password");
        user2.setCreatedAt(new Date());
        user2.setShoppingCart(shoppingCart2);
        user2.setUpdatedAt(new Date());

        List<User> users = Arrays.asList(user1, user2);
        when(userDao.getAllUsers()).thenReturn(users);

        // When
        List<ResponseUserDto> retrievedUsers = userService.getAllUsers();

        // Then
        assertThat(retrievedUsers).hasSize(2);
        assertThat(retrievedUsers).extracting(ResponseUserDto::getFullName)
                .containsExactlyInAnyOrder("John Doe", "Jane Doe");
        assertThat(retrievedUsers).extracting(ResponseUserDto::getEmail)
                .containsExactlyInAnyOrder("john.doe@example.com", "jane.doe@example.com");
        verify(userDao, times(1)).getAllUsers();
    }
}
