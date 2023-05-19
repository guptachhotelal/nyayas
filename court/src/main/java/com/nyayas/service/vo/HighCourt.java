package com.nyayas.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HighCourt extends Court {

	private static final long serialVersionUID = 1L;
	private int stateCode;
	private String stateName;

	@Builder(builderMethodName = "highCourtBuilder")
	public HighCourt(String courtName, int benchCode, String benchName, int stateCode, String stateName) {
		super(courtName, benchCode, benchName);
		this.stateCode = stateCode;
		this.stateName = stateName;
	}

}
