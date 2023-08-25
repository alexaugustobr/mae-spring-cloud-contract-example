set foreign_key_checks = 0;

delete from review;

INSERT INTO review (id, comment, created_at, grade, product_id) VALUES(1, 'Ótimo produto.', '2023-08-10 19:14:36.137145', 4, 1);
INSERT INTO review (id, comment, created_at, grade, product_id) VALUES(2, 'Superou as expectativas.', '2023-08-10 19:14:36.137145', 5, 1);
INSERT INTO review (id, comment, created_at, grade, product_id) VALUES(3, 'Não gostei.', '2023-08-10 19:14:36.137145', 1, 1);
INSERT INTO review (id, comment, created_at, grade, product_id) VALUES(4, 'Não tem um bom custo benefício.', '2023-08-10 19:14:36.137145', 2, 2);
INSERT INTO review (id, comment, created_at, grade, product_id) VALUES(5, 'Gostei, mas poderia ser mais barato.', '2023-08-10 19:14:36.138142', 3, 2);

set foreign_key_checks = 1;