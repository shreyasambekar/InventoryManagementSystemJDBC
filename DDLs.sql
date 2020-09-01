drop database if exists mydb;
create database mydb;
use mydb;

drop table if exists customers;

create table customers(id int primary key auto_increment, 
 						zip int, 
 						homephone varchar(20), cellphone varchar(20), workphone varchar(20), 
 						name varchar(50), street varchar(50), city varchar(50), state varchar(50));
drop table if exists purchaseorders;
create table purchaseorders(poNumber int primary key auto_increment, 
							   orderDate date, shipDate date, 
							   customerid int, foreign key(customerid) references customers(id));

create table stockitems(itemNumber int primary key auto_increment, 
						  itemDescription varchar(50), itemPrice double, 
						  unitOfItem enum('KGS','LBS','GALLONS','NUMBER','GRAMS'), quantity int);

create table orderitems(id int primary key auto_increment, 
						  stockItemId int, numberOfItems int, 
						  purchaseOrderId int, 
						  foreign key(stockItemId) references stockitems(itemNumber), 
						  foreign key(purchaseOrderId) references purchaseorders(poNumber));









