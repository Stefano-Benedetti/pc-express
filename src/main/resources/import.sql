INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100001, 'Gaming PC 1', 'AMD Ryzen 5 7600X', 'NVIDIA GeForce RTX 4070', '32GB DDR5', 'MSI B650 Tomahawk', '1TB NVMe SSD', 'NZXT H5 Flow', 'Corsair RM750e', 'Windows 11 Pro', 'Noctua NH-D15', '3x 120mm PWM',999.99,30);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100002, 'Workstation 1', 'Intel Core i7-14700K', 'NVIDIA RTX 4060 Ti', '64GB DDR5', 'ASUS Z790 TUF', '2TB NVMe SSD', 'Fractal Design Define 7', 'Seasonic Focus GX-850', 'Windows 11 Pro', 'be quiet! Dark Rock Pro 4', '4x 140mm PWM',2,0);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100003, 'Budget PC 1', 'AMD Ryzen 5 5600', 'AMD Radeon RX 6600', '16GB DDR4', 'Gigabyte B550M DS3H', '512GB NVMe SSD', 'Cooler Master Q300L', 'EVGA 600 W1', 'Windows 10 Home', 'AMD Wraith Stealth', '2x 120mm',999999.99,1000);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100004, 'Gaming PC 2', 'Intel Core i5-14600K', 'NVIDIA GeForce RTX 4080 SUPER', '32GB DDR5', 'Gigabyte Z790 AORUS Elite', '2TB NVMe SSD', 'Lian Li Lancool III', 'Corsair RM850x', 'Windows 11 Pro', 'Arctic Liquid Freezer II 360', '6x 120mm',0,1);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100005, 'Mini-ITX 1', 'AMD Ryzen 7 7800X3D', 'NVIDIA GeForce RTX 4070 SUPER', '32GB DDR5', 'ASUS ROG Strix B650E-I', '1TB NVMe SSD', 'Cooler Master NR200', 'Corsair SF750', 'Windows 11 Home', 'Noctua NH-U12A', '2x 120mm',69.99,67);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100006, 'Office PC 1', 'Intel Core i3-14100', 'Intel UHD Graphics', '16GB DDR5', 'ASRock B760M Pro RS', '512GB NVMe SSD', 'Thermaltake Versa H18', 'be quiet! System Power 10 450W', 'Windows 11 Home', 'Stock Cooler', '1x 120mm',1.50,100000);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100007, 'Creator PC 1', 'AMD Ryzen 9 7950X', 'NVIDIA GeForce RTX 4090', '128GB DDR5', 'ASUS ProArt X670E-CREATOR', '4TB NVMe SSD', 'Fractal Design Meshify 2', 'Seasonic PRIME TX-1000', 'Windows 11 Pro', 'Corsair iCUE H150i', '5x 140mm PWM',1000,1000);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100008, 'Linux Dev PC 1', 'Intel Core i5-13600K', 'AMD Radeon RX 7600', '32GB DDR5', 'MSI PRO Z690-A', '1TB NVMe SSD', 'Phanteks Eclipse P400A', 'Corsair RM650', 'Ubuntu 24.04 LTS', 'DeepCool AK620', '3x 120mm',500,50);

INSERT INTO pc (id, codice, nome, cpu, gpu, ram, scheda_madre, rom, case_pc, alimentatore, sistema_operativo, dissipatore, ventole, prezzo, disponibilita) VALUES (nextval('pc_seq'),100009, 'HTPC 1', 'AMD Ryzen 5 8600G', 'Radeon 760M (iGPU)', '16GB DDR5', 'ASUS B650M-A', '1TB NVMe SSD', 'SilverStone GD09', 'Corsair CX550M', 'Windows 11 Home', 'Low-profile cooler', '2x 120mm',69.69,69);

INSERT INTO users (id, first_name, last_name, email, date_of_birth, phone_number) VALUES (nextval('users_seq'), 'Zio', 'Pera', 'ziopera@lol.com', '1984-12-21', '3332221111');

INSERT INTO credentials (id, user_id, username, password, role) VALUES (nextval('credentials_seq'), 1,'ziopera', '$2a$12$.SQC2tN77hdeh/k3TSV2KeSlsddUPCo/JTP4YvAEXh3MyG.WbVNSW', 'ADMIN');

INSERT INTO cart (id, total_price) VALUES (nextval('cart_seq'),0);
INSERT INTO cart_item(id, quantity, cart_id, pc_id) VALUES (nextval('cart_item_seq'), 1, 1, 1);
INSERT INTO cart_item(id, quantity, cart_id, pc_id) VALUES (nextval('cart_item_seq'), 2, 1, 51);