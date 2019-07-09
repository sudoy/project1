CREATE table sales(
	sale_id int primary key auto_increment,
	sale_date date not null,
	account_id int not null,
	category_id int not null,
	trade_name varchar(100) not null,
	unit_price int not null,
	sale_number int not null,
	note text
);