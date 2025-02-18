-- Création de la table cityhalls
create table status
(
    id int auto_increment primary key,
    name varchar(255),
    description text,
    creation datetime default current_timestamp
);

INSERT INTO status (`name`)
VALUES
    ('NEW'),
    ('ON_GOING'),
    ('REJECTED'),
    ('VALIDATED');