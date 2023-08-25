CREATE TABLE review (
  id bigint NOT NULL auto_increment,
  comment varchar(255) NOT NULL DEFAULT '',
  created_at datetime(6) NOT NULL,
  grade int DEFAULT 0,
  product_id bigint DEFAULT NULL,
  PRIMARY KEY (id)
);