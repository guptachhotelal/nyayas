package com.nyayas.status.service.court;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nyayas.status.service.CaseStatusByAdvocateService;
import com.nyayas.status.service.AdvocateService;

@Service
public class GujaratHighCourt extends CaseStatusByAdvocateService {

	@Override
	public boolean supports(Class<AdvocateService> clazz, Object id) {
		return false;
	}

	@Override
	public Map<String, Object> byAdvocateName(Map<String, String> param) throws IOException {
		return super.byAdvocateName(param);
	}

}
