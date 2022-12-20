package com.nyayas.common.constant;

public enum Courts {
	SUPREME_COURT("SCI", "Supreme Court Of India"), ECOURT_DISTRICT_COURT("ECDC", "eCourt District Court"),
	ECOURT_HIGH_COURT("ECHC", "eCourt High Court"), DELHI_HIGH_COURT("DHC", "Delhi High Court"),
	BOMBAY_HIGH_COURT_AURANGABAD("BHCA", "Bombay High Court, Aurangabad Bench"),
	BOMBAY_HIGH_COURT_BOMBAY("BHCB", "Bombay High Court, Bombay Bench"),
	BOMBAY_HIGH_COURT_NAGPUR("BHCN", "Bombay High Court, Nagpur Bench"),
	BOMBAY_HIGH_COURT_GOA("BHCG", "Bombay High Court, Goa Bench");

	private String code;
	private String text;

	private Courts(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public final String code() {
		return code;
	}

	public final String text() {
		return text;
	}
}
