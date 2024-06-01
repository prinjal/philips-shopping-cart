package com.philips.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestingTestContainer extends AbstractTestContainer{

    @Test
    void checkPostgresqlContainerRunning() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }
}
