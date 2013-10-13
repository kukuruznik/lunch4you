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
;

drop view v_referral;
create view v_referral as
SELECT
`sender`.`id` sender_id,
`sender`.`first_name` sender_first_name,
`sender`.`last_name` sender_last_name,
`sender`.`email` sender_email,
`sender`.`is_active` sender_is_active,
`sender`.`default_delivery_location_id` sender_default_delivery_location_id,
`sender`.`is_subscribed_menu_weekly` sender_is_subscribed_menu_weekly,
`sender`.`is_subscribed_news` sender_is_subscribed_news,
`sender_delivery_location`.`name` sender_delivery_location_name,
`referral`.`id` `referral_id`,
`referral`.`referral_message`,
`referral`.`time_stamp`,
DATE(`referral`.`time_stamp`) r_date,
MONTH(`referral`.`time_stamp`) r_month,
YEAR(`referral`.`time_stamp`) r_year,
`recipient`.`id` recipient_id,
`recipient`.`first_name` recipient_first_name,
`recipient`.`last_name` recipient_last_name,
`recipient`.`email` recipient_email,
`recipient`.`is_active` recipient_is_active,
`recipient`.`default_delivery_location_id` recipient_default_delivery_location_id,
`recipient`.`is_subscribed_menu_weekly` recipient_is_subscribed_menu_weekly,
`recipient`.`is_subscribed_news` recipient_is_subscribed_news,
`recipient_delivery_location`.`name` recipient_delivery_location_name
FROM
`referral`
Inner Join `customer` AS `sender` ON `referral`.`sender_id` = `sender`.`id`
Inner Join `customer` AS `recipient` ON `referral`.`recipient_id` = `recipient`.`id`
left outer Join `delivery_location` AS `sender_delivery_location` ON `sender`.`default_delivery_location_id` = `sender_delivery_location`.`id`
left outer Join `delivery_location` AS `recipient_delivery_location` ON `recipient`.`default_delivery_location_id` = `recipient_delivery_location`.`id`
;
