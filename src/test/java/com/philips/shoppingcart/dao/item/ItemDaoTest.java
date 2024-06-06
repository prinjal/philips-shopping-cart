package com.philips.shoppingcart.dao.item;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.dao.item.impl.ItemJpaDataAccessImpl;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.repository.ItemJpaRepository;
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
class ItemDaoTest extends AbstractTestContainer {

    private ItemDao testItemJpaDataAccess;
    private AutoCloseable autoCloseable;
    @Mock private ItemJpaRepository testItemJpaRepository;

    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        this.testItemJpaDataAccess=new ItemJpaDataAccessImpl(testItemJpaRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }

    @Test
    void getItemById() {
        // Given
        Long itemId = 1L;
        Item item = new Item();
        item.setId(itemId);
        when(testItemJpaRepository.findById(itemId)).thenReturn(Optional.of(item));

        // When
        Optional<Item> retrievedItem = testItemJpaDataAccess.getItemById(itemId);

        // Then
        assertThat(retrievedItem).isPresent();
        assertThat(retrievedItem.get().getId()).isEqualTo(itemId);
        verify(testItemJpaRepository, times(1)).findById(itemId);
    }

    @Test
    void getItems() {
        // Given
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(2L);
        List<Item> items = Arrays.asList(item1, item2);
        when(testItemJpaRepository.findAll()).thenReturn(items);

        // When
        List<Item> retrievedItems = testItemJpaDataAccess.getItems();

        // Then
        assertThat(retrievedItems).hasSize(2);
        assertThat(retrievedItems).contains(item1, item2);
        verify(testItemJpaRepository, times(1)).findAll();
    }
}