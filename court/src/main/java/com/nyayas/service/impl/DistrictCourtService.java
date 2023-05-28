package com.nyayas.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.nyayas.common.constant.Courts;
import com.nyayas.service.AbstractDistrictCourtService;
import com.nyayas.service.CourtService;
import com.nyayas.service.vo.DistrictCourtComplex;
import com.nyayas.service.vo.DistrictCourtEstablishment;

//https://curl.trillworks.com
//https://curlconverter.com/
@Service
public class DistrictCourtService extends AbstractDistrictCourtService {

	private static final List<Serializable> COURT_COMPLEX = new ArrayList<>();

	private static final List<Serializable> COURT_ESTABLISHMENT = Collections.emptyList();

	@Override
	public boolean supports(Class<CourtService> clazz, Object id) {
		return CourtService.class.equals(clazz) && id.equals(Courts.ECOURT_DISTRICT_COURT.code());
	}

	@Override
	public List<Serializable> courts(String courtType) throws IOException {
		return "ce".equals(courtType) ? courtEstablishment() : courtComplex();
	}

	@Override
	public List<Serializable> courtComplex() throws IOException {
		if (!COURT_COMPLEX.isEmpty()) {
			return COURT_COMPLEX;
		} else {
			for (Entry<String, String> state : states().entrySet()) {
				String sc = state.getKey();
				String sn = state.getValue();
				for (Entry<String, String> district : districts(sc).entrySet()) {
					String dc = district.getKey();
					String dn = district.getValue();
					for (Entry<String, String> cc : courtComplexes(sc, dc).entrySet()) {
						DistrictCourtComplex dcc = DistrictCourtComplex.courtComplexBuilder().stateCode(sc)
								.stateName(sn).districtCode(dc).districtName(dn).complexCode(cc.getKey())
								.complexName(cc.getValue()).build();
						COURT_COMPLEX.add(dcc);
					}
				}
			}
		}
		return COURT_COMPLEX;
	}

	@Override
	public List<Serializable> courtEstablishment() throws IOException {
		if (!COURT_ESTABLISHMENT.isEmpty()) {
			return COURT_ESTABLISHMENT;
		} else {
			for (Entry<String, String> state : states().entrySet()) {
				String sc = state.getKey();
				String sn = state.getValue();
				for (Entry<String, String> district : districts(sc).entrySet()) {
					String dc = district.getKey();
					String dn = district.getValue();
					for (Entry<String, String> cc : courtEstablishments(sc, dc).entrySet()) {
						DistrictCourtEstablishment dcc = DistrictCourtEstablishment.courtEstablishmentBuilder()
								.stateCode(sc).stateName(sn).districtCode(dc).districtName(dn)
								.establishmentCode(cc.getKey()).establishmentName(cc.getValue()).build();
						COURT_ESTABLISHMENT.add(dcc);
					}
				}
			}
		}
		return COURT_ESTABLISHMENT;
	}
}
