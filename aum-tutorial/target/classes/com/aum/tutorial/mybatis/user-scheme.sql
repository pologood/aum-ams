DROP TABLE IF EXISTS user;
CREATE TABLE user (
  id       BIGINT PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(255),
  password VARCHAR(255)
);