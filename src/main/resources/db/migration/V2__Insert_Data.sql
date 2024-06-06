INSERT INTO PRODUCTS (name, price) VALUES
                                       ('Product A', 10.99),
                                       ('Product B', 12.49),
                                       ('Product C', 8.75);

INSERT INTO SHOPPING_CARTS (total_price, total_items) VALUES
                                                          (0, 0),
                                                          (0, 0);

INSERT INTO USERS (full_name, email, password) VALUES
                                                   ('John Doe', 'john.doe@example.com', '$2a$10$FnKMVVQA0mLB5fNUb5kOsOnKC8TPA8eC2jRoH1Z8hnGDhKhsbmGy2'), --password123
                                                   ('Jane Smith', 'jane.smith@example.com', '$2a$10$2VfItA4IJnzGbwVJ3AbleuSia6LGpQPYFi5z1hQrvS3HdM748/Z7a'); --password123

UPDATE USERS SET shopping_cart_id = (SELECT id FROM SHOPPING_CARTS LIMIT 1 OFFSET 0) WHERE email = 'john.doe@example.com';
UPDATE USERS SET shopping_cart_id = (SELECT id FROM SHOPPING_CARTS LIMIT 1 OFFSET 1) WHERE email = 'jane.smith@example.com';

INSERT INTO ITEMS (product_id, quantity, shopping_cart_id) VALUES
                                                               ((SELECT id FROM PRODUCTS WHERE name = 'Product A'), 1, (SELECT shopping_cart_id FROM USERS WHERE email = 'john.doe@example.com')),
                                                               ((SELECT id FROM PRODUCTS WHERE name = 'Product B'), 2, (SELECT shopping_cart_id FROM USERS WHERE email = 'john.doe@example.com')),
                                                               ((SELECT id FROM PRODUCTS WHERE name = 'Product C'), 2, (SELECT shopping_cart_id FROM USERS WHERE email = 'jane.smith@example.com'));

WITH cart_totals AS (
    SELECT
        shopping_cart_id,
        SUM(p.price * i.quantity) AS total_price,
        SUM(i.quantity) AS total_items
    FROM
        ITEMS i
            JOIN PRODUCTS p ON i.product_id = p.id
    GROUP BY
        shopping_cart_id
)
UPDATE SHOPPING_CARTS sc
SET
    total_price = ct.total_price,
    total_items = ct.total_items
FROM
    cart_totals ct
WHERE
    sc.id = ct.shopping_cart_id;