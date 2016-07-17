CREATE DATABASE IF NOT EXISTS support;
GRANT ALL privileges ON support.* TO root@localhost;
USE support;
CREATE TABLE IF NOT EXISTS `support`.`Topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `support`.`Inquiry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `customer_name` text,
  `description` text,
  `topic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_6rquftraixpq8nl71p43ck7jo` (`topic_id`),
  CONSTRAINT `FK_6rquftraixpq8nl71p43ck7jo` 
  FOREIGN KEY (`topic_id`) 
  REFERENCES `topic` (`id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `support`.`inquiry_attributes` (
  `inquiry_id` bigint(20) NOT NULL,
  `attributes` varchar(255) DEFAULT NULL,
  KEY `FK_6tfipksos7oyssgdgabmp7yqb` (`inquiry_id`),
  CONSTRAINT `FK_6tfipksos7oyssgdgabmp7yqb` 
  FOREIGN KEY (`inquiry_id`) 
  REFERENCES `inquiry` (`id`))
ENGINE=InnoDB;