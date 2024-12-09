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
