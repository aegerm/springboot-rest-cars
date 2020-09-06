INSERT INTO tb_cars (name, type) VALUES ('Carro 1', 'Tipo 1');
INSERT INTO tb_cars (name, type) VALUES ('Carro 2', 'Tipo 2');
INSERT INTO tb_cars (name, type) VALUES ('Carro 3', 'Tipo 3');
INSERT INTO tb_cars (name, type) VALUES ('Carro 4', 'Tipo 4');
INSERT INTO tb_cars (name, type) VALUES ('Carro 5', 'Tipo 5');
INSERT INTO tb_cars (name, type) VALUES ('Carro 6', 'Tipo 6');
INSERT INTO tb_cars (name, type) VALUES ('Carro 7', 'Tipo 7');
INSERT INTO tb_cars (name, type) VALUES ('Carro 8', 'Tipo 8');
INSERT INTO tb_cars (name, type) VALUES ('Carro 9', 'Tipo 9');
INSERT INTO tb_cars (name, type) VALUES ('Carro 10', 'Tipo 10');

INSERT INTO tb_roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO tb_roles (name) VALUES ('ROLE_USER');

INSERT INTO tb_users (name, login, password, email) VALUES ('Administrador', 'admin', '$2a$10$hsPacqZib6WGJrum.lp.mOE8FYnI1iDUP6SuzQ03hr6LFaCV4IFTC', 'admin@gmail.com');
INSERT INTO tb_users (name, login, password, email) VALUES ('User', 'user', '$2a$10$0lK7L.xwtPrOROAt49PTp.FJv7Qk4P2BMhPB4HbQJcwevlZoisHUa', 'user@gmail.com');

INSERT INTO tb_users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_users_roles (user_id, role_id) VALUES (2, 2);