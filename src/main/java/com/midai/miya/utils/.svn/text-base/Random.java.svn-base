package com.midai.miya.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class Random {
	private static final char[] DIGIT_CODE = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9' };
	private static final char[] LETTER_CODE = { 'a', 'b', 'c', 'd', 'e', 'f',
			'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
			't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * 生成任意位数随机数
	 * 
	 * @param length
	 *            (位数)
	 * @return 指定位数的随机数
	 */
	public static String genVerifyCode(int length) {
		return RandomStringUtils.random(length, DIGIT_CODE);
	}

	/**
	 * 生成任意位数随机字符
	 * 
	 * @param length
	 *            (位数)
	 * @return 指定位数的随机字符
	 * 
	 */
	public static String genLetterCode(int length) {
		return RandomStringUtils.random(length, LETTER_CODE);
	}

}
