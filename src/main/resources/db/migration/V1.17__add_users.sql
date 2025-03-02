-- Création des utilisateurs de l'administration du système
insert into profiles(first_name, last_name, password, email, active, roles_id)
values
    ('Agent', 'digital', '$2a$10$LGnVGCykQ4kmTtTOzZZ1yOxL9C7RaapWBoITgB3Y0s1k.6EyqMybW', 'asp.digital@gmail.com', true, (select  id from roles where  name = 'AGENT')),
    ('Admin', 'admin_digital', '$2a$10$LGnVGCykQ4kmTtTOzZZ1yOxL9C7RaapWBoITgB3Y0s1k.6EyqMybW', 'admin_asp.digital@gmail.com', true, (select  id from roles where  name = 'ADMINISTRATOR'));
