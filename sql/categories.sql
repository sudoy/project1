CREATE table categories(
	category_id int primary key auto_increment not null,
	category_name varchar(50) not null ,
	active_flg int not null default'1'
);