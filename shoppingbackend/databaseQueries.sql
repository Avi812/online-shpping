CREATE TABLE category (
    id IDENTITY,
    name VARCHAR(50),
    description VARCHAR(255),
    image_url VARCHAR(50),
    is_active BOOLEAN
    constraint pk_category_id PRIMARY KEY (id)
);