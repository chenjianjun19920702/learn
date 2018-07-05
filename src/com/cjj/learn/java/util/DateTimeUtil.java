package com.cjj.learn.java.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SimpleTimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtil {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(DateTimeUtil.class);

	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	private static final SimpleDateFormat TIME_FORMATER = new SimpleDateFormat(
			YYYY_MM_DD_HH_MM_SS);
	
	private static final SimpleDateFormat NO_SECOND_TIME_FORMATER = new SimpleDateFormat(
			YYYY_MM_DD_HH_MM);

	private static final SimpleDateFormat NO_SIGN_TIME_FORMATER = new SimpleDateFormat(
			YYYYMMDDHHMMSS);

	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private static final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(
			YYYY_MM_DD);
	
	private static final String YYYYMMDD = "yyyyMMdd";

	private static final SimpleDateFormat NO_SIGN_DATE_FORMATER = new SimpleDateFormat(
			YYYYMMDD);

	private static final String YYYY = "yyyy";

	private static final SimpleDateFormat YYYY_FORMATER = new SimpleDateFormat(
			YYYY);

	private static final String GMT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private static final SimpleDateFormat GMT_FORMAT = new SimpleDateFormat(GMT);
	
	private static final String YYYY_C_MM_C_DD_C = "yyyy年MM月dd日";

	private static final SimpleDateFormat DATE_CHARACTER_FORMATER = new SimpleDateFormat(
			YYYY_C_MM_C_DD_C);
	
	private static final String YYYY_MM_DD_HH = "yyyy-MM-dd HH";
	
	private static final SimpleDateFormat HOUR_FORMATER = new SimpleDateFormat(
			YYYY_MM_DD_HH);

	static {
		GMT_FORMAT.setCalendar(new GregorianCalendar(new SimpleTimeZone(0,
				"GMT")));
	}

	public static final String getNowTime() {
		return TIME_FORMATER.format(new Date());
	}
	
	public static final String getNowDateHour() {
		return HOUR_FORMATER.format(new Date());
	}
	
	public static final String getStrDateHour(Date date) {
		return HOUR_FORMATER.format(date);
	}
	
	private static final Date getDateHour(String hourStr) {
		if (null == hourStr || "".equals(hourStr)) {
			return null;
		}
		try {
			return HOUR_FORMATER.parse(hourStr);
		} catch (ParseException e) {
			LOGGER.error("解析日期出错", e);
		}
		return null;
	}

	public static final String getNowDate() {
		return DATE_FORMATER.format(new Date());
	}
	
	public static final String getNoSignNowDate() {
		return NO_SIGN_DATE_FORMATER.format(new Date());
	}
	
	public static final String getNoSignDate(Date date) {
		if(date==null){
			return null;
		}
		return NO_SIGN_DATE_FORMATER.format(date);
	}
	
	public static final String getNowCharacterDate() {
		return DATE_CHARACTER_FORMATER.format(new Date());
	}

	public static final String getStrDate(Date date) {
		return DATE_FORMATER.format(date);
	}

	public static final String getDateYear(Date date) {
		return YYYY_FORMATER.format(date);
	}

	public static final Date getDate(String dateStr) {
		if (null == dateStr || "".equals(dateStr)) {
			return null;
		}
		try {
			return DATE_FORMATER.parse(dateStr);
		} catch (ParseException e) {
			LOGGER.error("解析日期出错", e);
		}
		return null;
	}

	public static final String getStrDateTime(Date date) {
		if(date==null){
			return null;
		}
		return TIME_FORMATER.format(date);
	}

	public static final Date getDateTime(String dateStr) {
		if (null == dateStr || "".equals(dateStr)) {
			return null;
		}
		try {
			return TIME_FORMATER.parse(dateStr);
		} catch (ParseException e) {
			LOGGER.error("解析日期出错", e);
		}
		return null;
	}
	
	public static final Date getNoSecondDateTime(String dateStr) {
		if (null == dateStr || "".equals(dateStr)) {
			return null;
		}
		try {
			return NO_SECOND_TIME_FORMATER.parse(dateStr);
		} catch (ParseException e) {
			LOGGER.error("解析日期出错", e);
		}
		return null;
	}

	public static final Integer getMonthDayCount(String date) {
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(simpleDate.parse(date));
			return cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 根据年月 获取月份天数
		} catch (ParseException e) {
			LOGGER.error("解析日期出错", e);
		}
		return null;
	}

	public static final String getNoSignStrDateTime(Date date) {
		return NO_SIGN_TIME_FORMATER.format(date);
	}
	
	public static final Date getNoSignDateTime(String dateStr) {
		if (null == dateStr || "".equals(dateStr)) {
			return null;
		}
		try {
			return NO_SIGN_TIME_FORMATER.parse(dateStr);
		} catch (ParseException e) {
			LOGGER.error("解析日期出错", e);
		}
		return null;
	}

	public static final String convertGMTTime(String gmtTime) {
		try {
			if(gmtTime.indexOf("T")==-1||gmtTime.indexOf("Z")==-1){
				return gmtTime;
			}
			Date date = GMT_FORMAT.parse(gmtTime);
			String result = DateTimeUtil.getStrDateTime(date);
			return result;
		} catch (ParseException e) {
			LOGGER.error("解析GMT日期出错", e);
		}
		return null;
	}

	public static final Map<String, String> getNoSignYesterdayRange() {
		Map<String, String> result = new HashMap<String, String>();
		Date now = new Date();
		Date start = getDateTime(DATE_FORMATER.format(now)+" 00:00:00");
		long yesterdayBegin = start.getTime()-86400000;
		long yesterdayEnd = start.getTime()-1;

		result.put("begin", NO_SIGN_TIME_FORMATER.format(new Date(yesterdayBegin)));
		result.put("end", NO_SIGN_TIME_FORMATER.format(new Date(yesterdayEnd)));
		return result;
	}

	/**
	 * 获取2个日期之间的日期字符串List
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final List<String> getNoSignRangeDates(Date startDate,
			Date endDate) {
		List<String> list = new ArrayList<String>();
		if (startDate == null || endDate == null) {
			return list;
		}
		if (startDate.getTime() > endDate.getTime()) {
			return list;
		}
		Long startTime = startDate.getTime();
		Long endTime = endDate.getTime();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		while (startTime <= endTime) {
			Date d = new Date(startTime);
			list.add(df.format(d));
			startTime += 86400000;
		}
		return list;
	}
	
	/**
	 * 获取2个日期之间的日期之间的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static final int getNoSignRangeDayCount(String startDateStr,
			String endDateStr) {
		int result = 0;
		if(StringUtil.isNullOrEmpty(startDateStr)||StringUtil.isNullOrEmpty(endDateStr)){
			return result;
		}
		if(startDateStr.length()!=endDateStr.length()||startDateStr.compareTo(endDateStr)>0){
			return result;
		}
		startDateStr = startDateStr.replace("-", "").replace(" ", "").replace(":", "");
		endDateStr = endDateStr.replace("-", "").replace(" ", "").replace(":", "");
		if(startDateStr.length()==4){
			startDateStr += "0101";
			endDateStr += "1231";
		}else if(startDateStr.length()==6){
			startDateStr += "01";
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(endDateStr.substring(0, 4)));
	        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推  
	        cal.set(Calendar.MONTH, Integer.parseInt(endDateStr.substring(4, 6))-1);
	        int maxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			endDateStr += maxDay;
		}else{
			startDateStr = startDateStr.substring(0, 8);
			endDateStr = endDateStr.substring(0, 8);
		}
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Long startTime = 0L;
		Long endTime = 0L;
		try {
			startTime = df.parse(startDateStr).getTime();
			endTime = df.parse(endDateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		while (startTime <= endTime) {
			result++;
			startTime += 86400000;
		}
		return result;
	}
	
	public static String convertFromNoSignToHaveSign(String dateTimeStr){
		if(StringUtil.isNullOrEmpty(dateTimeStr)){
			return dateTimeStr;
		}
		dateTimeStr = dateTimeStr.replace("-", "").replace(" ", "").replace(":", "");
		String toAppend = "";
		if(dateTimeStr.length()>=4){
			toAppend = dateTimeStr.substring(0, 4);
		}else{
			return dateTimeStr;
		}
		if(dateTimeStr.length()>=6){
			toAppend += "-" + dateTimeStr.substring(4, 6);
		}
		if(dateTimeStr.length()>=8){
			toAppend += "-" + dateTimeStr.substring(6, 8);
		}
		if(dateTimeStr.length()>=10){
			toAppend += " " + dateTimeStr.substring(8, 10);
		}
		if(dateTimeStr.length()>=12){
			toAppend += ":" + dateTimeStr.substring(10, 12);
		}
		if(dateTimeStr.length()>=14){
			toAppend += ":" + dateTimeStr.substring(12, 14);
		}
		return toAppend;
	}
	
	public static String convertFromNoSignToCharacter(String dateTimeStr){
		if(StringUtil.isNullOrEmpty(dateTimeStr)){
			return dateTimeStr;
		}
		dateTimeStr = dateTimeStr.replace("-", "").replace(" ", "").replace(":", "");
		String toAppend = "";
		if(dateTimeStr.length()>=4){
			toAppend = dateTimeStr.substring(0, 4)+"年";
		}else{
			return dateTimeStr;
		}
		if(dateTimeStr.length()>=6){
			toAppend += dateTimeStr.substring(4, 6)+"月";
		}
		if(dateTimeStr.length()>=8){
			toAppend += dateTimeStr.substring(6, 8)+"日";
		}
		if(dateTimeStr.length()>=10){
			toAppend += " " + dateTimeStr.substring(8, 10)+"时";
		}
		if(dateTimeStr.length()>=12){
			toAppend += dateTimeStr.substring(10, 12)+"分";
		}
		if(dateTimeStr.length()>=14){
			toAppend += dateTimeStr.substring(12, 14)+"秒";
		}
		return toAppend;
	}
	
	public static final Long[] getNoSignRangeDateTime(String startDateStr,
			String endDateStr) {
		Long[] dateTimes = new Long[2];
		if(StringUtil.isNullOrEmpty(startDateStr)&&StringUtil.isNullOrEmpty(endDateStr)){
			return dateTimes;
		}
		if(StringUtil.isNotNullOrEmpty(startDateStr)){
			startDateStr = startDateStr.replace("-", "").replace(" ", "").replace(":", "");
			if(startDateStr.length()==4){
				startDateStr += "0101";
			}else if(startDateStr.length()==6){
				startDateStr += "01";
			}else{
				while(startDateStr.length()<14){					
					startDateStr += "0";
				}
			}
			dateTimes[0] = Long.parseLong(startDateStr);
		}
		if(StringUtil.isNotNullOrEmpty(endDateStr)){			
			endDateStr = endDateStr.replace("-", "").replace(" ", "").replace(":", "");
			if(endDateStr.length()==4){
				endDateStr += "1231";
			}else if(endDateStr.length()==6){
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(endDateStr.substring(0, 4)));
				//下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推  
				cal.set(Calendar.MONTH, Integer.parseInt(endDateStr.substring(4, 6))-1);
				int maxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				endDateStr += maxDay;
			}
			if(endDateStr.length()==8){
				endDateStr += "235959";
			}else if(endDateStr.length()==10){
				endDateStr += "5959";
			}else if(endDateStr.length()==12){
				endDateStr += "59";
			}
			dateTimes[1] = Long.parseLong(endDateStr);
		}
		
		return dateTimes;
	}
	
	public static String[] lastDayStrRange(int dayCount){
		String[] result = new String[2];
		if(dayCount<=0){
			result[0] = "9999-12-31 23:59:59";
			result[1] = "1970-01-01 00:00:00";
			return result; 
		}
		String nowDate = getNowDate();
		String strNowStart = nowDate + " 00:00:00";
		Date startDate = getDateTime(strNowStart);
		for (int i=0;i<(dayCount-1);i++) {
			startDate = new Date(startDate.getTime()-86400000);
		}
		result[0] = getStrDateTime(startDate);
		result[1] = nowDate + " 23:59:59";
		return result;
	}
	
	public static String[] lastNoSignDayStrRange(int dayCount){
		String[] result = new String[2];
		if(dayCount<=0){
			result[0] = "99991231235959";
			result[1] = "19700101000000";
			return result; 
		}
		String nowDate = getNoSignNowDate();
		String strNowStart = nowDate + "000000";
		Date startDate = getNoSignDateTime(strNowStart);
		for (int i=0;i<(dayCount-1);i++) {
			startDate = new Date(startDate.getTime()-86400000);
		}
		result[0] = getNoSignStrDateTime(startDate);
		result[1] = nowDate + "235959";
		return result;
	}
	
	public static Long[] lastMonthRangeIncludeNow(Date date,int monthCount){
		Long[] result = new Long[2];
		if(monthCount<=0){
			result[0] = 999999999999L;
			result[1] = -999999999999L;
			return result; 
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH) + 1;
		if(nowMonth==1){
			nowMonth = 12;
			year--;
		}
		
		String endMonth = nowMonth<10?("0"+nowMonth):(nowMonth+"");
		int endDayCount = getMonthDayCount(year+"-"+endMonth);
		result[1] = Long.parseLong(year+endMonth+endDayCount+"235959");
		
		for (int i=0;i<(monthCount-1);i++) {
			if(nowMonth==1){
				year--;
				nowMonth = 12;
			}else{
				nowMonth--;
			}
		}
		String startMonth = nowMonth<10?("0"+nowMonth):(nowMonth+"");
		result[0] = Long.parseLong(year+startMonth+"01000000");
		
		return result;
	}
	
	public static String[] lastMonthStrRangeIncludeNow(int monthCount){
		String[] result = new String[2];
		if(monthCount<=0){
			result[0] = "9999-12-31 23:59:59";
			result[1] = "1970-01-01 00:00:00";
			return result; 
		}
		Date now = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR);
		int nowMonth = cal.get(Calendar.MONTH) + 1;
		
		String endMonth = nowMonth<10?("0"+nowMonth):(nowMonth+"");
		int endDayCount = getMonthDayCount(year+"-"+endMonth);
		result[1] = year+"-"+endMonth+"-"+endDayCount+" 23:59:59";
		
		for (int i=0;i<(monthCount-1);i++) {
			if(nowMonth==1){
				year--;
				nowMonth = 12;
			}else{
				nowMonth--;
			}
		}
		String startMonth = nowMonth<10?("0"+nowMonth):(nowMonth+"");
		result[0] = year+"-"+startMonth+"-01 00:00:00";
		
		return result;
	}
	
	public static String[] lastYearStrRange(int yearCount){
		String[] result = new String[2];
		if(yearCount<=0){
			result[0] = "9999-12-31 23:59:59";
			result[1] = "1970-01-01 00:00:00";
			return result; 
		}
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		int year = cal.get(Calendar.YEAR);
		result[1] = year+"-12-31 23:59:59";
		
		for (int i=0;i<(yearCount-1);i++) {
			year--;
		}
		result[0] = year+"-01-01 00:00:00";
		
		return result;
	}
	
	public static String convertTime(String time){
		if(StringUtil.isNullOrEmpty(time)){
			return time;
		}
		time = time.replace("-", "").replace(" ", "").replace(":", "");
		return time.substring(0, 4)+"-"+time.substring(4, 6)+"-"+time.substring(6, 8)+" "+time.substring(8, 10)+":"+time.substring(10, 12)+":"+time.substring(12, 14);
	}
	
	public static long innerGetDailyExpireUnixTime() {
		String todayLastSecond = DateTimeUtil.getNowDate() + " 23:59:59";
		return DateTimeUtil.getDateTime(todayLastSecond).getTime() / 1000;
	}
	
	public static Date convertTimestampToDate(Timestamp timestamp){
		if(timestamp==null){
			return null;
		}
		return new java.util.Date(timestamp.getTime());
	}
	
	public static void main(String[] args) {
		Date beginTime = DateTimeUtil.getDateTime("2009-10-09 16:12:11");
		Date endTime = DateTimeUtil.getDateTime("2009-10-09 16:13:22");
		long seconds = endTime.getTime() - beginTime.getTime();
		int min = (int)seconds/60000;
		int sec = ((int)seconds%60000)/1000;
		System.out.println((int)seconds/60000);
		System.out.println((int)seconds%60000);
		System.out.println(((int)seconds%60000)/1000);
		String str=String.format("%02d", sec);  
		System.out.println(str);
		System.out.println(String.format("%02d", min));
		// System.out.println(DateTimeUtil.class.getName());
		// System.out.println(getMonthDayCount("2012-02"));
		// System.out.println(getMonthDayCount("2015-02"));

		// System.out.println(getLastYearRange());

		/*Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.MONTH, -1);
		Calendar c2 = Calendar.getInstance();
		List<String> list = (getDateBetweenDates(c1.getTime(), c2.getTime()));
		for (String s : list) {
			System.out.println(s);
		}*/
		
		/*System.out.println(getLastMonthRange());
		System.out.println(getDate("2014-08-25"));*/
		/*List<String> hourRanges = getRangeHours("2015-08-28 15",
				"2015-09-07 15");
		for (String hour : hourRanges) {			
			System.out.println(hour);
		}*/
		
		/*System.out.println(convertFromNoSignToHaveSign("20140328123648"));
		System.out.println(convertFromNoSignToCharacter("201403281236"));*/
		
		/*System.out.println(getRangeDayCount("201302",
				"201302"));*/
		
		/*Long[] range = lastDayRange(90);
		System.out.println(range[0]+"-"+range[1]);
		range = lastMonthRange(90);
		System.out.println(range[0]+"-"+range[1]);*/
		
		/*Long[] range = lastMonthRange(1);
		Date date = getNoSignTime(range[0]+"");
		String endStr = getNoSignStrTime(new Date(date.getTime()-1000));
		long end = Long.parseLong(endStr);
		System.out.println(end);
		System.out.println(range[0]+"---"+range[1]);
		range = lastMonthRange(4);
		System.out.println(range[0]+"---"+range[1]);*/
		
		/*String var = "2014-03-28 12:36:48";
		String test = convertFromNoSignToCharacter(var);
		
		System.out.println(test);
		System.out.println(var);
		
		System.out.println(getNoSignYesterdayRange());*/
		
		/*String end = "201202";
		int dayCount = DateTimeUtil.getMonthDayCount(end.substring(0, 4)+"-"+end.substring(4, 6));
		System.out.println(dayCount);*/
		
		
		//System.out.println(innerGetDailyExpireUnixTime());
		
		//System.out.println("zhsj_201508".substring(5, "zhsj_201508".length()));
		
		//System.out.println(getMonthDayCount("2015-02"));
		/*long time = 1479359724690L;
		System.out.println(new Date(time));*/
//		System.out.println("12345".substring(0, 6));
		
		String operateTime = "20161215200240";
		System.out.println(operateTime.substring(0, 4));
		System.out.println(operateTime.substring(4, 6));
		System.out.println(operateTime.substring(6, 8));
		System.out.println(operateTime.substring(8, 10));
		System.out.println(operateTime.substring(10, 12));
		System.out.println(operateTime.substring(12));
		
	}

}
