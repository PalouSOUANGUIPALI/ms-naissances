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
