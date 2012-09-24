package com.lunch4you.dao;

import com.lunch4you.dao.common.ReadWriteDaoBase;
import com.lunch4you.dao.filter.DeliveryLocationFilter;
import com.lunch4you.domain.DeliveryLocation;

public interface DeliveryLocationDao extends ReadWriteDaoBase<DeliveryLocation, Long, DeliveryLocationFilter> {
}
