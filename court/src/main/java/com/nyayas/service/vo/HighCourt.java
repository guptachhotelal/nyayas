package com.nyayas.service.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class HighCourt extends State {

	private static final long serialVersionUID = 1L;

	private String courtName;
	private String benchCode;
	private String benchName;

	@Builder(builderMethodName = "highCourtBuilder")
	public HighCourt(String stateCode, String stateName, String courtName, String benchCode, String benchName) {
		super(stateCode, stateName);
		this.courtName = courtName;
		this.benchCode = benchCode;
		this.benchName = benchName;
	}
}
