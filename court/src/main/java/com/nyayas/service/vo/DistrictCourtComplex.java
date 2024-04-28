package com.nyayas.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DistrictCourtComplex extends DistrictCourt {

    private static final long serialVersionUID = 1L;

    private String complexCode;
    private String complexName;

    @Builder(builderMethodName = "courtComplexBuilder")
    public DistrictCourtComplex(String stateCode, String stateName, String districtCode, String districtName,
	    String complexCode, String complexName) {
	super(stateCode, stateName, districtCode, districtName);
	this.complexCode = complexCode;
	this.complexName = complexName;
    }
}
