package com.suhas.springboot.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class LongToDateConversion {

	private static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss a";

	private static final String TIME_ZONE = "America/New_York";

	private SimpleDateFormat getFormattedTimeZone() {
		SimpleDateFormat sdfAmerica = null;
		TimeZone tzInAmerica = null;
		try {
			sdfAmerica = new SimpleDateFormat(DATE_FORMAT);
			tzInAmerica = TimeZone.getTimeZone(TIME_ZONE);
			sdfAmerica.setTimeZone(tzInAmerica);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return sdfAmerica;
	}

	public String convertLongValueToDate(Long longValue) {
		Date date = null;
		SimpleDateFormat sdfAmerica = null;
		String dateFormat = null;
		try {
			date = new Date(longValue);

			// get est time zone
			sdfAmerica = getFormattedTimeZone();

			dateFormat = sdfAmerica.format(date);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return dateFormat;
	}

	public static void main(String[] args) {
		Long longVal = new Long("1606126109752"); /*System.currentTimeMillis();*/ 
		LongToDateConversion longToDateConversion = new LongToDateConversion();
		String convertedDate = longToDateConversion.convertLongValueToDate(longVal);
		System.out.println("Date (New York) (Object) : " + convertedDate);
	}

}
