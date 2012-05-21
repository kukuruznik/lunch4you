package com.lunch4you.dao;

import com.lunch4you.dao.common.ReadWriteDaoBase;
import com.lunch4you.dao.filter.OrderFilter;
import com.lunch4you.domain.Order;

public interface OrderDao extends ReadWriteDaoBase<Order, Long, OrderFilter> {
}
