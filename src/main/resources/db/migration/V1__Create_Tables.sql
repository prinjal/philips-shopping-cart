CREATE TABLE PRODUCT (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) UNIQUE NOT NULL,
                         price DOUBLE PRECISION NOT NULL
);

CREATE TABLE SHOPPING_CART (
                              id BIGSERIAL PRIMARY KEY,
                              total_price DOUBLE PRECISION,
                              total_items INTEGER
);

CREATE TABLE ITEM (
                      id BIGSERIAL PRIMARY KEY,
                      product_id BIGINT NOT NULL,
                      quantity INTEGER NOT NULL,
                      shopping_cart_id BIGINT NOT NULL,
                      CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES PRODUCT(id),
                      CONSTRAINT fk_shopping_cart FOREIGN KEY (shopping_cart_id) REFERENCES SHOPPING_CART(id)
);
