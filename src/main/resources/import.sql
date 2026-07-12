-- INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100010, 'Drago PC', 'Intel Core i5-14600K', 'RTX 4060', '32GB DDR5', '1TB NVMe SSD', 'NZXT H5 Flow', 999.99, 12);
-- INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100011, 'Tigre PC', 'AMD Ryzen 7 7800X3D', 'RX 7800 XT', '32GB DDR5', '2TB NVMe SSD', 'Fractal Design Meshify 2 Compact', 1599.90, 7);
-- omessi perché inseriti con immagine dalla classe DataLoader
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100012, 'Falco PC', 'Intel Core i7-14700F', 'RTX 4070 SUPER', '32GB DDR5', '1TB NVMe SSD', 'Corsair 4000D Airflow', 1799.00, 5);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100013, 'Lupo PC', 'AMD Ryzen 5 7600', 'RX 7600', '16GB DDR5', '1TB NVMe SSD', 'Cooler Master MasterBox NR600', 899.00, 20);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100014, 'Squalo PC', 'Intel Core i9-14900K', 'RTX 4090', '64GB DDR5', '4TB NVMe SSD', 'Lian Li O11 Dynamic EVO', 4299.99, 2);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100015, 'Orso PC', 'AMD Ryzen 9 7900X', 'RTX 4080 SUPER', '64GB DDR5', '2TB NVMe SSD', 'be quiet! Pure Base 500DX', 2799.00, 3);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100016, 'Cobra PC', 'Intel Core i5-13400F', 'RTX 3060', '16GB DDR4', '1TB NVMe SSD', 'Thermaltake S100', 749.90, 18);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100017, 'Pantera PC', 'AMD Ryzen 7 7700', 'RTX 4070', '32GB DDR5', '2TB NVMe SSD', 'Phanteks Eclipse P400A', 1499.50, 9);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100018, 'Rana PC', 'Intel Core i3-14100', 'Intel UHD Graphics 730', '16GB DDR5', '512GB NVMe SSD', 'Aerocool Cylon', 499.00, 30);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100019, 'Volpe PC', 'AMD Ryzen 5 5600', 'GTX 1660 SUPER', '16GB DDR4', '1TB SATA SSD', 'DeepCool MATREXX 50', 599.99, 25);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100020, 'Gufo PC', 'Intel Core i7-13700K', 'RTX 3080', '32GB DDR5', '2TB NVMe SSD', 'Antec DF700 Flux', 1399.00, 6);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100021, 'Bisonte PC', 'AMD Ryzen 9 7950X3D', 'RX 7900 XTX', '64GB DDR5', '4TB NVMe SSD', 'Corsair 5000D Airflow', 3199.00, 4);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100022, 'Piovra PC', 'Intel Core i5-12600K', 'RX 6700 XT', '32GB DDR4', '1TB NVMe SSD', 'Sharkoon TG5', 999.00, 11);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100023, 'Leone PC', 'AMD Ryzen 7 5700G', 'RTX 3050', '16GB DDR4', '1TB NVMe SSD', 'Cougar MX330', 699.00, 16);
INSERT INTO pc (id, codice, nome, cpu, gpu, ram, rom, case_pc, prezzo, disponibilita) VALUES (nextval('pc_seq'),100009, 'Pollo PC', 'AMD Ryzen 5 8600G', 'Radeon 760M (iGPU)', '16GB DDR5',  '1TB NVMe SSD', 'SilverStone GD09',69.99,67);

INSERT INTO cart (id) VALUES (100000);
INSERT INTO cart_item(id, quantity, cart_id, pc_id) VALUES (nextval('cart_item_seq'), 1, 100000, 1);
INSERT INTO cart_item(id, quantity, cart_id, pc_id) VALUES (nextval('cart_item_seq'), 2, 100000, 51);
INSERT INTO cart (id) VALUES (200000);

INSERT INTO users (id, first_name, last_name, email, date_of_birth, phone_number, cart_id) VALUES (100000, 'Luigi', 'Verdi', 'admin@pcexpress.com','1984-12-21', '1112223333',100000);
INSERT INTO credentials (id, user_id, email, password, role) VALUES (nextval('credentials_seq'), 100000,'admin@pcexpress.com', '$2a$12$oukZc.1ieiF.G6hvVe3Hd.CFfxc3P6Bar5DjbiWMlyZrHIgrcnTTe', 'ADMIN');  --la password è 123456
INSERT INTO users (id, first_name, last_name, email, date_of_birth, phone_number, cart_id) VALUES (200000, 'Mario', 'Rossi', 'a@a.com','2000-12-12', '3332221111',200000);
INSERT INTO credentials (id, user_id, email, password, role) VALUES (nextval('credentials_seq'), 200000,'a@a.com', '$2a$12$oukZc.1ieiF.G6hvVe3Hd.CFfxc3P6Bar5DjbiWMlyZrHIgrcnTTe', 'DEFAULT');  --la password è 123456