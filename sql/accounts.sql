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
values('吉田安崇','yoshida.yasutaka@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('鈴木花穂','suzuki.kaho@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('山本竜也','yamamoto.tatsuya@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('稲田万穂','inada.maho@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('小林彩夏','kobayashi.ayaka@mail.tokyosystem.co.jp',MD5('0000'),'1');

INSERT into accounts(name,mail,password,authority) 
values('藤ノ木祥真','fujinoki.syoma@mail.tokyosystem.co.jp',MD5('0000'),'1');



