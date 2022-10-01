package com.nyayas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourtController {

	@GetMapping("hc")
	public String highCourt() {
		return "jsp/highCourt";
	}

	@GetMapping("dc")
	public String districtCourt() {
		return "jsp/districtCourt";
	}
}
