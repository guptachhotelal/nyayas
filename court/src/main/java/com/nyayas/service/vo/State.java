package com.nyayas.service.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class State implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stateCode;
	private String stateName;
}
