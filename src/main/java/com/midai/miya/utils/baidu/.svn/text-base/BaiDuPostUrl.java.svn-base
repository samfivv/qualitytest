package com.midai.miya.utils.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;


/**
 * 链接提交到百度收录
 * @author hyz
 * @Company 米袋网络
 * 2015年11月3日
 */
public class BaiDuPostUrl implements Serializable{
	private static final long serialVersionUID = -4526891822540909787L;
	public static final Logger logger = Logger.getLogger(BaiDuPostUrl.class);
	
	
	/**
	 *  例子
	 *  黄扬仲
	 *  2015年11月3日
	 */
	public static void main(String[] args) {
		//需要推送的网址
		String[] param = {
				"http://www.qumiya.com/mdmy/interest/detail/d5aa3be24004446fb2aa59e90f4f333e/1.html",
				"http://www.qumiya.com/mdmy/interest/detail/679711daa3554ddbb6fcf7dec4c49f08/1.html"
				
		};
	    PostToBaidu(param);//执行推送方法

	}
	
	/**
	 * 百度链接实时推送
	 * @param PostUrl
	 * @param Parameters
	 * @return
	 */
	public static String PostToBaidu(String[] Parameters){
		String postUrl = "http://data.zz.baidu.com/urls?site=www.qumiya.com&token=Ra2MEp9pjoduFk9J";//网站的服务器连接
        String result="";
        PrintWriter out=null;
        BufferedReader in=null;
        try {
            //建立URL之间的连接
            URLConnection conn=new URL(postUrl).openConnection();
            //设置通用的请求属性
            conn.setRequestProperty("Host","data.zz.baidu.com");
            conn.setRequestProperty("User-Agent", "curl/7.12.1");
            conn.setRequestProperty("Content-Length", "83");
            conn.setRequestProperty("Content-Type", "text/plain");
             
            //发送POST请求必须设置如下两行
            conn.setDoInput(true);
            conn.setDoOutput(true);
             
            //获取conn对应的输出流
            out=new PrintWriter(conn.getOutputStream());
            //发送请求参数
            String param = "";
            for(String s : Parameters){
            	param += s+"\n";
            }
            out.print(param.trim());
            //进行输出流的缓冲
            out.flush();
            //通过BufferedReader输入流来读取Url的响应
            in=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while((line=in.readLine())!= null){
                result += line;
            }
             
        } catch (Exception e) {
            logger.error(e);
        } finally{
            try{
                if(out != null){
                    out.close();
                }
                if(in!= null){
                    in.close();
                }
                 
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        logger.info(result);
        return result;
    }

}
