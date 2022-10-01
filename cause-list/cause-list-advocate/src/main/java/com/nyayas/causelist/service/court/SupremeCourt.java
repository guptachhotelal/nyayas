package com.nyayas.causelist.service.court;

import org.springframework.stereotype.Service;

import com.nyayas.causelist.service.AbstractCauselistService;
import com.nyayas.causelist.service.CauseListService;
import com.nyayas.common.constant.Courts;

@Service
public class SupremeCourt extends AbstractCauselistService {

	@Override
	public boolean supports(Class<CauseListService> clazz, Object id) {
		return CauseListService.class.equals(clazz) && id.equals(Courts.SUPREME_COURT.courtId());
	}

}
