INSERT INTO PRODUCT (name, price) VALUES
                                      ('Product A', 10.99),
                                      ('Product B', 12.49),
                                      ('Product C', 8.75);


INSERT INTO SHOPPING_CART (total_price, total_items) VALUES
                                                         (0, 0),
                                                         (0, 0);


INSERT INTO ITEM (product_id, quantity, shopping_cart_id) VALUES
                                                              ((SELECT id FROM PRODUCT WHERE name = 'Product A'), 1, (SELECT id FROM SHOPPING_CART LIMIT 1 OFFSET 0)),
                                                              ((SELECT id FROM PRODUCT WHERE name = 'Product B'), 2, (SELECT id FROM SHOPPING_CART LIMIT 1 OFFSET 0)),
                                                              ((SELECT id FROM PRODUCT WHERE name = 'Product C'), 2, (SELECT id FROM SHOPPING_CART LIMIT 1 OFFSET 1));


WITH cart_totals AS (
    SELECT
        shopping_cart_id,
        SUM(p.price * i.quantity) AS total_price,
        SUM(i.quantity) AS total_items
    FROM
        ITEM i
            JOIN PRODUCT p ON i.product_id = p.id
    GROUP BY
        shopping_cart_id
)
UPDATE SHOPPING_CART sc
SET
    total_price = ct.total_price,
    total_items = ct.total_items
FROM
    cart_totals ct
WHERE
    sc.id = ct.shopping_cart_id;
