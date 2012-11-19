package com.lunch4you.web.dto;

import java.util.Date;

public final class ReferralDto {

	public Long id;

	public CustomerDto sender;

	public CustomerDto recipient;

	public String referralMessage;

	public Date timestamp;

}
