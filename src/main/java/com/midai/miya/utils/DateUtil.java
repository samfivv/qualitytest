package com.midai.miya.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author 孙宇
 * 
 */
public class DateUtil {

	public static void main(String[] args) {

	}

	/**
	 * 把秒转成时间格式 黄扬仲 2015年7月14日
	 */
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":"
						+ unitFormat(second);
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}

	/**
	 * 时间转成秒 传入格式HH:mm:ss 黄扬仲 2015年7月30日
	 */
	public static long getSecond(String time) {
		long second = Integer.parseInt(time.substring(0, time.indexOf(":", 0))) * 3600; // 小时
		second += Integer.parseInt(time.substring(time.indexOf(":", 0) + 1,
				time.lastIndexOf(":"))) * 60; // 分钟
		second += Integer.parseInt(time.substring(time.lastIndexOf(":") + 1,
				time.length())); // 秒
		return second;
	}

	/**
	 * 将秒转成时间格式 HH:mm:ss 黄扬仲 2015年7月30日
	 */
	public static String formatSecond(long second) {
		String html = "00:00:00";
		Integer hours = (int) (second / (60 * 60));
		Integer minutes = (int) (second / 60 - hours * 60);
		Integer seconds = (int) (second - minutes * 60 - hours * 60 * 60);
		String hoursStr = "00";
		if (hours > 0) {
			hoursStr = hours >= 10 ? "" + hours : "0" + hours;
		}
		String minutesStr = "00";
		if (minutes > 0) {
			minutesStr = minutes >= 10 ? "" + minutes : "0" + minutes;
		}
		String secondsStr = "00";
		if (seconds > 0) {
			secondsStr = seconds >= 10 ? "" + seconds : "0" + seconds;
		}
		html = hoursStr + ":" + minutesStr + ":" + secondsStr;
		return html;

	}
	
	/**
	 *  获取时间相差秒数
	 *  黄扬仲
	 *  2015年8月5日
	 */
	public static long getNumberOfSeconds(Date startDay, Date endDay) {
		return (endDay.getTime() - startDay.getTime()) / (1000);
	}
}
