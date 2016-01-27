package com.midai.miya.utils;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
 
public class CalendarUtil {
 
	static SimpleDateFormat  sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat  sdf2 = new SimpleDateFormat("yyyyMMdd");
 
    public static void main(String[] args) throws ParseException {
        CalendarUtil test = new CalendarUtil();
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        List<String> dates = getDates(start, end);
        test.printDate("Start\t", start.getTime());
        for (String date : dates) {
        	System.out.println(date);
        }
       
    }
    
    public static  List<String> getBtDates(String startStr,String endStr) {
        Calendar start = Calendar.getInstance();
        List<String> dates=null;
        try {
			start.setTime(sdf.parse(startStr));
			Calendar end = Calendar.getInstance();
	        end.setTime(sdf.parse(endStr));
	        dates = getDates(start, end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return dates;
       
    }
 
 
    // Print the date with the format.
    public void printDate(String p_msg, Date p_date) {
        System.out.println(p_msg + sdf.format(p_date));
    }
 
    /**
     * 获取一个时间段内的每一天
     * @param p_start   Start Date
     * @param p_end     End Date
     * @return Dates List
     */
    public static List<String> getDates(Calendar p_start, Calendar p_end) {
        List<String> result = new ArrayList<String>();
        Calendar temp = (Calendar) p_start.clone();
        while (temp.before(p_end)||temp.equals(p_end)) {
            result.add(sdf2.format(temp.getTime()));
            temp.add(Calendar.DAY_OF_YEAR, 1);
        }
        temp.add(Calendar.DAY_OF_YEAR, 1);
        return result;
    }
    /**
     * 定义一个24小时的数组
     * 王梦圆
     * 2015年5月25日
     */
    public static String[] getHours(){
    	 String[] hours=new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13"
					,"14","15","16","17","18","19","20","21","22","23"};
    	 return hours;
    }
}