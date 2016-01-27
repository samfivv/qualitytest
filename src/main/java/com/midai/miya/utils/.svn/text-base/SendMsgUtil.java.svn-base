package com.midai.miya.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 发送短信工具类
 * @author hyz
 * @Company 米袋网络
 * 2015年9月2日
 */
public class SendMsgUtil implements Serializable{
	private static final long serialVersionUID = 214565432248499398L;

	/**
	 *  发送短信
	 *  黄扬仲
	 *  2015年9月2日
	 */
	 public static String sendMsg(Message message){
		   String url=message.getUrl();
		   Map<String, String> params=new HashMap<String, String>();
		   params.put("account",message.getAccount());
		   params.put("pswd", message.getPswd());
		   params.put("mobile",message.getMobile());
		   params.put("msg", message.getMsgContent());
		   params.put("needstatus", "true");
		  String res = HttpUtil.doPost(url, params);
		  return res;
	   }
}
