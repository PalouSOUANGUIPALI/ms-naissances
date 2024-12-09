-- Création de la table companies
create table companies
(
    id int auto_increment primary key,
    name varchar(255),
    description text,
    address_id int,
    creation datetime default current_timestamp,
    constraint fk_companies_addresses foreign key(address_id) references addresses(id)
);
