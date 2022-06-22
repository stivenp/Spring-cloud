INSERT INTO usuarios (username,password,enabled,name,email)values('wspereira','123456',true,'wilson pereira','wspereira@sprc.com.co');
INSERT INTO usuarios (username,password,enabled,name,email)values('meiviloria','123456',true,'melissa viloria','meiviloria@sprc.com.co');

inserT into roles (name) values('ROLE_USER');
inseRT into roles (name) values('ROLE_ADMIN');

INSERT INTO usuarios_roles(user_id,roles_id)values(1,1);
INSERT INTO usuarios_roles(user_id,roles_id)values(2,1);
INSERT INTO usuarios_roles(user_id,roles_id)values(2,2);