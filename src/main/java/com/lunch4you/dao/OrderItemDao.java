package com.lunch4you.dao;

import com.lunch4you.dao.common.ReadWriteDaoBase;
import com.lunch4you.dao.filter.OrderFilter;
import com.lunch4you.domain.OrderItem;

public interface OrderItemDao extends ReadWriteDaoBase<OrderItem, Long, OrderFilter> {
}
