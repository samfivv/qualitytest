package com.midai.miya.utils.mail;

import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

import com.midai.miya.common.CommonThreadFactory;
/**
 * 发送邮件线程池
 * @author hyz
 * @Company 米袋网络
 * 2015年10月21日
 */
public class MailPoolExecutor implements Serializable{
	private static final long serialVersionUID = 4094417033804580747L;
	public static final Logger logger = Logger.getLogger(MailPoolExecutor.class);
	private static final ThreadPoolExecutor EXECUTORS = (ThreadPoolExecutor) Executors.newFixedThreadPool(10, new CommonThreadFactory("myw-mail-cu-core") );
	
	public void putThreadPooLExecute(MailSenderInfo mailInfo) {
		logger.info("wait MailPoolExecutor  execute task "+EXECUTORS.getActiveCount());
		EXECUTORS.execute(new SendMailCoreTask(mailInfo));
	}


	
	class SendMailCoreTask implements Runnable{
		MailSenderInfo mailInfo;
		public SendMailCoreTask(MailSenderInfo mailInfo) {
			this.mailInfo =  mailInfo;
		}
		@Override
		public void run() {
			logger.info("startsend mail:"+mailInfo.getToAddress());
			SimpleMailSender.sendHtmlMail(mailInfo);// 发送html格式
			logger.info("end send mail:"+mailInfo.getToAddress());
		}
	}
}
