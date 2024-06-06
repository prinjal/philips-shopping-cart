package com.philips.shoppingcart.repository;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserJpaInterfaceTest extends AbstractTestContainer {

    @Autowired
    private UserRepository testUserRepository;

    @Test
    void findByEmail() {
        // Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user = testUserRepository.save(user);

        // When
        Optional<User> retrievedUser = testUserRepository.findByEmail("test@example.com");

        // Then
        assertThat(retrievedUser).isPresent();
        assertThat(retrievedUser.get().getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void existsByEmail() {
        // Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        testUserRepository.save(user);

        // When
        boolean exists = testUserRepository.findByEmail("test@example.com").isPresent();

        // Then
        assertThat(exists).isTrue();
    }
}
