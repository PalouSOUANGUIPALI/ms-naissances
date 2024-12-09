/*
    Insertion des data dans les tables correspondantes sans les jointures avec la tables `addresses`:
    Entités : profiles, addresses, companies, cityhalls
 */
 -- Insertion des données dans la table address
/*INSERT INTO addresses (`tag`,`street`,`zip`,`city`,`country`)
VALUES
    ('COMPANY','704 Ainslie Street','179','Marienthal','New Mexico'),
    ('COMPANY','425 Vermont Court','702','Mulberry','Montana'),
    ('CITY_HALL','118 Eastern Parkway','459','Connerton','Utah'),
    ('CITY_HALL','596 Folsom Place','917','Greenwich','Alabama'),
    ('CITY_HALL','341 Tehama Street','496','Muir','Ohio');

 */

-- Insertion des données dans la table companies
/*INSERT INTO companies (`name`,`description`)
VALUES
    ('ipsum','esse'),
    ('duis','minim'),
    ('in','occaecat'),
    ('magna','eiusmod'),
    ('id','id'),
    ('ullamco','adipisicing'),
    ('veniam','ipsum');


 */

-- Insertion des données dans la table cityhalls
/*INSERT INTO cityhalls (`name`,`description`)
VALUES
    ('ipsum','esse'),
    ('duis','minim'),
    ('in','occaecat'),
    ('magna','eiusmod'),
    ('id','id'),
    ('ullamco','adipisicing'),
    ('veniam','ipsum');

 */


-- Insertion des données dans la table profiles sans les addresses associées
/*INSERT INTO profiles (`civility`,`first_name`,`last_name`,`email`,`phone`,`password`)
VALUES
    ('MLLE','Lisa','Farrell','lisafarrell@goko.com','(832) 425-2296','fugiat'),
    ('MR','Harrington','Casey','harringtoncasey@goko.com','(883) 554-3469','aliquip'),
    ('MLLE','Hilary','Marshall','hilarymarshall@goko.com','(891) 508-3801','laborum'),
    ('MLLE','Deborah','Dean','deborahdean@goko.com','(887) 587-2226','proident'),
    ('MR','Mari','Mcpherson','marimcpherson@goko.com','(806) 466-2450','commodo'),
    ('MME','Oneil','Goodwin','oneilgoodwin@goko.com','(863) 532-2693','ex'),
    ('MME','Preston','Shields','prestonshields@goko.com','(982) 496-3833','nulla');

 */



/*
    Insertion des profiles, companies et les cityhalls avec les jointures à la table `addresses` associée
 */
-- Insérer les adresses dans la table `addresses`
INSERT INTO addresses (street, zip, city, country, tag)
VALUES
    ('486 Canarsie Road', 154, 'Emory', 'Nebraska', 'COMPANY'),
    ('288 Troutman Street', 356, 'Corinne', 'Palau', 'COMPANY'),
    ('113 Kay Court', 838, 'Nicholson', 'Kansas', 'CITY_HALL'),
    ('737 Kansas Place', 409, 'Morgandale', 'Virginia', 'CITY_HALL'),
    ('885 Lawrence Street', 549, 'Glenville', 'Oregon', 'COMPANY'),
    ('766 Folsom Place', 315, 'Weeksville', 'Puerto Rico', 'CITY_HALL'),
    ('933 Glenmore Avenue', 217, 'Lowell', 'Massachusetts', 'COMPANY');

-- Insérer les utilisateurs dans `profile`, avec des sous-requêtes pour récupérer l'ID de l'adresse
INSERT INTO profiles (civility, first_name, last_name, email, phone, password, addresses_id)
VALUES
    ('MLLE', 'Richards', 'Ortega', 'richardsortega@goko.com', '(862) 405-3519', 'ea', (SELECT id FROM addresses WHERE street = '486 Canarsie Road' AND zip = 154 AND city = 'Emory' AND country = 'Nebraska' LIMIT 1)),
    ('MLLE', 'Barton', 'Lancaster', 'bartonlancaster@goko.com', '(955) 517-3664', 'enim', (SELECT id FROM addresses WHERE street = '288 Troutman Street' AND zip = 356 AND city = 'Corinne' AND country = 'Palau' LIMIT 1)),
    ('MR', 'Norton', 'Morin', 'nortonmorin@goko.com', '(907) 434-2273', 'fugiat', (SELECT id FROM addresses WHERE street = '113 Kay Court' AND zip = 838 AND city = 'Nicholson' AND country = 'Kansas' LIMIT 1)),
    ('MR', 'Jaclyn', 'Lyons', 'jaclynlyons@goko.com', '(841) 537-3420', 'occaecat', (SELECT id FROM addresses WHERE street = '737 Kansas Place' AND zip = 409 AND city = 'Morgandale' AND country = 'Virginia' LIMIT 1)),
    ('MLLE', 'Pollard', 'Cotton', 'pollardcotton@goko.com', '(833) 429-3594', 'magna', (SELECT id FROM addresses WHERE street = '885 Lawrence Street' AND zip = 549 AND city = 'Glenville' AND country = 'Oregon' LIMIT 1)),
    ('MME', 'Young', 'Blevins', 'youngblevins@goko.com', '(920) 439-2262', 'ullamco', (SELECT id FROM addresses WHERE street = '766 Folsom Place' AND zip = 315 AND city = 'Weeksville' AND country = 'Puerto Rico' LIMIT 1)),
    ('MR', 'Pearl', 'Mckay', 'pearlmckay@goko.com', '(930) 495-2367', 'deserunt', (SELECT id FROM addresses WHERE street = '933 Glenmore Avenue' AND zip = 217 AND city = 'Lowell' AND country = 'Massachusetts' LIMIT 1));



-- Insérer des companies dans la table `companies` avec des sous-requêtes pour récupérer l'ID de l'adresse
INSERT INTO companies (name, description, address_id)
VALUES
    ('ipsum', 'esse', (SELECT id FROM addresses WHERE street = '486 Canarsie Road' AND zip = 154 AND city = 'Emory' AND country = 'Nebraska' LIMIT 1)),
    ('duis', 'minim', (SELECT id FROM addresses WHERE street = '288 Troutman Street' AND zip = 356 AND city = 'Corinne' AND country = 'Palau' LIMIT 1)),
    ('in', 'occaecat', (SELECT id FROM addresses WHERE street = '113 Kay Court' AND zip = 838 AND city = 'Nicholson' AND country = 'Kansas' LIMIT 1)),
    ('magna', 'eiusmod', (SELECT id FROM addresses WHERE street = '737 Kansas Place' AND zip = 409 AND city = 'Morgandale' AND country = 'Virginia' LIMIT 1)),
    ('id', 'id', (SELECT id FROM addresses WHERE street = '885 Lawrence Street' AND zip = 549 AND city = 'Glenville' AND country = 'Oregon' LIMIT 1)),
    ('ullamco', 'adipisicing', (SELECT id FROM addresses WHERE street = '766 Folsom Place' AND zip = 315 AND city = 'Weeksville' AND country = 'Puerto Rico' LIMIT 1)),
    ('veniam', 'ipsum', (SELECT id FROM addresses WHERE street = '933 Glenmore Avenue' AND zip = 217 AND city = 'Lowell' AND country = 'Massachusetts' LIMIT 1));


-- Insérer des mairies dans la table `cityhalls` avec des sous-requêtes pour récupérer l'ID de l'adresse
INSERT INTO cityhalls (name, description, address_id)
VALUES
    -- ('ipsum', 'esse', (SELECT id FROM addresses WHERE street = '486 Canarsie Road' AND zip = 154 AND city = 'Emory' AND country = 'Nebraska' AND tag = 'CITY_HALL' LIMIT 1)),
    -- ('duis', 'minim', (SELECT id FROM addresses WHERE street = '288 Troutman Street' AND zip = 356 AND city = 'Corinne' AND country = 'Palau' AND tag = 'CITY_HALL' LIMIT 1)),
    ('in', 'occaecat', (SELECT id FROM addresses WHERE street = '113 Kay Court' AND zip = 838 AND city = 'Nicholson' AND country = 'Kansas' AND tag = 'CITY_HALL' LIMIT 1)),
    ('magna', 'eiusmod', (SELECT id FROM addresses WHERE street = '737 Kansas Place' AND zip = 409 AND city = 'Morgandale' AND country = 'Virginia' AND tag = 'CITY_HALL' LIMIT 1)),
    -- ('id', 'id', (SELECT id FROM addresses WHERE street = '885 Lawrence Street' AND zip = 549 AND city = 'Glenville' AND country = 'Oregon' AND tag = 'CITY_HALL' LIMIT 1)),
    ('ullamco', 'adipisicing', (SELECT id FROM addresses WHERE street = '766 Folsom Place' AND zip = 315 AND city = 'Weeksville' AND country = 'Puerto Rico' AND tag = 'CITY_HALL' LIMIT 1));
    -- ('veniam', 'ipsum', (SELECT id FROM addresses WHERE street = '933 Glenmore Avenue' AND zip = 217 AND city = 'Lowell' AND country = 'Massachusetts' AND tag = 'CITY_HALL' LIMIT 1));


