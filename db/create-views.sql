create view v_order_item as
select 
`order_item`.`id` AS `order_item_id`, 
`customer`.`id` AS `customer_id`,
`customer`.`first_name` AS `customer_first_name`,
`customer`.`last_name` AS `customer_last_name`,
`customer`.`email` AS `customer_email`,
`customer`.`is_active` AS `customer_is_active`,
`customer`.`is_subscribed_menu_weekly` AS `customer_is_subscribed_menu_weekly`,
`customer`.`is_subscribed_news` AS `customer_is_subscribed_news`,
`article`.`id` AS `article_id`,
`article`.`daily_limit` AS `article_daily_limit`,
`article`.`price` AS `article_price`,
`article`.`price_restaurant` AS `article_price_restaurant`,
`article`.`is_active` AS `article_article_is_active`,
`article`.`is_active_restaurant_weekly` AS `article_is_active_restaurant_weekly`,
`article`.`is_active_restaurant_daily` AS `article_is_active_restaurant_daily`,
`article`.`version` AS `article_version`,
`article`.`code` AS `article_code`,
`article`.`name_cz` AS `article_name_cz`,
`article`.`name_en` AS `article_name_en`,
`article`.`description_cz` AS `article_description_cz`,
`article`.`description_en` AS `article_description_en`,
`article`.`new_flag` AS `article_new_flag`,
`category`.`id` AS `category_id`,
`category`.`name_cz` AS `category_name_cz`,
`category`.`name_en` AS `category_name_en`,
`category`.`sort_order` AS `category_sort_order`,
`plain_order`.`id` AS `order_id`,
`plain_order`.`owner_id` AS `order_owner_id`,
`plain_order`.`status` AS `order_status`,
`plain_order`.`note` AS `order_note`,
`plain_order`.`time_stamp` AS `order_time_stamp`,
date_format(`plain_order`.`time_stamp`,'%d/%m/%Y') AS order_date,
`delivery_location`.`id` AS `delivery_location_id`,
`delivery_location`.`name` AS `delivery_location_name`,
`delivery_location`.`abbreviation` AS `delivery_location_abbreviation`,
`delivery_location`.`is_active` AS `delivery_location_is_active`
from 
`category` 
join `article` on `article`.`category_id` = `category`.`id`
join `order_item` on `order_item`.`article_id` = `article`.`id`
join `plain_order` on `order_item`.`order_id` = `plain_order`.`id`
join `delivery_location` on `plain_order`.`delivery_location_id` = `delivery_location`.`id`
join `customer` on `plain_order`.`owner_id` = `customer`.`id`
order by 
order_time_stamp,
delivery_location_name,
customer_first_name,
customer_last_name