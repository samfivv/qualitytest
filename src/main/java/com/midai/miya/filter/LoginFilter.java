package com.midai.miya.filter;
import java.io.IOException;
 

import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.midai.miya.sys.model.Operator;
 
 
/**
 * 用于检查用户是否登录了系统的过滤器<br>
 * 创建日期：2012-01-09
 * @author <a href="mailto:hemingwang0902@126.com">何明旺</a>
 */
public class LoginFilter implements Filter {
 
 
    @Override
    public void init(FilterConfig cfg) throws ServletException {
    	
    }
 
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String servletPath = request.getRequestURI();
        String contextPath =request.getContextPath();
        Operator operator=(Operator) request.getSession().getAttribute("_loginOperator");
        // 如果Session为空，则跳转到指定页面
        if (operator == null) {
        	 if(examineUrl(servletPath)){
             	 chain.doFilter(req, res);
             	 return;
             }
        	 if(request.getHeader("x-requested-with")!=null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){ 
						PrintWriter printWriter = response.getWriter(); 
						printWriter.print("{sessionState:0,url:"+request.getContextPath()+"}"); 
						printWriter.flush(); 
						printWriter.close(); 
						return;
					}else{
						response.sendRedirect(contextPath +"/login.jsp");
						return;
					}
           
        } else {
            chain.doFilter(req, res);
        }
    }
    /**
     *  校验是否放行
     *  黄扬仲
     *  2015年5月25日
     */
	 public boolean examineUrl(String servletPath){
		 boolean pass=false;
		 for(String url:PropertieUtil.unCheckUrlList){
			 if(servletPath.indexOf(url)!=-1){
				 pass=true;
				 break;
			 }
		 }
		 return pass;
	 }
	 
	@Override
	public void destroy() {
		
	}
}