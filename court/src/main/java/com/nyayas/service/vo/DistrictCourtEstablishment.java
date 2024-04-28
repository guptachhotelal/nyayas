package com.nyayas.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DistrictCourtEstablishment extends DistrictCourt {

    private static final long serialVersionUID = 1L;

    private String establishmentCode;
    private String establishmentName;

    @Builder(builderMethodName = "courtEstablishmentBuilder")
    public DistrictCourtEstablishment(String stateCode, String stateName, String districtCode, String districtName,
	    String establishmentCode, String establishmentName) {
	super(stateCode, stateName, districtCode, districtName);
	this.establishmentCode = establishmentCode;
	this.establishmentName = establishmentName;
    }

}
