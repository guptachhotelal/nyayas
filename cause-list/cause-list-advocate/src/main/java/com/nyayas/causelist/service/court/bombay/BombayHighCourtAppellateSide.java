package com.nyayas.causelist.service.court.bombay;

import org.springframework.stereotype.Service;

import com.nyayas.causelist.service.CauseListService;
import com.nyayas.causelist.service.court.BombayHighCourt;

@Service
public class BombayHighCourtAppellateSide extends BombayHighCourt {

	@Override
	public boolean supports(Class<CauseListService> clazz, Object id) {
		return false;
	}

 
}
