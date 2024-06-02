package com.philips.shoppingcart.service.item;

import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.service.item.impl.ItemServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    @Mock
    private ItemDao itemDao;


    private ItemServiceImpl itemService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        itemService = new ItemServiceImpl(itemDao);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getItemById() {
        // Given
        Long itemId = 1L;
        Item item = new Item();
        item.setId(itemId);
        when(itemDao.getItemById(itemId)).thenReturn(item);

        // When
        Optional<Item> retrievedItem = itemService.getItemById(itemId);

        // Then
        assertThat(retrievedItem).isEqualTo(item);
        verify(itemDao, times(1)).getItemById(itemId);
    }

    @Test
    void getItems() {
        // Given
        Item item1 = new Item();
        item1.setId(1L);
        Item item2 = new Item();
        item2.setId(2L);
        List<Item> items = Arrays.asList(item1, item2);
        when(itemDao.getItems()).thenReturn(items);

        // When
        List<Item> retrievedItems = itemService.getItems();

        // Then
        assertThat(retrievedItems).hasSize(2);
        assertThat(retrievedItems).containsExactlyInAnyOrder(item1, item2);
        verify(itemDao, times(1)).getItems();
    }
}
