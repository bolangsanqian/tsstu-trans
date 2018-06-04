package com.tsstu.console.core.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.tsstu.common.constants.Constants.UserConstants;
import com.tsstu.console.model.User;

public class HasPermissionTabSupport extends SimpleTagSupport {

	private String sign;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspContext context = getJspContext();
		User user = (User) context.getAttribute(UserConstants.LOGIN_USER_INFO, PageContext.SESSION_SCOPE);
		if (user != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> userPermissionMap = (Map<String, Object>) context.getAttribute(UserConstants.LOGIN_USER_PERMISSION, PageContext.SESSION_SCOPE);
			//System.out.println(JSON.toJSONString(userPermissionMap));
			if (!userPermissionMap.containsKey(sign)) {
				return;
			}
		}
		JspWriter out = context.getOut();
		getJspBody().invoke(out);
	}
	
}
