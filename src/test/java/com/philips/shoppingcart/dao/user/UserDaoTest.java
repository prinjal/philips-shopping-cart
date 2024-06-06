package com.philips.shoppingcart.dao.user;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.dao.user.impl.UserJpaDataAccessImpl;
import com.philips.shoppingcart.model.User;
import com.philips.shoppingcart.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserDaoTest extends AbstractTestContainer {

    private UserDao testUserJpaDataAccess;
    private AutoCloseable autoCloseable;

    @Mock
    private UserRepository testUserRepository;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        this.testUserJpaDataAccess = new UserJpaDataAccessImpl(testUserRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void saveUser() {
        // Given
        User user = new User();
        user.setId(1L);
        when(testUserRepository.save(user)).thenReturn(user);

        // When
        User savedUser = testUserJpaDataAccess.saveUser(user);

        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isEqualTo(1L);
        verify(testUserRepository, times(1)).save(user);
    }

    @Test
    void findByEmail() {
        // Given
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        when(testUserRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // When
        Optional<User> retrievedUser = testUserJpaDataAccess.findByEmail(email);

        // Then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getEmail()).isEqualTo(email);
        verify(testUserRepository, times(1)).findByEmail(email);
    }

    @Test
    void getAllUsers() {
        // Given
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);
        List<User> users = Arrays.asList(user1, user2);
        when(testUserRepository.findAll()).thenReturn(users);

        // When
        List<User> retrievedUsers = testUserJpaDataAccess.getAllUsers();

        // Then
        assertThat(retrievedUsers).hasSize(2);
        assertThat(retrievedUsers).contains(user1, user2);
        verify(testUserRepository, times(1)).findAll();
    }
}
