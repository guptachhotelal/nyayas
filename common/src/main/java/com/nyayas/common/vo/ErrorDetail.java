package com.nyayas.common.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private String desciption;

    public ErrorDetail(int code) {
	this.code = code;
    }

    public ErrorDetail(int code, String message) {
	this.code = code;
	this.message = message;
    }

}
