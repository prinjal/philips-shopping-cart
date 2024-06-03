package com.philips.shoppingcart.service.item;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.service.item.impl.ItemServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemServiceTest extends AbstractTestContainer {

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
        item.setProduct(
                new Product(
                        1L,
                        "Test",
                        10.0
                )
        );
        when(itemDao.getItemById(itemId)).thenReturn(Optional.of(item));

        // When
        Optional<ResponseItemDto> retrievedItem = itemService.getItemById(itemId);

        // Then
        ResponseItemDto expectedItem = new ResponseItemDto(
                item.getId(),
                new ResponseProductDto(item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice()),
                item.getQuantity());

        assertThat(retrievedItem).isPresent();
        assertThat(retrievedItem.get()).isEqualToComparingFieldByField(expectedItem);
        verify(itemDao, times(1)).getItemById(itemId);
    }

    @Test
    void getItems() {
        // Given
        Product product1 = new Product(1L, "Product 1", 100.0);
        Product product2 = new Product(2L, "Product 2", 200.0);

        Item item1 = new Item();
        item1.setId(1L);
        item1.setProduct(product1);
        item1.setQuantity(2);

        Item item2 = new Item();
        item2.setId(2L);
        item2.setProduct(product2);
        item2.setQuantity(3);

        List<Item> items = Arrays.asList(item1, item2);
        when(itemDao.getItems()).thenReturn(items);

        // When
        List<ResponseItemDto> retrievedItems = itemService.getItems();

        // Then
        assertThat(retrievedItems).hasSize(2);

        List<ResponseItemDto> expectedItems = items.stream()
                .map(item -> new ResponseItemDto(
                        item.getId(),
                        new ResponseProductDto(item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice()),
                        item.getQuantity()))
                .collect(Collectors.toList());

        assertThat(retrievedItems).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedItems);

        verify(itemDao, times(1)).getItems();
    }
}
