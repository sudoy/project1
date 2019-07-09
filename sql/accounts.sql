CREATE table accounts(
	account_id int primary key auto_increment not null,
	name varchar(20) not null,
	mail varchar(100) not null,
	password varchar(32) not null,
	authority int not null default'0'
);

INSERT into accounts(name,mail,password,authority) 
values('Admin','admin@mail.tokyosystem.co.jp',MD5('0000'),'10');

INSERT into accounts(name,mail,password,authority) 
values('�g�c����','yoshida.yasutaka@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('��؉ԕ�','suzuki.kaho@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('�R�{����','yamamoto.tatsuya@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('��c����','inada.maho@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('���эʉ�','kobayashi.ayaka@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('���m�؏ː^','fujinoki.syoma@mail.tokyosystem.co.jp',MD5('0000'),'1');



