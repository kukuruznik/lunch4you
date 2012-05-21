package com.lunch4you.dao;

import com.lunch4you.dao.common.ReadWriteDaoBase;
import com.lunch4you.dao.filter.CustomerFilter;
import com.lunch4you.domain.Customer;


public interface CustomerDao extends ReadWriteDaoBase<Customer, Long, CustomerFilter> {
}
