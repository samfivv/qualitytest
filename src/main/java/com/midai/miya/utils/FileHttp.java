package com.midai.miya.utils;

import java.nio.charset.Charset;
import java.io.*;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;

@SuppressWarnings("deprecation")
public class FileHttp {
	public static final Logger log = Logger.getLogger(FileHttp.class);
	/**
	 * @param fileName 
	 *            图片路径
	 */
	public static boolean uploadFileWithHttpMime(String headportraitDir, String headportraitName,
			MultipartFile multipartFile) {
		boolean uploadState=true;
		// 定义请求url
		// 实例化http客户端
		HttpClient httpClient = new DefaultHttpClient();
		// 实例化post提交方式
		HttpPost post = new HttpPost(Constant.UPLOAD_IMG_SERVER_ADDR);
		// 添加json参数
		try {
			// 实例化参数对象
			MultipartEntity params = new MultipartEntity();
			// 图片文本参数
			params.addPart("headportraitDir",new StringBody(headportraitDir,Charset.forName("UTF-8")));
			params.addPart("headportraitName",new StringBody(headportraitName,Charset.forName("UTF-8")));
			// 设置上传文件
			
			CommonsMultipartFile cf= (CommonsMultipartFile)multipartFile;
	        DiskFileItem fi = (DiskFileItem)cf.getFileItem();

	        File file = fi.getStoreLocation(); 
			// 文件参数内容
			FileBody fileBody = new FileBody(file);
			// 添加文件参数
			params.addPart("uploadFile", fileBody);
			// 将参数加入post请求体中
			post.setEntity(params);
			// 执行post请求并得到返回对象 [ 到这一步我们的请求就开始了 ]
			HttpResponse resp = httpClient.execute(post);
			// 解析返回请求结果
			HttpEntity entity = resp.getEntity();
			InputStream is = entity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuffer buffer = new StringBuffer();
			String temp;
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
			}
		} catch (UnsupportedEncodingException e) {
			log.error(e);
			uploadState=false;
		} catch (ClientProtocolException e) {
			log.error(e);
			uploadState=false;
		} catch (IOException e) {
			log.error(e);
			uploadState=false;
		} catch (IllegalStateException e) {
			log.error(e);
			uploadState=false;
		}
		return uploadState;
	}
	
}
