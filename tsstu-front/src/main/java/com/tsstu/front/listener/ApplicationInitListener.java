
package com.tsstu.front.listener;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;


/**
 * 初始化系统初始化数据
 * @author liwei
 * @date 2017年4月20日16:56:09
 */
@Component
public class ApplicationInitListener implements ServletContextAware {

	private static Logger logger = LoggerFactory.getLogger(ApplicationInitListener.class);
    
	private ServletContext servletContext;
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@PostConstruct 
	public void init() throws IOException {
		//this.initConfig();
		this.servletContext.setAttribute("theme", "default");
	}
	
}