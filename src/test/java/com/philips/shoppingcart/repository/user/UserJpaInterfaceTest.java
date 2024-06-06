package com.philips.shoppingcart.repository.user;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.model.User;
import com.philips.shoppingcart.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserJpaInterfaceTest extends AbstractTestContainer {

    @Autowired
    private UserRepository testUserRepository;

    @Test
    @Transactional
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
