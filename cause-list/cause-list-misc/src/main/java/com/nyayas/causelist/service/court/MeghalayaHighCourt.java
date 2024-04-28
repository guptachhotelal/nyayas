package com.nyayas.causelist.service.court;

import org.springframework.stereotype.Service;

import com.nyayas.causelist.service.AbstractCauselistService;
import com.nyayas.causelist.service.CauseListService;

@Service
public class MeghalayaHighCourt extends AbstractCauselistService {

    @Override
    public boolean supports(Class<CauseListService> clazz, Object id) {
	return false;
    }
}
