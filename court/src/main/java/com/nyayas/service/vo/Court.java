package com.nyayas.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Court {
	private String courtName;
	private int benchCode;
	private String benchName;
}
