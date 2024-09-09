
CREATE TABLE IF NOT EXISTS `Movie`(
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  PRIMARY KEY(`id`)
);