CREATE TABLE users (
	username varchar(25),
	password varchar(255),
	name varchar(50),
	access enum('General','Editor','Admin')
);
