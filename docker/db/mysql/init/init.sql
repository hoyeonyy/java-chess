CREATE DATABASE chess;

USE chess;

CREATE TABLE chess_game
(
    id    bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    state varchar(12)
);

create table piece
(
    id     bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    file   char(1),
    `rank` int,
    type   varchar(10),
    color  varchar(5)
);
