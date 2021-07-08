drop table if exists t_attachment;

CREATE TABLE t_attachment (
  id INT NOT NULL AUTO_INCREMENT,
  doc_id VARCHAR(45) NULL,
  doc_name VARCHAR(45) NULL,
  type_id INT NULL,
  PRIMARY KEY (id));

ALTER TABLE t_attachment
ADD COLUMN flag VARCHAR(45) NULL AFTER `type_id`,
ADD COLUMN isDeleted VARCHAR(45) NULL AFTER `flag`;


drop table if exists T_ATTACHMENT_TYPE;

CREATE TABLE T_ATTACHMENT_TYPE (
  type_id INT NOT NULL AUTO_INCREMENT,
  type_name VARCHAR(45) NULL,
  PRIMARY KEY (type_id));
