-- Création de la table status
create table status
(
    id int auto_increment primary key,
    name varchar(255),
    description text,
    creation datetime default current_timestamp
);


-- Insérer des données dans la table `status`
INSERT INTO status (name)
VALUES
    ('NEW'),
    ('ON_GOING' ),
    ('VALIDATED'),
    ('REJECTED');
