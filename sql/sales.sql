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


INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','1','22','あけぼの『新中華街 あおり炒めの焼豚炒飯』 450gX12袋','4500','10');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','1','22','業務用 ニッスイ えび フリッター大 1kg 冷凍食品 エビ の フリッター','3417','15');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','1','22','天ぷら 冷凍食品【オクラ肉詰め天 50個入り】業務用 惣菜','2700','1000');




INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','2','7','明治屋 純粋蜂蜜 使いきりタイプ (15g×5本)×48個','10143','5');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','2','7','株式会社ダイショー ダイショー のみやすい大麦若葉入青汁4週間84g×10個','8684','1');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','2','7','国太楼 SIPマテ茶三角TB 20P×12個','3159','10');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','2','7','森永 甘酒<しょうが> 190g×30本','3137','2');



INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','3','3','日清 カップヌードル シーフードヌードル 75g×20個','2664','150');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-01','3','3','そばの実（北海道産）/1kg TOMIZ/cuoca(富澤商店)','2200','4');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-03','3','3','ココアパウダー（オランダ産有名ブランド使用） / 500g TOMIZ/cuoca(富澤商店)','1361','105');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-03','3','3','春よ恋 / 3kg TOMIZ/cuoca(富澤商店) ','1318','51');



INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-03','4','9','サントリー角瓶５Ｌ業務用４本入り１ケース','31980','1');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-03','4','9','麦焼酎 一番札 25度 1800ml','1820','14');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-03','4','9','The Essence シングルモルトウイスキー 山崎蒸溜所 ピーテッドモルト 500ml','75000','3');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-04','4','9','古式有機原酒 なゝこ 本格芋焼酎 37度 専用木箱付き 1800ml×4本','95740','1');




INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','5','10','中日本氷糖 白マーク ロックA 1kg','409','500');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','5','10','中日本氷糖 国産原料 ロック 1kg','537','21');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','5','10','アウリーノ アイスクリーム バニラ 8個入り','3129','10');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','5','10','コエンザイムQ10配合ノンシュガー低カロリーアイス「キュート」8個セット','3860','10');




INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','6','10','大人のひとときレディーボーデンチョコナッツバー 80ml','96','1200');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','6','10','大人のひとときレディーボーデンストロベリーバー 80ml','96','900');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','6','10','ソフ バニラ 赤城乳業','91','1250');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','6','10','江崎グリコ 【糖質50% オフ※】 スナオ SUNAO チョコクランチ','137','12');





INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','7','1','かに風味かまぼこ 15本×3箱セット','4290','42');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','7','1','浜浦水産 ほたるいか素干 45尾×2袋','2160','21');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','7','1','【小針水産】特大無頭 開き縞ホッケ 5枚','2750','582');

INSERT into sales(sale_date,account_id,category_id,trade_name,unit_price,sale_number) 
values('2019-06-05','7','1','川口水産 特大国産 うなぎ の蒲焼き（170g）5尾セット（タレ、山椒付き)','16600','231');
