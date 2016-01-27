package sy.test;

import java.io.*;
import java.util.HashMap;


import com.midai.miya.utils.HttpUtil;

/**
 * 静态页面引擎技术（突乱了乱码问题UTF-8）
 * @author 吴彦文
 *
 */
public class HtmlGenerator {
	BufferedWriter fw = null;
	String page = null;
	String webappname = null;
	BufferedReader br = null;
	InputStream in = null;
	StringBuffer sb = null;
	String line = null; 
	//构造方法
	public HtmlGenerator(String webappname){
		this.webappname = webappname;
		
	}
	
	/** 根据模版及参数产生静态页面 */
	public boolean createHtmlPage(String url,String htmlFileName){
		boolean status = false;	
		try{
				//读取解析结果
				page = HttpUtil.doGet(url, new HashMap<String, String>());
				//将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
				page = formatPage(page);
				//将解析结果写入指定的静态HTML文件中，实现静态HTML生成
				writeHtml(htmlFileName,page);
		}catch(Exception ex){
			System.out.println("静态页面引擎在解析"+url+"产生静态页面"+htmlFileName+"时出错:"+ex.getMessage());			
        }
		return status;
	}
	
	//将解析结果写入指定的静态HTML文件中
	private synchronized void writeHtml(String htmlFileName,String content) throws Exception{
		fw = new BufferedWriter(new FileWriter(htmlFileName));
		OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(htmlFileName),"UTF-8");
		fw.write(page);	
		if(fw!=null)fw.close();		
	}
	
	//将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
	private String formatPage(String page){		
		page = page.replaceAll("\\.\\./\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./", webappname+"/");			
		return page;
	}
	
	//测试方法
	public static void main(String[] args){
		HtmlGenerator h = new HtmlGenerator("webappname");
		h.createHtmlPage("http://www.qumiya.com/mdmy/interest/index.html","D:/html/a.html");
		System.out.println("静态页面已经生成到D:/html/a.html");
		
	}

}