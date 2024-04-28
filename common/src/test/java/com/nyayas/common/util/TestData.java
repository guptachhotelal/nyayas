package com.nyayas.common.util;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long dob;
    private String phone;
    private String email;
    private String city;
    private String pincode;
    private String state;
    private Long createDate;
    private Long updateDate;
}