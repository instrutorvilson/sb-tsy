insert into tb_user(nome, email, senha)values('maria','maria@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
insert into tb_user(nome, email, senha)values('joana','joana@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
insert into tb_user(nome, email, senha)values('Antonia','antonia@gmail.com','$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');


insert into tb_role(authority)values('ROLE_USUARIO');
insert into tb_role(authority)values('ROLE_ADMIN');
insert into tb_role(authority)values('ROLE_OPERADOR');

insert into tb_user_role(user_id,role_id)values(1,1);
insert into tb_user_role(user_id,role_id)values(2,2);
insert into tb_user_role(user_id,role_id)values(3,2);