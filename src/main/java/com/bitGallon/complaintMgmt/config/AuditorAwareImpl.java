package com.bitGallon.complaintMgmt.config;

import org.springframework.data.domain.AuditorAware;

import com.bitGallon.complaintMgmt.rest.RestResource;

public class AuditorAwareImpl extends RestResource implements AuditorAware<String>  {

	@Override
	public String getCurrentAuditor() {
		return String.valueOf(getUserId());
	}

}
