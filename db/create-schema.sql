-- Table "article" DDL
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daily_limit` int(11) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Table "customer" DDL
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `token` varchar(50) NOT NULL,
  `credit` int(11) NOT NULL DEFAULT '0',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Table "isprepared" DDL
CREATE TABLE `is_prepared` (
  `preparation_id` bigint(20) NOT NULL,
  `order_item_id` bigint(20) NOT NULL,
  KEY `isprepared_preparation` (`preparation_id`),
  KEY `isprepared_orderitem` (`order_item_id`),
  CONSTRAINT `is_prepared_ibfk_1` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `is_prepared_ibfk_2` FOREIGN KEY (`preparation_id`) REFERENCES `preparation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table "order" DDL
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) NOT NULL,
  `total` int(11) NOT NULL DEFAULT '0',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orders_owner` (`owner_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table "orderitem" DDL
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `amount` int(11) NOT NULL,
  `unit_price` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_items_article` (`article_id`),
  CONSTRAINT `order_items_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Table "preparation" DDL
CREATE TABLE `preparation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `amount` int(11) NOT NULL DEFAULT '0',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `preparations_article` (`article_id`),
  CONSTRAINT `preparations_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
