package com.midai.miya.utils;


import java.util.Date;

import javax.servlet.http.HttpSession;

import com.midai.miya.constant.Constant;

public class SessionUtil {

	/**
	 * 防止获取验证码的时候用户频繁点击获取 60秒内只发送一次
	 * @param session
	 * @return
	 */
	public static boolean limitVerifyCode(HttpSession session,String phone){
		Date sendTime = (Date)session.getAttribute(Constant.VERIFY_CODE_SEND_TIME+phone);
		if(sendTime != null){
			long overtime = DateUtil.getNumberOfSeconds(sendTime, new Date());
			if(overtime < 60){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 检查验证码是否过期和正确 0:检查通过   1:请先获取验证码  2:验证码错误  3:验证码过期
	 * @param session
	 * @return
	 */
	public static int checkVerifyCode(HttpSession session,String code,String phone){
		Date sendTime = (Date)session.getAttribute(Constant.VERIFY_CODE_SEND_TIME+phone);
		String verifycode = (String) session.getAttribute(Constant.VERIFY_CODE+phone);
		if(verifycode == null){
			return 1;
		}else {
			if(!(verifycode.equals(code))){
				return 2;
			}
		}
		if(sendTime == null){
			return 1;
		}else {
			long overtime = DateUtil.getNumberOfSeconds(sendTime, new Date());
			//90秒过期
			if(overtime > 90){
				return 3;
			}
		}
		return 0;
	}
	
	/**
	 * 将短信验证码放入session中
	 * @param session
	 * @param code
	 */
	public static void setVerifyCode(HttpSession session,String code,String phone){
		session.setAttribute(Constant.VERIFY_CODE+phone, code);
		session.setAttribute(Constant.VERIFY_CODE_SEND_TIME+phone, new Date());
	}
	
}
