CREATE table categories(
	category_id int primary key auto_increment not null,
	category_name varchar(50) not null ,
	active_flg int not null default'1'
);


INSERT into categories(category_name,active_flg) 
values('水産・畜産・農産加工品','1');

INSERT into categories(category_name,active_flg) 
values('生鮮・チルド・冷凍食品','1');

INSERT into categories(category_name,active_flg) 
values('乳油製品・調味料・調味食品','1');

INSERT into categories(category_name,active_flg) 
values('麺類','1');

INSERT into categories(category_name,active_flg) 
values('スープ類','1');

INSERT into categories(category_name,active_flg) 
values('菓子類','1');

INSERT into categories(category_name,active_flg) 
values('嗜好飲料','1');

INSERT into categories(category_name,active_flg) 
values('飲料','1');

INSERT into categories(category_name,active_flg) 
values('酒類','1');

INSERT into categories(category_name,active_flg) 
values('氷・アイスクリーム類','1');

INSERT into categories(category_name,active_flg) 
values('デザート類','1');

INSERT into categories(category_name,active_flg) 
values('健康サポート','1');

INSERT into categories(category_name,active_flg) 
values('化粧品','1');

INSERT into categories(category_name,active_flg) 
values('トイレタリー','1');

INSERT into categories(category_name,active_flg) 
values('文具・仏具・雑貨','1');

INSERT into categories(category_name,active_flg) 
values('たばこ','1');

INSERT into categories(category_name,active_flg) 
values('水産','0');

INSERT into categories(category_name,active_flg) 
values('畜産','0');

INSERT into categories(category_name,active_flg) 
values('農産加工品','0');

INSERT into categories(category_name,active_flg) 
values('生鮮','0');

INSERT into categories(category_name,active_flg) 
values('チルド','0');

INSERT into categories(category_name,active_flg) 
values('冷凍食品','0');
