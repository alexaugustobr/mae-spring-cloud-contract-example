CREATE TABLE product (
  id bigint NOT NULL auto_increment,
  name varchar(255) DEFAULT NULL,
  price decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (id)
);