package com.tsstu.console.core.tag;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.tsstu.common.model.Model;
import com.tsstu.console.core.page.Pager;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 分页标签类
 * @author 孔师平
 *
 */
public class PageTabSupport extends SimpleTagSupport {
	
	 private String form;
	
	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	/**
	 * freemarker的模板
	 */
	private static Template template;
	
	@Override
	@SuppressWarnings("unchecked")
	public void doTag() throws JspException, IOException {
		Pager<Model> pageInfo = (Pager<Model>) getJspContext().getAttribute("pageInfo", PageContext.REQUEST_SCOPE);
		if (null == pageInfo) {
			return;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageInfo", pageInfo);
		map.put("formName", form);
		
		if (pageInfo.getTotalPage() >0) {
			int first = pageInfo.getPageNum() - 4;
			if (first < 1) {
				first = 1;
			}
			int last = first + 9;
			if (last > pageInfo.getTotalPage()) {
				last = Integer.valueOf(pageInfo.getTotalPage() + "");
			}
			
			first = last - 9;
			if (first < 1) {
				first = 1;
			}
			map.put("first", first);
			map.put("last", last);
		} else {
			map.put("first", 0);
			map.put("last", 0);
		}
		
		
		
		try {
			JspWriter out = getJspContext().getOut();
			Template temp = getTemplate();
			temp.process(map, out);
		} catch (TemplateException e) {
			throw new JspException(e);
		}
	}
	
	/**
	 * 获取freemarker模板
	 * @return
	 * @throws IOException
	 */
	private static Template getTemplate() throws IOException {
		if (null == template) {
			synchronized (PageTabSupport.class) {
				if (null == template) {
					createTemplate();
				}
			}
		}
		return template;
	}
	
	/**
	 * 创建framemaker的模板
	 * @throws IOException
	 */
	private static void createTemplate() throws IOException {
		Configuration configurable = new Configuration();
		InputStream in = PageTabSupport.class.getResourceAsStream("/template/pager.template");
		InputStreamReader reader = new InputStreamReader(in, "UTF-8");
		template = new Template("", reader, configurable);
	}
	
}
