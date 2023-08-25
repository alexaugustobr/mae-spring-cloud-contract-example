set foreign_key_checks = 0;

delete from product;

INSERT INTO product (id, name, price) VALUES(1, 'Notebook Gamer RX76', 1999.90);
INSERT INTO product (id, name, price) VALUES(2, 'Monitor 22p', 1500.00);
INSERT INTO product (id, name, price) VALUES(3, 'Microfone FT342', 300.00);
INSERT INTO product (id, name, price) VALUES(4, 'Notebook AW Office RV342', 4370.00);
INSERT INTO product (id, name, price) VALUES(5, 'Nobreak PowerTec', 498.00);

set foreign_key_checks = 1;