-- Création de la table roles
/*create table roles
(
    id int auto_increment primary key,
    name varchar(255) not null,
    description text,
    creation datetime default current_timestamp
);

-- Insertion des rôles AGENT, ADMINISTRATOR et PUBLIC avec leurs descriptions
insert into roles (name, description)
values
    ('AGENT', 'Rôle attribué aux individus ayant un accès de base, généralement pour gérer des tâches courantes'),
    ('ADMINISTRATOR', 'Rôle attribué aux individus ayant un accès administratif complet pour gérer les paramètres du système et les utilisateurs'),
    ('PUBLIC', 'Rôle attribué à tous les utilisateurs généraux avec un accès limité pour visualiser les données publiques');
*/