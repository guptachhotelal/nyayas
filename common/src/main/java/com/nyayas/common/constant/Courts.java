package com.nyayas.common.constant;

public enum Courts {
	SUPREME_COURT(1, "Supreme Court Of India"), ECOURT_DISTRICT_COURT(2, "eCourt District Court"),
	ECOURT_HIGH_COURT(3, "eCourt High Court"), DELHI_HIGH_COURT(10, "Delhi High Court"),
	BOMBAY_HIGH_COURT_AURANGABAD(21, "Bombay High Court, Aurangabad Bench"),
	BOMBAY_HIGH_COURT_BOMBAY(22, "Bombay High Court, Bombay Bench"),
	BOMBAY_HIGH_COURT_NAGPUR(23, "Bombay High Court, Nagpur Bench"),
	BOMBAY_HIGH_COURT_GOA(24, "Bombay High Court, Goa Bench");

	private int courtId;
	private String text;

	private Courts(int courtId, String text) {
		this.courtId = courtId;
		this.text = text;
	}

	public final int courtId() {
		return courtId;
	}

	public final String text() {
		return text;
	}
}
