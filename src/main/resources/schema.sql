CREATE TABLE IF NOT EXISTS customer (
  id       BIGINT PRIMARY KEY    AUTO_INCREMENT,
  email    VARCHAR(80)  NOT NULL,
  name     VARCHAR(200) NOT NULL,
  address  VARCHAR(500) NOT NULL,
  creation DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  password VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS cousine (
  id   BIGINT PRIMARY KEY    AUTO_INCREMENT,
  name VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS store (
  id         BIGINT PRIMARY KEY    AUTO_INCREMENT,
  name       VARCHAR(200) NOT NULL,
  address    VARCHAR(400) NOT NULL,
  cousine_id BIGINT       NOT NULL,

  CONSTRAINT fk_store_cousine_id FOREIGN KEY (cousine_id) REFERENCES store (id)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS product (
  id          BIGINT PRIMARY KEY    AUTO_INCREMENT,
  store_id    BIGINT NOT NULL,
  name        VARCHAR(300),
  description VARCHAR(500),
  price       DOUBLE,

  CONSTRAINT fk_product_store_id FOREIGN KEY (store_id) REFERENCES store (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS skip_order (
  id               BIGINT PRIMARY KEY                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                AUTO_INCREMENT,
  date             DATETIME     NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         DEFAULT CURRENT_TIMESTAMP,
  customer_id      BIGINT       NOT NULL,
  delivery_address VARCHAR(500) NOT NULL,
  contact          VARCHAR(30)  NOT NULL,
  store_id         BIGINT       NOT NULL,
  total            DOUBLE,
  status           VARCHAR(20)  NOT NULL,
  last_update      DATETIME,

  CONSTRAINT fk_skip_customer_id FOREIGN KEY (customer_id) REFERENCES customer (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_skip_store_id FOREIGN KEY (store_id) REFERENCES store (id)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS order_item (
  id         BIGINT PRIMARY KEY    AUTO_INCREMENT,
  order_id   BIGINT NOT NULL,
  product_id BIGINT NOT NULL,
  price      DOUBLE NOT NULL,
  quantity   INT    NOT NULL,
  total      DOUBLE,

  CONSTRAINT fk_order_item_id FOREIGN KEY (order_id) REFERENCES skip_order (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT fk_order_item_product_id FOREIGN KEY (product_id) REFERENCES product (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)