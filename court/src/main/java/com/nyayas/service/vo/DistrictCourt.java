package com.nyayas.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DistrictCourt extends State {

    private static final long serialVersionUID = 1L;

    private String districtCode;
    private String districtName;

    @Builder(builderMethodName = "districtCourtBuilder")
    public DistrictCourt(String stateCode, String stateName, String districtCode, String districtName) {
	super(stateCode, stateName);
	this.districtCode = districtCode;
	this.districtName = districtName;
    }
}
