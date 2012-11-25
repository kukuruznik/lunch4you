/*
Updates all orders with the price of corresponding article
*/

update plain_order set price = ( 
SELECT SUM(a.price) 
from article a 
join order_item oi on oi.article_id = a.id
where oi.order_id = plain_order.id
group by oi.order_id
);


/*
Updates those orders with discounted price where there was some order from the same customer on same day as main meal
*/

drop temporary table IF EXISTS tmp_table;
create temporary table tmp_table as (
select o1.id
from plain_order o1
join order_item item1 on item1.order_id = o1.id
join article article1 on article1.id = item1.article_id
 where

exists  
(
select item2.id
from order_item item2
join article article2 on article2.id = item2.article_id
join plain_order o2 on o2.id = item2.order_id
where
article2.category_id not in (1,2) 
and o2.owner_id = o1.owner_id
-- and DATE(o2.time_stamp) = DATE(o1.time_stamp)
)
and article1.category_id in (1,2) 
);

update plain_order set price = 10
where plain_order.id in 
(select id from tmp_table)
;
