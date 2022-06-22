INSERT INTO usuarios (username,password,enabled,name,email)values('wspereira','$2a$10$BFA48PoZSRrnEnZ5JFfbee2jX8E2rmf6fsyO6FZU9eNjdyww/4tUO',true,'wilson pereira','wspereira@sprc.com.co');
INSERT INTO usuarios (username,password,enabled,name,email)values('meiviloria','$2a$10$3n9Mcga5hDIOuXaDmrgcY.8GRP3L3RVxf.ysLwiPardnQ7jD4bQwq',true,'melissa viloria','meiviloria@sprc.com.co');

inserT into roles (name) values('ROLE_USER');
inseRT into roles (name) values('ROLE_ADMIN');

INSERT INTO usuarios_roles(user_id,roles_id)values(1,1);
INSERT INTO usuarios_roles(user_id,roles_id)values(2,1);
INSERT INTO usuarios_roles(user_id,roles_id)values(2,2);