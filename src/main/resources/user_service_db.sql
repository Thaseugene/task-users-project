CREATE SCHEMA IF NOT EXISTS user_service_db;

USE user_service_db;

CREATE TABLE `users`
(
    id          INT auto_increment              not null primary key,
    surname     VARCHAR(255)                    not null,
    name        VARCHAR(255)                    not null,
    middle_name VARCHAR(255)                    not null,
    email       VARCHAR(255)                    not null,
    user_role   ENUM ('ADMINISTRATOR', 'SALE_USER', 'CUSTOMER_USER', 'SECURE_API_USER') not null

)
    ENGINE = InnoDB;