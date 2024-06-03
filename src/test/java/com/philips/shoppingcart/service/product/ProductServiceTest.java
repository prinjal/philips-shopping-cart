package com.philips.shoppingcart.service.product;

import com.philips.shoppingcart.AbstractTestContainer;
import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.dto.product.RequestProductDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.service.product.impl.ProductServiceImpl;
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
class ProductServiceTest extends AbstractTestContainer {

    @Mock
    private ProductDao productDao;


    private ProductServiceImpl productService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productDao);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllProducts() {
        // Given
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(100.0);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(200.0);

        List<Product> products = Arrays.asList(product1, product2);
        when(productDao.getAllProducts()).thenReturn(products);

        // When
        List<ResponseProductDto> retrievedProducts = productService.getAllProducts();

        // Then
        assertThat(retrievedProducts).hasSize(2);

        List<ResponseProductDto> expectedProducts = products.stream()
                .map(product -> new ResponseProductDto(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());

        assertThat(retrievedProducts).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrderElementsOf(expectedProducts);

        verify(productDao, times(1)).getAllProducts();
    }

    @Test
    void getProductById() {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setPrice(10.0);
        when(productDao.getProductById(productId)).thenReturn(Optional.of(product));

        // When
        ResponseProductDto retrievedProduct = productService.getProductById(productId);

        // Then
        ResponseProductDto expectedProduct = new ResponseProductDto(product.getId(), product.getName(), product.getPrice());

        assertThat(retrievedProduct).isEqualToComparingFieldByField(expectedProduct);
        verify(productDao, times(1)).getProductById(productId);
    }

    @Test
    void createProduct() {
        // Given
        Product product = new Product();
        product.setId(1L);
        product.setName("New Product");
        product.setPrice(10.0);
        when(productDao.createOrUpdateProduct(any(Product.class))).thenReturn(product);

        // When
        ResponseProductDto createdProduct = productService.createProduct(new RequestProductDto(product.getName(), product.getPrice()));

        // Then
        ResponseProductDto expectedProduct = new ResponseProductDto(product.getId(), product.getName(), product.getPrice());

        assertThat(createdProduct).usingRecursiveComparison().isEqualTo(expectedProduct);
        verify(productDao, times(1)).createOrUpdateProduct(any(Product.class));
    }

    @Test
    void updateProduct() {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Updated Product");
        product.setPrice(20.0);

        when(productDao.getProductById(productId)).thenReturn(Optional.of(product));
        when(productDao.createOrUpdateProduct(product)).thenReturn(product);

        // When
        RequestProductDto requestProductDto = new RequestProductDto(product.getName(), product.getPrice());
        ResponseProductDto updatedProduct = productService.updateProduct(productId, requestProductDto);

        // Then
        ResponseProductDto expectedProduct = new ResponseProductDto(product.getId(), product.getName(), product.getPrice());
        assertThat(updatedProduct).usingRecursiveComparison().isEqualTo(expectedProduct);
        verify(productDao, times(1)).getProductById(productId);
        verify(productDao, times(1)).createOrUpdateProduct(product);
    }

    @Test
    void deleteProduct() {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        when(productDao.getProductById(productId)).thenReturn(Optional.of(product));

        // When
        productService.deleteProduct(productId);

        // Then
        verify(productDao, times(1)).deleteProduct(product);
        verify(productDao, times(1)).getProductById(productId);
    }

    @Test
    void productExists() {
        // Given
        Long productId = 1L;
        String productName = "Test";
        when(productDao.productExists(productName)).thenReturn(true);

        // When
        boolean exists = productService.productExists(productName);

        // Then
        assertThat(exists).isTrue();
        verify(productDao, times(1)).productExists(productName);
    }
}
