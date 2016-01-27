package com.midai.miya.constant;

public class Constant {
	/**
	 * 登录
	 */
	public static int LOG_TYPE_LOGIN=1;
	/**
	 * 注销
	 */
	public static int LOG_TYPE_LOGOUT=2;
	/**
	 * 新增
	 */
	public static int LOG_TYPE_ADD=3;
	/**
	 * 删除
	 */
	public static int LOG_TYPE_DELETE=4;
	/**
	 * 修改
	 */
	public static int LOG_TYPE_UPDATE=5;
	/**
	 * 查询
	 */
	public static int LOG_TYPE_SELECT=6;
	/**
	 * 导出
	 */
	public static int LOG_TYPE_EXPORT=6;
	
	
	/**
	 * 短信发送时间
	 */
	public static final String VERIFY_CODE_SEND_TIME = "VERIFY_CODE_SEND_TIME";
	/**短信验证码**/
	public static final String VERIFY_CODE = "VERIFY_CODE";
	/**图片验证码**/
	public static final String VERIFY_CODE_IMAGE = "VERIFY_CODE_IMAGE";
	
	/** 图片服务器上传默认地址 如果数据库没有配置则使用这个地址**/
	public static String UPLOAD_IMG_SERVER_ADDR="http://192.168.0.233:8080/img/mdmy/upload/uploadHeadportrait";
	/** 删除图片服务器地址**/
	public static String DEL_IMG_SERVER_ADDR="/mdmy/DeleteFile/deleteFile";
	/** 获取图片默认地址  如果数据库没有配置则使用这个地址**/
	public static String GET_IMG_SERVER_ADDR="http://192.168.0.233:8080/img/";
}
