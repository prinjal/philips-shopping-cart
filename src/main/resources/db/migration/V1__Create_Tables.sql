CREATE TABLE PRODUCTS (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255) UNIQUE NOT NULL,
                          price DOUBLE PRECISION NOT NULL
);

CREATE TABLE SHOPPING_CARTS (
                                id BIGSERIAL PRIMARY KEY,
                                total_price DOUBLE PRECISION,
                                total_items INTEGER
);

CREATE TABLE ITEMS (
                       id BIGSERIAL PRIMARY KEY,
                       product_id BIGINT NOT NULL,
                       quantity INTEGER NOT NULL,
                       shopping_cart_id BIGINT NOT NULL,
                       CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES PRODUCTS(id),
                       CONSTRAINT fk_shopping_cart FOREIGN KEY (shopping_cart_id) REFERENCES SHOPPING_CARTS(id)
);

CREATE TABLE USERS (
                       id BIGSERIAL PRIMARY KEY,
                       full_name VARCHAR(255),
                       email VARCHAR(100) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       shopping_cart_id BIGINT UNIQUE,
                       CONSTRAINT fk_shopping_cart FOREIGN KEY (shopping_cart_id) REFERENCES SHOPPING_CARTS(id)
);