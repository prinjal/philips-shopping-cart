package com.philips.shoppingcart.service.shoppingcart.impl;

import com.philips.shoppingcart.ShoppingcartApplication;
import com.philips.shoppingcart.dao.item.ItemDao;
import com.philips.shoppingcart.dao.product.ProductDao;
import com.philips.shoppingcart.dao.shoppingcart.ShoppingCartDao;
import com.philips.shoppingcart.dto.item.RequestItemDto;
import com.philips.shoppingcart.dto.item.ResponseItemDto;
import com.philips.shoppingcart.dto.product.ResponseProductDto;
import com.philips.shoppingcart.dto.shoppingcart.RequestShoppingCartDto;
import com.philips.shoppingcart.dto.shoppingcart.ResponseShoppingCartDto;
import com.philips.shoppingcart.exceptions.ResourceNotFound;
import com.philips.shoppingcart.model.Item;
import com.philips.shoppingcart.model.Product;
import com.philips.shoppingcart.model.ShoppingCart;
import com.philips.shoppingcart.service.shoppingcart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDao shoppingCartDao;
    private final ItemDao itemDao;
    private final ProductDao productDao;

    @Override
    public List<ResponseShoppingCartDto> getAllShoppingCarts() {
        return shoppingCartDao.getAllShoppingCarts().stream().map(
                this::convertToDto
        ).collect(Collectors.toList());
    }

    @Override
    public ResponseShoppingCartDto createShoppingCart(RequestShoppingCartDto requestShoppingCartDto) {
        ShoppingCart shoppingCart = new ShoppingCart();

        List<Item> items = requestShoppingCartDto.getItemIds().stream()
                .map(itemId -> itemDao.getItemById(itemId)
                        .orElseThrow(() -> new ResourceNotFound("Item not found with id: " + itemId)))
                .collect(Collectors.toList());

        shoppingCart.setItems(items);
        ShoppingCart savedCart = shoppingCartDao.saveShoppingCart(shoppingCart);

        return convertToDto(savedCart);
    }

    @Override
    public ResponseShoppingCartDto getShoppingCartById(Long id) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartById(id)
                .orElseThrow(() -> new ResourceNotFound("Shopping Cart not found"));
        return convertToDto(shoppingCart);
    }

    private ShoppingCart getShoppingCartEntityById(Long id) {
        return shoppingCartDao.getShoppingCartById(id)
                .orElseThrow(() -> new ResourceNotFound("Shopping Cart not found"));
    }

    @Override
    public ResponseShoppingCartDto addItemToCart(Long cartId, RequestItemDto itemDto) {
        ShoppingCart shoppingCart = getShoppingCartEntityById(cartId);

        Product product = productDao.getProductById(itemDto.getProductId())
                .orElseThrow(() -> new ResourceNotFound("Product not found with id: " + itemDto.getProductId()));

        Item item = new Item();
        item.setProduct(product);
        item.setQuantity(itemDto.getQuantity());
        item.setShoppingCart(shoppingCart);

        shoppingCart.getItems().add(item);

        ShoppingCart updatedCart = shoppingCartDao.saveShoppingCart(shoppingCart);

        return updateCartTotals(updatedCart.getId());
    }

    @Override
    public ResponseShoppingCartDto updateItemInCart(Long cartId, Long itemId, RequestItemDto itemDto) {
        ShoppingCart shoppingCart = getShoppingCartEntityById(cartId);
        Item item = shoppingCart.getItems().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFound("Item not found with id: " + itemId));

        Product product = productDao.getProductById(itemDto.getProductId())
                .orElseThrow(() -> new ResourceNotFound("Product not found with id: " + itemDto.getProductId()));

        item.setProduct(product);
        item.setQuantity(itemDto.getQuantity());

        ShoppingCart updatedCart = shoppingCartDao.saveShoppingCart(shoppingCart);
        return updateCartTotals(updatedCart.getId());
    }

    @Override
    public ResponseShoppingCartDto removeItemFromCart(Long cartId, Long itemId) {
        ShoppingCart shoppingCart = getShoppingCartEntityById(cartId);
        shoppingCart.getItems().removeIf(item -> item.getId().equals(itemId));
        ShoppingCart updatedCart = shoppingCartDao.saveShoppingCart(shoppingCart);
        return updateCartTotals(updatedCart.getId());
    }

    @Override
    public List<Item> getAllItemsInCart(Long cartId) {
        return getShoppingCartEntityById(cartId).getItems();
    }

    @Override
    public ResponseShoppingCartDto updateCartTotals(Long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCartEntityById(shoppingCartId);
        double totalPrice = shoppingCart.getItems().stream()
                .mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice())
                .sum();
        int totalItems = shoppingCart.getItems().stream()
                .mapToInt(Item::getQuantity)
                .sum();
        shoppingCart.setTotalPrice(totalPrice);
        shoppingCart.setTotalItems(totalItems);
        ShoppingCart updatedCart = shoppingCartDao.saveShoppingCart(shoppingCart);
        return convertToDto(updatedCart);
    }

    private ResponseShoppingCartDto convertToDto(ShoppingCart shoppingCart) {
        List<ResponseItemDto> responseItemDtos = shoppingCart.getItems().stream()
                .map(item -> new ResponseItemDto(item.getId(), new ResponseProductDto
                        (item.getProduct().getId(), item.getProduct().getName(), item.getProduct().getPrice())
                        , item.getQuantity(),item.getShoppingCart().getId()))
                .collect(Collectors.toList());

        return new ResponseShoppingCartDto(
                shoppingCart.getId(),
                responseItemDtos,
                shoppingCart.getTotalPrice(),
                shoppingCart.getTotalItems()
        );
    }
}
