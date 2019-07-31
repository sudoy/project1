CREATE table histories(
	history_id int primary key auto_increment not null,
	updated_at DATETIME DEFAULT CURRENT_TIMESTAMP,
	updated_by int not null DEFAULT '0',
	deleted_at DATETIME DEFAULT null ON UPDATE CURRENT_TIMESTAMP,
	deleted_by int DEFAULT null,
	sale_id int ,
	sale_date date not null,
	account_id int not null,
	category_id int not null,
	trade_name varchar(100) not null,
	unit_price int not null,
	sale_number int not null,
	note text
);


INSERT into histories(sale_id,sale_date,account_id,category_id,trade_name,unit_price,sale_number,note)
select sale_id,sale_date,account_id,category_id,trade_name,unit_price,sale_number,note from sales;