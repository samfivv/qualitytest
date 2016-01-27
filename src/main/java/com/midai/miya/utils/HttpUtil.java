package com.midai.miya.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
 
/**
 * 基于 httpclient 4.3.6版本的 http工具类
 *
 */
public class HttpUtil {
	
	private static final Log LOG = LogFactory.getLog(HttpUtil.class);
    private static final CloseableHttpClient httpClient;
    public static final String CHARSET = "UTF-8";
    static {
        RequestConfig config = RequestConfig.custom().setConnectTimeout(60000).setSocketTimeout(15000).build();
        httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }
 
    public static String doGet(String url, Map<String, String> params){
        return doGet(url, params,CHARSET);
    }
    public static String doPost(String url, Map params){
        return doPost(url, params,CHARSET);
    }
    /**
     * HTTP Get 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @return    页面内容
     */
    public static String doGet(String url,Map<String,String> params,String charset){
    	LOG.info("HttpUtil doGet url====="+url);
    	if(params!=null){
    		LOG.info("HttpUtil doGet params====="+JSONObject.toJSON(params));
    	}
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, CHARSET);
                LOG.info("HttpUtil result===="+result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
        	LOG.error("doGet error ",e);
        }
        return null;
    }
     
    /**
     * HTTP Post 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @param charset    编码格式
     * @return    页面内容
     */
    public static String doPost(String url,Map<String,String> params,String charset){
    	LOG.info("HttpUtil doPost url====="+url);
     	if(params!=null){
     		LOG.info("HttpUtil doPost params====="+JSONObject.toJSON(params));
     	}
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if(params != null && !params.isEmpty()){
                pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
            }
            HttpPost httpPost = new HttpPost(url);
            if(pairs != null && pairs.size() > 0){
                httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, CHARSET);
                LOG.info("HttpUtil result===="+result);
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
        	LOG.error("doPost error ",e);
        }
        return null;
    }
    
   public static String doPost(String url ,String content){
	   LOG.info("HttpUtil doPost url====="+url);
   	   LOG.info("HttpUtil doPost content====="+content);
	   HttpPost httpPost = new HttpPost(url);
	   try {
		httpPost.setEntity(new ByteArrayEntity(content.getBytes(CHARSET),ContentType.APPLICATION_JSON));
	    CloseableHttpResponse response = httpClient.execute(httpPost);
	    int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            httpPost.abort();
            throw new RuntimeException("HttpClient,error status code :" + statusCode);
        }
        HttpEntity entity = response.getEntity();
        String result = null;
        if (entity != null){
            result = EntityUtils.toString(entity, CHARSET);
            LOG.info("HttpUtil result===="+result);
        }
        EntityUtils.consume(entity);
        response.close();
        return result;
	} catch (UnsupportedEncodingException e) {
    	LOG.error("doPost UnsupportedEncodingException ",e);
	} catch (ClientProtocolException e) {
    	LOG.error("doPost ClientProtocolException ",e);
	} catch (IOException e) {
    	LOG.error("doPost IOException ",e);
	}
	   return null;
   }
   
   /**
    * HTTP Get 获取内容
    * @param url  请求的url地址 ?之前的地址
    * @param params 请求的参数
    * @param charset    编码格式
    * @return    页面内容
    */
   public static byte[] getDownLoad(String url,Map<String,String> params,String charset){
	   LOG.info("HttpUtil getDownLoad url====="+url);
	   	if(params!=null){
	   		LOG.info("HttpUtil getDownLoad params====="+JSONObject.toJSON(params));
	   	}
       if(StringUtils.isBlank(url)){
           return null;
       }
       CloseableHttpResponse response =null;
       try {
           if(params != null && !params.isEmpty()){
               List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
               for(Map.Entry<String,String> entry : params.entrySet()){
                   String value = entry.getValue();
                   if(value != null){
                       pairs.add(new BasicNameValuePair(entry.getKey(),value));
                   }
               }
               url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
           }
           HttpGet httpGet = new HttpGet(url);
            response = httpClient.execute(httpGet);
           int statusCode = response.getStatusLine().getStatusCode();
           if (statusCode != 200) {
               httpGet.abort();
               throw new RuntimeException("HttpClient,error status code :" + statusCode);
           }
           long length =  response.getEntity().getContentLength();
           if(length==0) return null;
           byte[] result = new byte[(int) length];
           response.getEntity().getContent().read(result);
           return result;
       } catch (Exception e) {
       		LOG.error("doGet error ",e);
       }
       return null;
   }
    
 
   /**
    * HTTP Post 获取内容
    * @param url  请求的url地址 ?之前的地址
    * @param params 请求的参数
    * @param charset    编码格式
    * @return    页面内容
    */
   public static String doPost(String url,Map<String,String> params,String charset,Header[] headers){
	   LOG.info("HttpUtil doPost url====="+url);
	   	if(params!=null){
	   		LOG.info("HttpUtil doPost params====="+JSONObject.toJSON(params));
	   	}
       if(StringUtils.isBlank(url)){
           return null;
       }
       try {
           List<NameValuePair> pairs = null;
           if(params != null && !params.isEmpty()){
               pairs = new ArrayList<NameValuePair>(params.size());
               for(Map.Entry<String,String> entry : params.entrySet()){
                   String value = entry.getValue();
                   if(value != null){
                       pairs.add(new BasicNameValuePair(entry.getKey(),value));
                   }
               }
           }
           HttpPost httpPost = new HttpPost(url);
           httpPost.setHeaders(headers);
           if(pairs != null && pairs.size() > 0){
               httpPost.setEntity(new UrlEncodedFormEntity(pairs,CHARSET));
           }
           CloseableHttpResponse response = httpClient.execute(httpPost);
           int statusCode = response.getStatusLine().getStatusCode();
           if (statusCode != 200) {
               httpPost.abort();
               throw new RuntimeException("HttpClient,error status code :" + statusCode);
           }
           HttpEntity entity = response.getEntity();
           String result = null;
           if (entity != null){
               result = EntityUtils.toString(entity, CHARSET);
               LOG.info("HttpUtil result===="+result);
           }
           EntityUtils.consume(entity);
           response.close();
           return result;
       } catch (Exception e) {
       	LOG.error("doPost error ",e);
       }
       return null;
   }  
   
   public static void main(String args[]){
	  /* String url="http://v.polyv.net/uc/services/rest?method=uploadfile";
	   Map params=new HashMap();
	   params.put("writetoken", "6UFzrYDguluXrmq-hp11369fFYAUcZGY");
	   params.put("writetoken", "{\"title\": \"这里是标题\", \"tag\": \"标签\", \"desc\": \"视频文档描述\"}");
	   params.put("Filedata", new File("D:\\井柏然 随性释放自我.f4v"));
	   String json=doPost(url, params);
	   System.out.println(json);*/
	  /* String charset="utf-8";
		String url="http://192.168.0.71:8080/myw/mdmy/userApi/v1/SaveVideoInfo/token";
		Map<String,String> map=new HashMap<String, String>();
		map.put("videoCreator", "ad066d037da945e28da24aa43210eb82");
		map.put("videoName", "polyvsdk");
		map.put("videoDesc", "polyvsdk upload demo video");
		map.put("videoImgUrl", "http://img.videocc.net/uimage/2/2d995e2af4/a/2d995e2af4407ef1cee6d0c5dcba98fa_0.jpg");
		map.put("videoDuration", "00:00:03");
		map.put("polyVid", "2d995e2af4407ef1cee6d0c5dcba98fa_2");
		
		String res=doPost(url, map, charset);
		System.out.println(res);*/
	   /*String url="http://222.73.117.158/msg/HttpBatchSendSM";
	   Map<String, String> params=new HashMap<String, String>();
	   params.put("account", "jiekou-clcs-02");
	   params.put("pswd", "Tch123456");
	   params.put("mobile", "13632520197,18923768865,18638663577");
	   params.put("msg", "【米芽网】您好，您的验证码短信为123456，请在10分钟内完成注册！");
	   params.put("needstatus", "true");
	   params.put("product", "201356329");
	  String res = doPost(url, params);
	  System.out.println(res);*/
	   //testWX();
	   postToBaidu();
   }
   
   public static void testRmsg(){
	   String url="http://localhost:8080/myw/mdmy/wechat/reviceMsg";
	   Map<String,String> map=new HashMap<String,String>();
	   map.put("signature", "dsfdsaf");
	   map.put("timestamp", "11111222333322");
	   map.put("nonce", "3423443");
	   map.put("echostr", "esdg");
	   String res=doPost(url,map);
	   System.out.println(res);
   }
   
   public static void testDx(){
	   String url="http://222.73.117.158/msg/HttpBatchSendSM";
	   Map<String, String> params=new HashMap<String, String>();
	   params.put("account", "mikuzhihui");
	   params.put("pswd", "Tch123456");
	   params.put("mobile", "13632520197");
	   params.put("msg", "您好，您的验证码短信为:123456,请在10分钟内完成注册！");
	   params.put("needstatus", "true");
	  String res = doPost(url, params);
	  System.out.println(res);
   }
   public static void testWX(){
	   String url="http://wx.jeezsoft.com.cn/index.php?g=Home&m=Weixin&a=index&token=983xeTwmSb6CkSns33Mg";
	   String Token="983xeTwmSb6CkSns33Mg";
	   String EncodingAESKey="iqlOBZgcQtUyPNaiIawKhyGiwZDjoUMRTceKYOfUNXL";
	   Map<String,String> map=new HashMap<String,String>();
	   map.put("signature", "");
	   map.put("timestamp", "11111222333322");
	   map.put("nonce", "3423443");
	   map.put("echostr", "esdg");
	   String res=doGet(url,map);
	   System.out.println(res);
   }
   
   public static void postToBaidu(){
	   String url="http://data.zz.baidu.com/urls?site=www.qumiya.com/mdmy/interest/vinterest_0_0_1.html&token=Ra2MEp9pjoduFk9J";
	   Map<String,String> map=new HashMap<String,String>();
	   //map.put("site", "http://www.qumiya.com/mdmy/interest/vinterest_0_0_1.html");
	  // map.put("urls", "www.qumiya.com/mdmy/interest/vinterest_0_0_1.html");
	   String res=doPost(url,map);
	   System.out.println(res);
   }
}