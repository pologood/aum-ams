DROP TABLE IF EXISTS fund_account;
CREATE TABLE fund_account (
  id       BIGINT PRIMARY KEY AUTO_INCREMENT,
  name     VARCHAR(255),
  password VARCHAR(255)
);
