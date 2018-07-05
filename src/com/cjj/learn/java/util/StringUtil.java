package com.cjj.learn.java.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);
	
	private static final String IP = "((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.(25[0-5]|2[0-4]\\d|[01]?\\d\\d?))"
    		+ "|"
    		+ "(((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b)\\.){3}(\\b((25[0-5])|(1\\d{2})|(2[0-4]\\d)|(\\d{1,2}))\\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:)))";

	private static Random r = new Random();
	
	public static boolean isNullOrEmpty(String str){
		return null==str||str.length()==0;
	}
	
	public static boolean isNotNullOrEmpty(String str){
		return str!=null&&str.length()>0;
	}
	
	public static String convertRate(long count,long total){
		if(total==0){
			return "0";
		}
		DecimalFormat fmt = new DecimalFormat(".##%");
		String rate = fmt.format((double)count / (double)total);
		if (rate.equals(".0%") || rate.equals(".00%")) {
			rate = "0";
		} else if (rate.indexOf(".") == 0) {
			rate = "0" + rate;
		}
		return rate;
	}
	
	public static boolean isAllNumeric(String str) {
		if(str==null){
			return false;
		}else if(str.indexOf(" ")!=-1){
			return false;
		}else{			
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher m = pattern.matcher(str);
			if (m.find() && m.group(0).equals(str)) {
				return true;
			}
			return false;
		}
	}
	
	public static boolean isAllEnglish(String str) {
		if(str==null){
			return false;
		}else if(str.indexOf(" ")!=-1){
			return false;
		}else{			
			Pattern pattern = Pattern.compile("[a-zA-Z]*");
			Matcher m = pattern.matcher(str);
			if (m.find() && m.group(0).equals(str)) {
				return true;
			}
			return false;
		}
	}
	
	public static boolean isAllChinese(String str) {
		if(str==null){
			return false;
		}else if(str.indexOf(" ")!=-1){
			return false;
		}else{			
			Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]*");
			Matcher m = p_str.matcher(str);
			if (m.find() && m.group(0).equals(str)) {
				return true;
			}
			return false;
		}
	}
	
	public static boolean mayBeIdCard(String content) {
		if (StringUtil.isNotNullOrEmpty(content)) {
			Pattern p = Pattern.compile("^"+Contants.REGEX_JAVA_STANDARD_ID_NO+"$");
			Matcher m = p.matcher(content);
			return m.find();
		}
		return false;
	}
	
	public static boolean isOperateType(String aStr){
		if(aStr==null){
			return false;
		}
		String aNum = "[0-4]{1}";
		Pattern pStr = Pattern.compile(aNum);
		Matcher m = pStr.matcher(aStr);
		if (m.find() && m.group(0).equals(aStr)) {
			return true;
		}else{
			return false;
		}
	}
	
	public static String[] split(String str, String delims) {  
        StringTokenizer st = new StringTokenizer(str, delims);  
        List list = new ArrayList();  
        for (; st.hasMoreTokens(); list.add(st.nextToken())) { 
        	
        }
        return (String[]) list.toArray(new String[list.size()]);  
    }  
	
	/*public static boolean isNullOrEmpty(String str){
		return null==str||str.length()==0;
	}
	
	public static boolean isNotNullOrEmpty(String str){
		return str!=null&&str.length()>0;
	}
	
	public static String convertRate(long count,long total){
		if(total==0){
			return "0";
		}
		DecimalFormat fmt = new DecimalFormat(".##%");
		String rate = fmt.format((double)count / (double)total);
		if (rate.equals(".0%") || rate.equals(".00%")) {
			rate = "0";
		} else if (rate.indexOf(".") == 0) {
			rate = "0" + rate;
		}
		return rate;
	}
	
	public static String getRandomStr(){
		String rs = r.nextFloat()*r.nextInt()+"";
		if(rs.indexOf("-")==0){			
			rs = rs.substring(1);
		}
		rs = rs.replace(".", "");
		return rs;
	}
	
	private static boolean isChinese(String str) {
		Pattern p_str = Pattern.compile("[\\u4e00-\\u9fa5]");
		Matcher m = p_str.matcher(str);
		if (m.find() && m.group(0).equals(str)) {
			return true;
		}
		return false;
	}
	
	private static boolean isNotChinese(String str) {
		Pattern p_str = Pattern.compile("[^(\\u4e00-\\u9fa5)]");
		Matcher m = p_str.matcher(str);
		if (m.find() && m.group(0).equals(str)) {
			return true;
		}
		return false;
	}
	
	public static String getChinese(String str){
		String result = "";
		char[] chars = str.toCharArray();
		for(char c : chars){
			String single = c+"";
			if(isChinese(single)){
				result += single;
			}
		}
		return result;
	}
	
	public static String getNotChinese(String str){
		String result = "";
		char[] chars = str.toCharArray();
		for(char c : chars){
			String single = c+"";
			if(isNotChinese(single)){
				result += single;
			}
		}
		return result;
	}

	public static String getHostIp(){
		InetAddress netAddress = null;
		try {
			netAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			LOGGER.error("获取当前服务器地址失败", e);
		} catch(Exception e){
			LOGGER.error("获取当前服务器地址失败", e);
		}
		if(null == netAddress){
			return null;
		}
		return netAddress.getHostAddress();
	}
	
	public static String parseIdNos(String content) {
		String idCards = null;
		if (StringUtil.isNotNullOrEmpty(content)) {
			Pattern p = Pattern.compile(Contants.REGEX_JAVA_STANDARD_ID_NO);
			
			Matcher m = p.matcher(content);
			while (m.find()) {
				String idCard = m.group();
				if (StringUtil.isNotNullOrEmpty(idCard)) {
					if(idCards==null){
						idCards = idCard;
					}else {
						idCards += " "+idCard;
					}
				}
			}
		}
		System.out.println(idCards);
		return idCards;
	}
	
	public static boolean isIp(String ipAddress)
	{
        Pattern pattern = Pattern.compile(IP); 
        Matcher matcher = pattern.matcher(ipAddress); 
        return matcher.matches(); 
	}
	
	public static String converseNull(Object object){
		return object == null?null:object.toString();
	}
	
	
	private static boolean isChinese2(String str) {
		Pattern p_str = Pattern.compile(".*[\\u4e00-\\u9fa5].*");
		Matcher m = p_str.matcher(str);
		return m.matches();
	}
	
	public static boolean isAllNumeric(String str) {
		if(str==null){
			return false;
		}else if(str.indexOf(" ")!=-1){
			return false;
		}else{			
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches()) {
				return false;
			}
			return true;
		}
	}
	
	public static boolean isAllEnglish(String str) {
		if(str==null){
			return false;
		}else if(str.indexOf(" ")!=-1){
			return false;
		}else{			
			Pattern pattern = Pattern.compile("[a-zA-Z]*");
			Matcher isNum = pattern.matcher(str);
			if (!isNum.matches()) {
				return false;
			}
			return true;
		}
	}
	
	public static boolean mayBeIdCard(String content) {
		if (StringUtil.isNotNullOrEmpty(content)) {
			Pattern p = Pattern.compile("^"+Contants.REGEX_JAVA_STANDARD_ID_NO+"$");
			Matcher m = p.matcher(content);
			return m.find();
		}
		return false;
	}*/
	
	public static void main(String[] args) {
		/*System.out.println(getHostIp());
		System.out.println(StringUtil.getNotChinese("'我,<www,门sg脸"));
		System.out.println(StringUtil.getChinese("ミ灬小孟"));
		System.out.println(StringUtil.getChinese("154544"));
		
		System.out.println();
		
		System.out.println(StringUtil.isChinese2("'我,<www,门sg脸"));
		System.out.println(StringUtil.isChinese2("小孟"));
		System.out.println(StringUtil.isChinese2("154544"));*/
		
		//System.out.println(mayBeIdCard(""));
		
		//"SELECT * FROM QS_LGY_2011 WHERE ZKLSH in (SELECT A.Zklsh FROM ZHCX_LGY.QS_LGY_2011 A, QS_LGY_2011 B where A.ZJHM <> '522228199002263130' AND A.ZSLG = B.ZSLG AND A.FH = B.FH AND A.RZSJ BETWEEN TO_CHAR(TO_DATE(B.RZSJ, 'YYYY-MM-DD HH24:mi:ss') - 10 / 60 / 24,'YYYYMMDDHH24MISS')AND TO_CHAR(TO_DATE(B.RZSJ, 'YYYY-MM-DD HH24:mi:ss')- (-10) / 60 / 24,'YYYYMMDDHH24MISS') AND B.ZJHM = '522228199002263130')"
		
		//System.out.println(StringUtil.mayBeIdCard("52222819900226313x"));
		
		//System.out.println(IdcardUtils.validateCard("5222281990022631301"));
		
		//System.out.println(isIp("10.118.161.238"));
		
		//System.out.println(StringUtil.isAllEnglish("addZZ"));
		
		//System.out.println(StringUtil.isChinese("我门脸"));//.getChinese("'我,<www,门sg脸")
		
		System.out.println(StringUtil.isAllChinese(" 我门我都脸 "));
		
		
	}
	
}
