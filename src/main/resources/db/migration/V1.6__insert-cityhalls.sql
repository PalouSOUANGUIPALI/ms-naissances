
INSERT INTO cityhalls (`name`, `address_id`)
VALUES
    ('irure aute non', (SELECT ID FROM  addresses WHERE tag = 'CITY_HALL' LIMIT 1)),
    ('amet et veniam', (SELECT ID FROM  addresses WHERE tag = 'CITY_HALL' LIMIT 1));