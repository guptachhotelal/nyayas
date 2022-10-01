package com.nyayas.causelist.service.court.gauhati;

import org.springframework.stereotype.Service;

import com.nyayas.causelist.service.CauseListService;
import com.nyayas.causelist.service.court.GauhatiHighCourt;

@Service
public class GauhatiHighCourtGuwahatiBench extends GauhatiHighCourt {
	@Override
	public boolean supports(Class<CauseListService> clazz, Object id) {
		return false;
	}
}
