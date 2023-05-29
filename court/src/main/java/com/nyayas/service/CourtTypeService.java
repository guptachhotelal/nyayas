package com.nyayas.service;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface CourtTypeService extends CourtService {

	Map<String, String> districts(String stateCode) throws IOException;

	List<? extends Serializable> courtComplex() throws IOException;

	Map<String, String> courtComplexes(String stateCode, String districtCode) throws IOException;

	List<? extends Serializable> courtEstablishment() throws IOException;

	Map<String, String> courtEstablishments(String stateCode, String districtCode) throws IOException;
}
