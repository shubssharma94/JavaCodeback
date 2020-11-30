package com.suhas.springboot.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	
	private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
	
	//https://stackoverflow.com/questions/48279848/how-to-convert-a-java-double-value-to-date-time-format-hhmmss-in-java
	public void convertDoubleValueToDate() throws ParseException{
		TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
		double x = 12803.000000;
		String s = String.format("%06d", (int)x);   
		DateFormat format = new SimpleDateFormat("HHmmss");
		format.setTimeZone(timeZone);
		Date date = format.parse(s);
		System.out.println(date);
	}
	 //https://howtodoinjava.com/java/date-time/convert-date-time-to-est-est5edt/
	public void convertDoubleValueToDateJava8() throws ParseException{
		    double x = 12803.000000;
		    String stringFormat = String.format("%06d", (int)x);   
	        SimpleDateFormat etDf = new SimpleDateFormat("HHmmss");
	        TimeZone etTimeZone = TimeZone.getTimeZone("America/New_York");
	 
	        Date currentDate = etDf.parse(stringFormat);
	        Calendar currentTime = Calendar.getInstance();
	        currentTime.setTime(currentDate);
	        currentTime.setTimeZone(etTimeZone);
	         
	        //In ET Time
	        System.out.println(etDf.format(currentDate.getTime()));
	        System.out.println(etDf.format(currentTime.getTimeInMillis()));

	}
	
	public void convertDoubleValueToDate1() throws ParseException{
		SimpleDateFormat etDf = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mma 'ET'");
		TimeZone etTimeZone = TimeZone.getTimeZone("America/New_York");
		
		etDf.setTimeZone(etTimeZone);
		
		double x = 12803.000000;
		String s = String.format("%06d", (int)x);
		
		Date date = etDf.parse(s);
		
		System.out.println(etDf.format(date.getTime()));
		
		
	}
	
	public Date convertDoubleValueToDate3(double doubleVal) throws ParseException{
		//convert double value to date
		String s = String.format("%06d", (int)doubleVal);   
		DateFormat format = new SimpleDateFormat("HHmmss");
		Date date = format.parse(s);
		
		//convert date into perticuler time zone
		SimpleDateFormat sdfAmerica = new SimpleDateFormat(DATE_FORMAT);
		TimeZone tzInAmerica = TimeZone.getTimeZone("America/New_York");
		sdfAmerica.setTimeZone(tzInAmerica);
		
		
		System.out.println("Simple Date : "+date);
        System.out.println("Date (New York) (Object) : " + sdfAmerica.format(date));
		return date;
	}
	
	
	
	public Date convertDoubleValueToDate2(double doubleVal) throws ParseException{
		String s = String.format("%06d", (int)doubleVal);   
		DateFormat format = new SimpleDateFormat("HHmmss");
		Date date = format.parse(s);
		System.out.println(date);
		return date;
	}
	
	public String getCurrentTime(String timeFormat, TimeZone timeZone, Date date)
	   {
	      /* Specifying the format */ 
	      DateFormat dateFormat = new SimpleDateFormat(timeFormat);
	      /* Setting the Timezone */
	      Calendar cal = Calendar.getInstance(timeZone);
	      /* Setting the Timezone */
	      cal.setTime(date);
	      dateFormat.setTimeZone(cal.getTimeZone());
	      /* Picking the time value in the required Format */
	      String currentTime = dateFormat.format(cal.getTime());
	      return currentTime;
	   }
	
	public static void main(String[] args) throws ParseException {
		Date date = null;
		double x = 12803.000000;
		String timeFormat = "hh:mm:ss";
		TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
		date = new DateUtil().convertDoubleValueToDate3(x);
		System.out.println(new DateUtil().getCurrentTime(timeFormat,timeZone, date));
	}

}
