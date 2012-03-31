-- Table "meal" DDL

CREATE TABLE `meal` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daily_limit` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
