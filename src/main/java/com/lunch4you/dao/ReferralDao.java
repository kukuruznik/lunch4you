package com.lunch4you.dao;

import com.lunch4you.dao.common.ReadWriteDaoBase;
import com.lunch4you.dao.filter.ReferralFilter;
import com.lunch4you.domain.Referral;

public interface ReferralDao extends ReadWriteDaoBase<Referral, Long, ReferralFilter> {
}
