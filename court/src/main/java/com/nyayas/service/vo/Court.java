package com.nyayas.service.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Court implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String courtName;
	private int benchCode;
	private String benchName;
}
