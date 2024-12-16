-- Création de la table roles
create table roles
(
    id int auto_increment primary key,
    name varchar(255),
    description text,
    creation datetime default current_timestamp
);

-- Insertion des roles et description dans la table roles
INSERT INTO roles (name, description)
VALUES
    ('AGENT', 'Rôle attribué aux agents responsables de certaines tâches dans le système.'),
    ('ADMINISTRATOR', 'Rôle avec des privilèges complets pour gérer tous les aspects du système.'),
    ('PUBLIC', 'Rôle attribué aux utilisateurs externes ou visiteurs avec des privilèges limités.');


-- Création de la table permissions
create table permissions
(
    id int auto_increment primary key,
    name varchar(255),
    description text,
    creation datetime default current_timestamp
);

-- Insertions des permissions dans la table permissions
INSERT INTO permissions (name, description)
VALUES
    -- Permissions pour le rôle AGENT
    ('AGENT_CREATE', "Permission d\'ajouter ou créer des ressources en tant qu\'agent."),
    ('AGENT_READ', "Permission de lire les ressources en tant qu\'agent."),
    ('AGENT_UPDATE', "Permission de mettre à jour les ressources en tant qu\'agent."),
    ('AGENT_DELETE', "Permission de supprimer des ressources en tant qu\'agent."),

    -- Permissions pour le rôle ADMINISTRATOR
    ('ADMINISTRATOR_CREATE', "Permission d\'ajouter ou créer des ressources en tant qu\'administrateur."),
    ('ADMINISTRATOR_READ', "Permission de lire toutes les ressources en tant qu\'administrateur."),
    ('ADMINISTRATOR_UPDATE', "Permission de mettre à jour toutes les ressources en tant qu\'administrateur."),
    ('ADMINISTRATOR_DELETE', "Permission de supprimer toutes les ressources en tant qu\'administrateur."),

    -- Permissions pour le rôle PUBLIC
    ('PUBLIC_READ', "Permission de lire les ressources publiques en tant qu\'utilisateur public."),
    ('PUBLIC_CREATE', "Permission de créer des ressources en tant qu\'utilisateur public (si autorisé par l\'application)."),
    ('PUBLIC_UPDATE', "Permission de mettre à jour des ressources publiques en tant qu\'utilisateur public (si autorisé par l\'application)."),
    ('PUBLIC_DELETE', "Permission de supprimer des ressources publiques en tant qu\'utilisateur public (si autorisé par l\'application)."),

    -- Permissions pour le rôle PROFILE
    ('PROFILE_CREATE', 'Permission de créer un profil utilisateur.'),
    ('PROFILE_READ', "Permission de lire les informations d\'un profil utilisateur."),
    ('PROFILE_UPDATE', "Permission de mettre à jour les informations d\'un profil utilisateur."),
    ('PROFILE_DELETE', 'Permission de supprimer un profil utilisateur.'),

    -- Permissions pour les déclarations (ex : déclaration de naissance)
    ('DECLARATION_CREATE', 'Permission de déclarer une naissance (ajouter une nouvelle déclaration de naissance).'),
    ('DECLARATION_READ', "Permission de lire les informations d\'une déclaration de naissance existante."),
    ('DECLARATION_UPDATE', "Permission de mettre à jour les informations d\'une déclaration de naissance."),
    ('DECLARATION_DELETE', "Permission de supprimer une déclaration de naissance (en cas d\'erreur ou d\'annulation)."),

    -- Permissions pour les demandes (ex : demandes de certificats de naissance)
    ('REQUEST_CREATE', 'Permission de créer une demande (ex : demande de certificat de naissance).'),
    ('REQUEST_READ', "Permission de lire les informations d\'une demande existante."),
    ('REQUEST_UPDATE', "Permission de mettre à jour les informations d\'une demande (ex : mise à jour des détails de la demande)."),
    ('REQUEST_DELETE', 'Permission de supprimer une demande (si annulée ou erronée).');


-- Création de la table roles_permissions @Join @ManyToMany
create table roles_permissions
(
    id int auto_increment primary key,
    roles_id int,
    permissions_id int,
    constraint fk_roles_permissions_roles foreign key(roles_id) references roles(id),
    constraint fk_roles_permissions_permissions foreign key(permissions_id) references permissions(id),
    constraint unique_roles_permissions unique (roles_id, permissions_id)
);

-- Insertion des données dans la table roles_permissions
-- Associations des rôles avec leurs permissions pour chaque rôle

-- ADMINISTRATOR
INSERT INTO roles_permissions (roles_id, permissions_id)
VALUES
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'ADMINISTRATOR_CREATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'ADMINISTRATOR_READ')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'ADMINISTRATOR_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'ADMINISTRATOR_DELETE')),

    -- /////////////////////     Les actions que le role ADMINISTRATOR peut faire sur les autre objet    ///////////////////////
    -- Les actions que l'adminsitrateur peut faire sur l'agent
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'AGENT_CREATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'AGENT_READ')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'AGENT_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'AGENT_DELETE')),

    -- Les actions que l'adminsitrateur peut faire sur le profile
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'PROFILE_READ')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'PROFILE_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'PROFILE_DELETE')),

    -- Les actions que l'adminsitrateur peut faire sur le public et/ou visiteur
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'PUBLIC_READ')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'PUBLIC_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'PUBLIC_DELETE')),

    -- Les actions que l'adminsitrateur peut faire sur la declaration
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'DECLARATION_CREATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'DECLARATION_READ')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'DECLARATION_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'DECLARATION_DELETE')),

    -- Les actions que l'adminsitrateur peut faire sur la request (demande)
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'REQUEST_CREATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'REQUEST_READ')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'REQUEST_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'ADMINISTRATOR'), (SELECT id FROM permissions WHERE name = 'REQUEST_DELETE')),

    -- /////////////////////     Les actions que le role Agent peut faire sur les autre objet    ///////////////////////
    -- Les actions que l'Agent peut faire sur lui même
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_CREATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_READ')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_DELETE')),

    -- Les actions que l'Agent peut faire sur le profile
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'PROFILE_READ')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'PROFILE_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'PROFILE_DELETE')),

    -- Les actions que l'Agent peut faire sur le public et/ou visiteur
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'PUBLIC_READ')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'PUBLIC_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'PUBLIC_DELETE')),

    -- Les actions que l'Agent peut faire sur la declaration
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'DECLARATION_CREATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'DECLARATION_READ')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'DECLARATION_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'DECLARATION_DELETE')),

    -- Les actions que l'Agent peut faire sur la request (demande)
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'REQUEST_CREATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'REQUEST_READ')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'REQUEST_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'REQUEST_DELETE')),


    -- /////////////////////     Les actions que le role PUBLIC peut faire sur les autre objet    ///////////////////////
    -- Les actions que le role PUBLIC peut faire sur lui même
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PUBLIC_READ')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PUBLIC_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PUBLIC_DELETE')),

    -- Les actions que le role PUBLIC peut faire sur la declaration
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'DECLARATION_CREATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'DECLARATION_READ')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'DECLARATION_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'DECLARATION_DELETE')),

    -- Les actions que le role PUBLIC peut faire sur la request (demande)
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'REQUEST_CREATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'REQUEST_READ')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'REQUEST_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'REQUEST_DELETE'));











/*
-- PROFILE
    ((SELECT id FROM roles WHERE name = 'PROFILE'), (SELECT id FROM permissions WHERE name = 'PROFILE_CREATE')),
    ((SELECT id FROM roles WHERE name = 'PROFILE'), (SELECT id FROM permissions WHERE name = 'PROFILE_READ')),
    ((SELECT id FROM roles WHERE name = 'PROFILE'), (SELECT id FROM permissions WHERE name = 'PROFILE_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'PROFILE'), (SELECT id FROM permissions WHERE name = 'PROFILE_DELETE')),

    -- Les actions que le role PUBLIC peut faire sur le profile
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PROFILE_READ')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PROFILE_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PROFILE_DELETE')),


    -- AGENT
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_CREATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_READ')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'AGENT'), (SELECT id FROM permissions WHERE name = 'AGENT_DELETE')),

    -- PUBLIC
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PUBLIC_CREATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PUBLIC_READ')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PUBLIC_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'PUBLIC'), (SELECT id FROM permissions WHERE name = 'PUBLIC_DELETE')),

    -- DECLARATION
    ((SELECT id FROM roles WHERE name = 'DECLARATION'), (SELECT id FROM permissions WHERE name = 'DECLARATION_CREATE')),
    ((SELECT id FROM roles WHERE name = 'DECLARATION'), (SELECT id FROM permissions WHERE name = 'DECLARATION_READ')),
    ((SELECT id FROM roles WHERE name = 'DECLARATION'), (SELECT id FROM permissions WHERE name = 'DECLARATION_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'DECLARATION'), (SELECT id FROM permissions WHERE name = 'DECLARATION_DELETE')),

    -- DEMANDE
    ((SELECT id FROM roles WHERE name = 'DEMANDE'), (SELECT id FROM permissions WHERE name = 'DEMANDE_CREATE')),
    ((SELECT id FROM roles WHERE name = 'DEMANDE'), (SELECT id FROM permissions WHERE name = 'DEMANDE_READ')),
    ((SELECT id FROM roles WHERE name = 'DEMANDE'), (SELECT id FROM permissions WHERE name = 'DEMANDE_UPDATE')),
    ((SELECT id FROM roles WHERE name = 'DEMANDE'), (SELECT id FROM permissions WHERE name = 'DEMANDE_DELETE'));


 */


