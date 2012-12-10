-- Table "category" DDL

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_cz` varchar(255) NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `sort_order` int(4) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Table "article" DDL

CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daily_limit` int(11) DEFAULT NULL,
  `price` int(11) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `version` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `name_cz` varchar(255) NOT NULL,
  `name_en` varchar(255) NOT NULL,
  `description_cz` varchar(1000) DEFAULT NULL,
  `description_en` varchar(1000) DEFAULT NULL,
  `new_flag` tinyint(1) NOT NULL DEFAULT '0',
  `package_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `article_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE NO ACTION
) ENGINE=InnoDB;

-- Table "delivery_location" DDL

CREATE TABLE `delivery_location` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(254) NOT NULL,
  `abbreviation` varchar(254) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

-- Table "customer" DDL
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `token` varchar(50) NOT NULL,
  `credit` int(11) NOT NULL DEFAULT '0',
  `version` int(11) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `default_delivery_location_id` bigint(20) NOT NULL,
  `is_subscribed_menu_weekly` tinyint(1) DEFAULT '1',
  `is_subscribed_news` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `customer_email` (`email`),
  KEY `customer_default_delivery_location_id` (`default_delivery_location_id`),
  CONSTRAINT `customer_default_delivery_location_id` FOREIGN KEY (`default_delivery_location_id`) REFERENCES `delivery_location` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table "plain_order" DDL
CREATE TABLE `plain_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_id` bigint(20) NOT NULL,
  `status` varchar(50) NOT NULL,
  `note` varchar(4000) default "",
  `version` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `delivery_location_id` bigint(20) NOT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `orders_owner` (`owner_id`),
  KEY `orders_status` (`status`),
  CONSTRAINT `plain_order_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `plain_order_ibfk_2` FOREIGN KEY (`delivery_location_id`) REFERENCES `delivery_location` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table "orderitem" DDL
CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_items_article` (`article_id`),
  KEY `order_items_order` (`order_id`),
  CONSTRAINT `order_items_order` FOREIGN KEY (`order_id`) REFERENCES `plain_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_items_article` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

-- Table "referral" DDL
CREATE TABLE `referral` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sender_id` bigint(20) NOT NULL,
  `recipient_id` bigint(20) NOT NULL,
  `referral_message` varchar(1000) NOT NULL,
  `time_stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `referral_sender` (`sender_id`),
  KEY `referral_recipient` (`recipient_id`),
  CONSTRAINT `referral_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `referral_ibfk_2` FOREIGN KEY (`recipient_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

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
) ENGINE=InnoDB;

-- Table "isprepared" DDL
CREATE TABLE `is_prepared` (
  `preparation_id` bigint(20) NOT NULL,
  `order_item_id` bigint(20) NOT NULL,
  KEY `isprepared_preparation` (`preparation_id`),
  KEY `isprepared_orderitem` (`order_item_id`),
  CONSTRAINT `is_prepared_ibfk_1` FOREIGN KEY (`order_item_id`) REFERENCES `order_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `is_prepared_ibfk_2` FOREIGN KEY (`preparation_id`) REFERENCES `preparation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

