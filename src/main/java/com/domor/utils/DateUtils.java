package com.domor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DateUtils {

	public static final String DATE_FROMAT1 = "yyyy-MM-dd";
	public static final String DATE_FROMAT2 = "yyyy-MM-dd HH:mm:ss";

	public static Date getDate(String s) {
		return getDate(s, null);
	}

	public static Date getJustDate(String s) {
		return getDate(s, "yyyy-MM-dd");
	}

	public static Date getDate(long date) {
		return getDate(date, null);
	}

	public static Date getJustDate(long date) {
		return getDate(date, "yyyy-MM-dd");
	}

	public static Date getDate(long date, String format) {
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}

		return getDate(formatDate(new Date(date), format), format);
	}

	public static Date getDate(String s, String format) {
		Date date;
		try {
			if (format == null || "".equals(format)) {
				format = "yyyy-MM-dd HH:mm:ss";
			}

			date = new SimpleDateFormat(format).parse(s);
		} catch (Exception e) {
			date = new Date(0L);
		}

		return date;
	}

	public static String formatDate(long date, String format) {
		return formatDate(new Date(date), format);
	}

	public static String formatDate(long date) {
		return formatDate(new Date(date), null);
	}

	public static String formatJustDate(long date) {
		return formatDate(new Date(date), "yyyy-MM-dd");
	}

	public static String formatDate(Date date, String format) {
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}

		return new SimpleDateFormat(format).format(date);
	}
	
	/**
	 * 根据结束时间获取最近一个月的所有日期
	 * @param Date edate
	 * @return List<Date>
	 * @throws ParseException 
	 */
	public static List<String> getOneMonthDate(Date edate) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(edate);
		c.add(Calendar.MONTH, -1);
		Date bdate = c.getTime();
		List<Date> dates = getDatesBetweenTwoDate(bdate, edate); 
		
		List<String> sdate = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(Date d:dates){
			sdate.add(sdf.format(d));
		}
		return sdate;
	}
	
	 /** 
     * 根据开始时间和结束时间返回时间段内的时间集合 
     * @param beginDate 
     * @param endDate 
     * @return List 
     */  
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {  
        List<Date> lDate = new ArrayList<Date>();  
        lDate.add(beginDate);// 把开始时间加入集合  
        Calendar cal = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间  
        cal.setTime(beginDate);  
        boolean bContinue = true;  
        while (bContinue) {  
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            cal.add(Calendar.DAY_OF_MONTH, 1);  
            // 测试此日期是否在指定日期之后  
            if (endDate.after(cal.getTime())) {  
                lDate.add(cal.getTime());  
            } else {  
                break;  
            }  
        }  
        lDate.add(endDate);// 把结束时间加入集合  
        return lDate;  
    }
    /**
     * 根据月份获取前十二个月的月份
     * @param String yyyy-MM
     * @return List<String>
     */
    public static List<String> getLast12Months(String d){ 
		List<String> last12Months = new ArrayList<String>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date date = sdf.parse(d);
	        
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(date);
	        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-12); 
	        
	        for(int i=0; i<12; i++){  
	            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1); 
	            String year = cal.get(Calendar.YEAR)+"";
	            String month = (cal.get(Calendar.MONTH)+1)<10?("0"+(cal.get(Calendar.MONTH)+1)):(""+(cal.get(Calendar.MONTH)+1));
	            last12Months.add(year+"-"+month);
	        } 
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return last12Months;
    }
}
