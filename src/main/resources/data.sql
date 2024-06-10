-- Insert Drinks
INSERT IGNORE INTO drinks (name, description, price, quantity) VALUES ('Cola', 'Classic cola drink', 1000.5, 100);
INSERT IGNORE INTO drinks (name, description, price, quantity) VALUES ('Lemonade', 'Refreshing lemonade', 1200.0, 150);
INSERT IGNORE INTO drinks (name, description, price, quantity) VALUES ('Orange Juice', 'Freshly squeezed orange juice', 2300.0, 80);

-- Insert client data
INSERT INTO clients (name, email, address, latitude, longitude) 
SELECT 'ABAYEZU Rene Rigobert', 'rigorene48@gmail.com', 'KK ST 106', '123.890', '-140.876'
WHERE NOT EXISTS (SELECT 1 FROM clients WHERE email = 'rigorene48@gmail.com');

INSERT INTO clients (name, email, address, latitude, longitude) 
SELECT 'MUGWANEZA Clement', 'clement@gmail.com', 'KK ST 106', '-13.890', '-10.876'
WHERE NOT EXISTS (SELECT 1 FROM clients WHERE email = 'clement@gmail.com');

INSERT INTO clients (name, email, address, latitude, longitude) 
SELECT 'MUKUNZI Angel', 'angel@gmail.com', 'KK ST 206', '23.890', '-40.876'
WHERE NOT EXISTS (SELECT 1 FROM clients WHERE email = 'angel@gmail.com');




-- Seeding cargo companies
INSERT INTO cargo_company (name, address, longitude, latitude) 
SELECT 'GARU AIR/RWANDA', 'KARURUMA KK 423 ST', '-140.866', '123.902'
WHERE NOT EXISTS (SELECT 1 FROM cargo_company WHERE name = 'GARU AIR/RWANDA');

INSERT INTO cargo_company (name, address, longitude, latitude) 
SELECT 'DHL', 'KARURUMA KK 423 ST', '-10.866', '13.902'
WHERE NOT EXISTS (SELECT 1 FROM cargo_company WHERE name = 'DHL');


