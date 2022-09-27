insert into role(id,nome) values (1, 'ROLE_USER');
insert into role(id,nome) values (2, 'ROLE_ADMIN');

insert into user(id,nome,email,login,senha) values (1,'Ricardo Lecheta','rlecheta@gmail.com','rlecheta','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe');
insert into user(id,nome,email,login,senha) values (2,'Admin','admin@gmail.com','admin','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe');
insert into user(id,nome,email,login,senha) values (3,'User','user@gmail.com','user','$2a$10$HKveMsPlst41Ie2LQgpijO691lUtZ8cLfcliAO1DD9TtZxEpaEoJe');

insert into user_roles(user_id,role_id) values(1, 1);
insert into user_roles(user_id,role_id) values(2, 2);
insert into user_roles(user_id,role_id) values(3, 1);


insert into pet (nome,descricao,raca,url_foto,url_video,latitude,longitude,tipo) VALUES('Onix','vacidao docil','pitbull','http://www.livroandroid.com.br/livro/carros/classicos/Tucker.png','http://www.livroandroid.com.br/livro/carros/classicos/tucker.mp4','-23.564224','-46.653156','cao');
insert into pet (nome,descricao,raca,url_foto,url_video,latitude,longitude,tipo) VALUES('Bigo','vacidao docil','pitbull','http://www.livroandroid.com.br/livro/carros/classicos/Chevrolet_Corvette.png','http://www.livroandroid.com.br/livro/carros/classicos/corvette.mp4','-23.564224','-46.653156','cao');
insert into pet (nome,descricao,raca,url_foto,url_video,latitude,longitude,tipo) VALUES('Milk','vacidao docil', 'vira lata','http://www.livroandroid.com.br/livro/carros/classicos/Chevrolet_Impala_Coupe.png','http://www.livroandroid.com.br/livro/carros/classicos/chevrolet_impala.mp4','-23.564224','-46.653156','cao');
insert into pet (nome,descricao,raca,url_foto,url_video,latitude,longitude,tipo) VALUES('Ariel','vacidao docil', 'vira lata','http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png','http://www.livroandroid.com.br/livro/carros/esportivos/ferrari_ff.mp4','44.532218','10.864019','gato');
insert into pet (nome,descricao,raca,url_foto,url_video,latitude,longitude,tipo) VALUES('Jasmine','vacidao docil', 'persa', 'http://www.livroandroid.com.br/livro/carros/esportivos/Audi_Spyder.png','http://www.livroandroid.com.br/livro/carros/esportivos/audi_gt.mp4','-23.564224','-46.653156','gato');
insert into pet (nome,descricao,raca,url_foto,url_video,latitude,longitude,tipo) VALUES('Barbie','vacidao docil', 'persa','http://www.livroandroid.com.br/livro/carros/esportivos/Porsche_Panamera.png','http://www.livroandroid.com.br/livro/carros/esportivos/porsche_panamera.mp4','-23.564224','-46.653156','gato');