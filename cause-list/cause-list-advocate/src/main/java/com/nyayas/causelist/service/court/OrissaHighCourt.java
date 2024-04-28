package com.nyayas.causelist.service.court;

import org.springframework.stereotype.Service;

import com.nyayas.causelist.service.AbstractCauselistService;
import com.nyayas.causelist.service.CauseListService;

@Service
public class OrissaHighCourt extends AbstractCauselistService {

    @Override
    public boolean supports(Class<CauseListService> clazz, Object id) {
	return false;
    }
}
