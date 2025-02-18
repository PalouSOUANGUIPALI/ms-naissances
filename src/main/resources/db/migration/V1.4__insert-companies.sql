-- Insertion des données dans la table companies
INSERT INTO companies (`name`, `address_id`)
VALUES
    ('exercitation dolor ea', (SELECT ID FROM  addresses WHERE tag = 'COMPANY' LIMIT 1)),
    ('adipisicing qui duis', (SELECT ID FROM  addresses WHERE tag = 'COMPANY' LIMIT 1)),
    ('adipisicing qui duis', (SELECT ID FROM  addresses WHERE tag = 'COMPANY' LIMIT 1));