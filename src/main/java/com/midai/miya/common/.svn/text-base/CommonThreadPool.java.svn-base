package com.midai.miya.common;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public final class CommonThreadPool {
	
	private final  static Executor esbHrsThreadPool= Executors.newFixedThreadPool(2, new CommonThreadFactory("esbHrsThreadPool"));
	
	private final  static Executor  exceptionReportPool= Executors.newFixedThreadPool(4, new CommonThreadFactory("esbHrsThreadPool"));
	
	private final  static Executor  siteSelectionSystemScorePool= Executors.newFixedThreadPool(2, new CommonThreadFactory("siteSelectionSystemScorePool"));
	
	public  static void executeEsbHrsThread(Runnable esbHrsThread){
		esbHrsThreadPool.execute(esbHrsThread);
	}
	
	
	public  static void executeExceptionReportThread(Runnable esbHrsThread){
		exceptionReportPool.execute(esbHrsThread);
	}	

	
	public  static void executeSiteSelectionSystemScoreThread(Runnable systemScoreThread){
		siteSelectionSystemScorePool.execute(systemScoreThread);
	}	
	
}
