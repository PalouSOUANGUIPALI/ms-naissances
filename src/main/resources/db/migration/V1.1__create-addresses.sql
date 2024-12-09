-- Création de la table adresses
create table addresses
(
    id int auto_increment primary key,
    tag varchar(30),
    zip varchar(30),
    street varchar(255),
    city varchar(255),
    country varchar(100),
    creation datetime default current_timestamp
);