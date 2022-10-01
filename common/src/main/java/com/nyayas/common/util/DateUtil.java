package com.nyayas.common.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public final class DateUtil {

	public static final String YYYY_MM_DD_DASH = "yyyy-MM-dd";
	public static final String YYYY_MM_DD_SLASH = "yyyy/MM/dd";

	public static final String DD_MM_YYYY_DASH = "dd/MM/yyyy";
	public static final String DD_MM_YYYY_SLASH = "dd/MM/yyyy";

	public static final long dateToLong(LocalDate ld) {
		return ld.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	public static final long dateTimeToLong(LocalDateTime ldt) {
		return ldt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	public static final LocalDateTime longToDateTime(long time) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone.getDefault().toZoneId());
	}

	public static final LocalDate stringToDate(String date, String format) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
	}
}
