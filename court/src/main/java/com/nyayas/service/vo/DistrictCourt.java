package com.nyayas.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DistrictCourt extends HighCourt {

	private String districtCode;
	private String districtName;

	@Builder(builderMethodName = "districtCourtBuiler")
	public DistrictCourt(String courtName, int benchCode, String benchName, int stateCode, String stateName,
			String districtCode, String districtName) {
		super(courtName, benchCode, benchName, stateCode, stateName);
		this.districtCode = districtCode;
		this.districtName = districtName;
	}

}
