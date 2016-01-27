package com.midai.miya.utils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {

	/**
	 * 浮点数相加
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static double add(double v1, double v2) {// 加法
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 浮点数相减
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static double sub(double v1, double v2) {// 减法
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 浮点数相乘
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static double mul(double v1, double v2) {// 乘法
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 浮点数相除
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static double div(double v1, double v2) {// 除法
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 截取3位
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static double round(double v) {// 截取3位
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


	/**
	 * 保留两位小数
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static String decimalFormat(double value) {
		return new DecimalFormat("0.00").format(value);
	}

	/**
	 * 根据模板格式话浮点数
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static String decimalFormat(double value, String pattern) {
		return new DecimalFormat(pattern).format(value);
	}

	

	/**
	 * 检查是否是数字
	 * 王梦圆
	 * 2015年8月25日
	 */
	public static boolean isNumber(String value) { // 检查是否是数字
		String patternStr = "^\\d+$";
		Pattern p = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE); // 忽略大小写;
		Matcher m = p.matcher(value);
		return m.find();
	}

	public static void main(String args[]){
		System.out.println(add(0.4,0.2));
	}
}