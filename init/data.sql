CREATE DATABASE IF NOT EXISTS `xml`;
USE `xml`;

CREATE TABLE users (
    id int,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    username VARCHAR(255)
);

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('1', 'mail@mail.com', 'Pera', 'Peric', 'admin', 'admin', 'admin');
INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('2', 'mail1@mail.com', 'Pera', 'Peric', 'user', 'user', 'user');
INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('3', 'mail2@mail.com', 'Pera', 'Peric', 'agent', 'agent', 'agent');