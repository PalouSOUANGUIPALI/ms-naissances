
-- Création de la table cityhalls
create table cityhalls
(
    id int auto_increment primary key,
    name varchar(255),
    description text,
    address_id int,
    creation datetime default current_timestamp,
    constraint fk_cityhalls_addresses foreign key(address_id) references addresses(id)
);