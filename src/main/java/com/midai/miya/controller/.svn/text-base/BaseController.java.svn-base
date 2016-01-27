package com.midai.miya.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.midai.miya.sys.model.Log;
import com.midai.miya.sys.model.Operator;
import com.midai.miya.sys.service.LogService;
import com.midai.miya.utils.Message;
import com.midai.miya.utils.PageUtil;
import com.midai.miya.utils.Random;
import com.midai.miya.utils.SendMsgUtil;
import com.midai.miya.utils.SessionUtil;
import com.midai.miya.utils.UUIDUtil;
import com.midai.miya.utils.mail.MailPoolExecutor;
import com.midai.miya.utils.mail.MailSenderInfo;

@SuppressWarnings("deprecation")
@Controller
@RequestMapping("/baseController")
public class BaseController implements Serializable{
	@Autowired
	private LogService logService;
	public static final Logger logger = Logger.getLogger(BaseController.class);
	public static ServletContext _servletContext;
	private static WebApplicationContext springContext;
	private static Object _instance;
	/**
	 * 
	 */
	private static final long serialVersionUID = -5833176973718872824L;
	/**
	 * 取得当前操作员
	 * @return
	 */
	public Operator getCurrentOperator(HttpServletRequest request){
		if(request.getSession()!=null){
			Operator operator = (Operator) request.getSession().getAttribute("_loginOperator");
			return operator;
		}
		return null;
	}
	
	/**
	 *  查询分页工具
	 *  黄扬仲
	 *  2015年4月30日
	 */
	public PageUtil getPage(HttpServletRequest request){
		PageUtil pageUtil = new PageUtil();
		String rowsStr=request.getParameter("rows")==null?"0":request.getParameter("rows");
		String pageStr=request.getParameter("page")==null?"0":request.getParameter("page");
		int rows=Integer.valueOf(rowsStr);
		int page=(Integer.valueOf(pageStr)-1)*rows;
		String order=request.getParameter("order");
		String sort=request.getParameter("sort");
		pageUtil.setOrder(order);
		pageUtil.setPage(page);
		pageUtil.setRows(rows);
		pageUtil.setSort(sort);
		return pageUtil;
	}
	
	
	
	/**
	 *  导出报表分页工具
	 *  黄扬仲
	 *  2015年4月30日
	 */
	public PageUtil getExportPage(HttpServletRequest request){
		PageUtil pageUtil = this.getPage(request);
		pageUtil.setPage(0);
		pageUtil.setRows(Integer.MAX_VALUE);
		return pageUtil;
	}
	
	/**
	 *  保存操作日志
	 *  黄扬仲
	 *  2015年4月30日
	 */
	public void addLog(HttpServletRequest request,String logContent,int logType){
		Operator operator = this.getCurrentOperator(request);
		if(operator!=null){
			Log log = new Log();
			log.setLogId(UUIDUtil.getUUID());
			log.setLogCreateTime(new Date());
			log.setLogCreator(operator.getOperatorId());
			log.setLogContent(logContent);
			log.setLogType(logType);
			logService.addLog(log);
		}
	}
	
	/**
	 *  导出报表
	 *  黄扬仲
	 *  2015年4月30日
	 */
	protected void doExport(HttpServletRequest request,
			HttpServletResponse response, List<?> dataList, String titleName,String excelName,String[] heads,String[] columns) {
		OutputStream os = null;
		HSSFWorkbook hssfWorkbook;
		try {
			String explorerType = request.getHeader("User-Agent");
			   if(null != explorerType && StringUtils.contains(explorerType, "MSIE")){//IE浏览器
				   excelName = URLEncoder.encode(excelName,"UTF8");
	             }else if( null != explorerType && StringUtils.contains(explorerType, "Mozilla")){//google,火狐浏览器
	            	 excelName = new String(excelName.getBytes(), "ISO8859-1");
	             }else{
	            	 excelName = URLEncoder.encode(excelName,"UTF8");//其他浏览器
	             }
			hssfWorkbook = this.decorateExcel(heads,columns,dataList, titleName);
			response.addHeader("Content-Disposition","attachment;filename="+excelName+".xls");
			
			if(explorerType!=null&&!"".equals(explorerType)&& explorerType.indexOf("MSIE") > 0){
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "public");
			}
			os = response.getOutputStream();
			hssfWorkbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != os) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 *  生成list为VO的数据， 有标题 通用格式excel
	 *  黄扬仲
	 *  2015年4月30日
	 */
	protected  HSSFWorkbook decorateExcel(String[] head, String[] column,
	List<?> data, String title) throws Exception {
	HSSFWorkbook workbook = new HSSFWorkbook(); 
	if(data == null || data.size()==0) {
	workbook.createSheet();
	return workbook;
	}
	HSSFFont headfont = workbook.createFont();
	headfont.setFontName("黑体");
	headfont.setFontHeightInPoints((short) 22);// 字体大小
	headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗

	HSSFCellStyle headstyle = workbook.createCellStyle();
	headstyle.setFont(headfont);
	headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
	headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中
	headstyle.setLocked(true);
	headstyle.setWrapText(true);// 自动换行

	HSSFFont columnHeadFont3 = workbook.createFont();
	columnHeadFont3.setFontName("黑体");
	columnHeadFont3.setFontHeightInPoints((short) 10);
	columnHeadFont3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

	HSSFCellStyle headstyle3 = workbook.createCellStyle();
	headstyle3.setFont(columnHeadFont3);
	headstyle3.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
	headstyle3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

	HSSFSheet sheet = workbook.createSheet(); // 创建工作薄
	// 创建第一行 索引从0开始
	HSSFRow row0 = sheet.createRow(0);
	row0.setHeight((short) 900);
	// 合并行 四个参数分别为：开始行 开始列 结束行 结束列
	sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, column.length-1));
	// 在row0行里创建第一列cell0
	HSSFCell cell1 = row0.createCell(0);
	// 为第一列设置样式
	cell1.setCellStyle(headstyle); 
	// 为第一列赋值
	cell1.setCellValue(new HSSFRichTextString(title));

	HSSFRow row = null; // 行计录
	HSSFCell cell = null; // 单元格
	int rowIndex =1;
	// 增加头文件信息
	if (head != null) {
	row = sheet.createRow(rowIndex);
	for (int i = 0; i < head.length; i++) {
	// 设置指定列宽 第一个参数表示 第几列 第二个参数表示 列宽
	sheet.setColumnWidth(i, 4000);
	cell = row.createCell(i);
	cell.setCellType(Cell.CELL_TYPE_STRING);
	// 为第一列设置样式
	cell.setCellStyle(headstyle3);
	cell.setCellValue(head[i]);
	}
	rowIndex++;
	}
	// 写入内容
	try {
	for (int i = 0,iSize= data.size(); i <iSize; i++) {
	row = sheet.createRow(rowIndex++);
	Object obj = data.get(i);
	for (int j = 0; j < column.length; j++) {
	HSSFCell cellj = row.createCell(j);
	cellj.setCellType(Cell.CELL_TYPE_STRING);
	cellj.setCellValue(BeanUtils.getProperty(obj,  column[j]));
	}
	}
	} catch (Exception e) {
	throw e;
	}
	return workbook;
	}
	
	
	/**
	 *  发送短信
	 *  黄扬仲
	 *  2015年9月3日
	 */
	public boolean sendMsg(HttpServletRequest request,String phone,String msgContent) {
		try{
			Message message=getMessageInstance();
			message.setMobile(phone);
			message.setMsgContent(msgContent);
			//发送短信
			SendMsgUtil.sendMsg(message);
			return true;
		}catch(Exception e){
			logger.error("sendMsg 发送短信失败=====",e);
			return false;
		}
			
	}
	
	
	/**
	 * 根据名称实例化对象 黄扬仲 2015年6月20日
	 */
	public static Object getInstance(String beanName) {
		springContext = WebApplicationContextUtils
				.getWebApplicationContext(_servletContext);
		_instance = (Object) springContext.getBean(beanName);
		return _instance;
	}

	/**
	 * 根据key值取得系统配置的value 黄扬仲 2015年6月20日
	 */
	@SuppressWarnings("unchecked")
	public static String getSysConfig(String key) {
		Map<String, String> map = (Map<String, String>) _servletContext
				.getAttribute("sysMap");
		return map.get(key);
	}
	
	/**
	 *  发送短信验证码
	 *  黄扬仲
	 *  2015年9月2日
	 */
	public String sendVerifyCode(HttpServletRequest request,String phone) {
		try{
			String verifyCode=Random.genVerifyCode(4);
			boolean limitVerifyCode=SessionUtil.limitVerifyCode(request.getSession(), phone);
			if(limitVerifyCode){
				Message message=getMessageInstance();
				message.setMobile(phone);
				String msg="您好,您的短信验证码为："+verifyCode+",有效时间为90秒,请及时验证,谢谢";
				message.setMsgContent(msg);
				//将验证码设置到session
				SessionUtil.setVerifyCode(request.getSession(), verifyCode, phone);
				//发送短信
				try{
					SendMsgUtil.sendMsg(message);
				}catch(Exception e){
					
				}
				
				return "发送成功";
			}else{
				return "请不要频繁发送短信验证码";
			}
		}catch(Exception e){
			logger.error("sendVerifyCode 发送短信失败=====",e);
			return "发送失败";
		}
	
	}
	
	/**
	 *  发送邮件
	 *  黄扬仲
	 *  2015年10月21日
	 *  @param title 邮件标题
	 *  @param content 需要发送的内容
	 *  @param toMail 接收邮件的邮箱
	 */
	public void sendMail(String title,String content,String toMail){
		MailSenderInfo mailInfo = new MailSenderInfo();
		String userName = BaseController.getSysConfig("mailUserName");
		String mailServerHost = BaseController.getSysConfig("mailServerHost");
		String mailServerPort = BaseController.getSysConfig("mailServerPort");
		String userPassword = BaseController.getSysConfig("mailPassword");
		String FromAddress = BaseController.getSysConfig("fromAddress");
		
		mailInfo.setMailServerHost(mailServerHost);
		mailInfo.setMailServerPort(mailServerPort);
		mailInfo.setValidate(true);
		mailInfo.setUserName(userName);
		mailInfo.setPassword(userPassword);
		mailInfo.setFromAddress(FromAddress);
		mailInfo.setToAddress(toMail);
		mailInfo.setSubject(title);
		mailInfo.setContent(content);
		MailPoolExecutor exceutor=new MailPoolExecutor();
		exceutor.putThreadPooLExecute(mailInfo);
	}
	
	
	
	/**
	 *  获取Message实例对象
	 *  黄扬仲
	 *  2015年9月3日
	 */
	public Message getMessageInstance(){
		Message message=new Message();
		message.setUrl(getSysConfig("msg_url"));
		message.setAccount(getSysConfig("msg_account"));
		message.setPswd(getSysConfig("msg_pswd"));
		return message;
	}
}
