select cust.email, art.name_cz, art.price
from plain_order o
join order_item oi on oi.order_id = o.id
join customer cust on cust.id = o.owner_id
join article art on art.id = oi.article_id
join category on category.id = art.category_id

where date(time_stamp) = '2012-11-28';


-- Referral grouped by year/month
select r_year, r_month, count(referral_id) from v_referral
group by r_year, r_month

-- Referral grouped by company
select sender_delivery_location_name, count(referral_id) from v_referral
group by sender_delivery_location_name


-- Orders grouped by weeks

select 
year(order_time_stamp), 
week(order_time_stamp),
 delivery_location_name, 
count(*) 

from v_order_item
group by 
year(order_time_stamp), 
week(order_time_stamp),
 delivery_location_name


-- total number of orders and revenue

select count(*), sum(article_price) from v_order_item
where 
order_time_stamp > '2012-12-31'