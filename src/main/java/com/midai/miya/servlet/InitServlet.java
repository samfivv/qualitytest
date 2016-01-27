package com.midai.miya.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.midai.miya.constant.Constant;
import com.midai.miya.controller.BaseController;
import com.midai.miya.sys.model.SysConfig;
import com.midai.miya.sys.service.SysConfigService;
import com.midai.miya.utils.PageUtil;

public class InitServlet extends HttpServlet {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -165010712166977098L;
	public static final Logger logger = Logger.getLogger(InitServlet.class);


	@Override
	public void init() throws ServletException {
		super.init();
		BaseController._servletContext=this.getServletContext();
		SysConfigService configService=(SysConfigService) BaseController.getInstance("sysConfigServiceImpl");
		PageUtil page=new PageUtil();
		page.setRows(500);
		List<SysConfig> sysConfigs=configService.find(new SysConfig(),page);
		Map<String,String> sysMap=new HashMap<String,String>();
		for(SysConfig config:sysConfigs){
			sysMap.put(config.getSysConfigKey(), config.getSysConfigValue());
		}
		BaseController._servletContext.setAttribute("sysMap", sysMap);
		String uploadImgServerAddr=sysMap.get("upload_img_server_addr");
		if(uploadImgServerAddr!=null&&!"".equals(uploadImgServerAddr)){
			Constant.UPLOAD_IMG_SERVER_ADDR=uploadImgServerAddr;
			logger.info("图片上传地址设置成功");
		}else{
			logger.info("图片上传地址未配置");
		}
		String getImgServerAddr=sysMap.get("get_img_server_addr");
		if(getImgServerAddr!=null&&!"".equals(getImgServerAddr)){
			Constant.GET_IMG_SERVER_ADDR=getImgServerAddr;
			logger.info("获取图片地址设置成功");
		}else{
			logger.info("获取图片地址未配置");
		}
		logger.info("=====================================初始化系统配置成功===============================");
	}

}
