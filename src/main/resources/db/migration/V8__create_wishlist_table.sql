CREATE TABLE IF NOT EXISTS wishlist(
    user_id bigint NOT NULL,
    product_id bigint NOT NULL,
    PRIMARY KEY (user_id, product_id),
    CONSTRAINT fk_wishlist_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_wishlist_product
        FOREIGN KEY (product_id) REFERENCES products(id)
        ON DELETE CASCADE
)