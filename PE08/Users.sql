USE travel;

CREATE TABLE users(
username VARCHAR(25),
password VARCHAR(255),
name VARCHAR(50),
access ENUM('General', 'Editor', 'Admin'));

INSERT INTO users VALUES
('vivek14', md5('vp123'), 'Vivek Panchal', 3),
('user02', md5('user002'), 'Virat Kohli', 1),
('user03', md5('user003'), 'Dale Steyn', 2);
