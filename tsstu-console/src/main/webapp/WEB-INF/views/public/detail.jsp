<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="../public/header.jsp" %>
<style type="text/css">
	.form_table td.right {
		width: 130px;
	}
</style>
</head>
<body>
    <div class="formbody">
    	<form id="form1">
	    	<input type="hidden" id="id" name="id" value="${model.id }" >
		    <table class="form_table">
				<c:forEach items="${detailList }" var="detail" varStatus="status">
				<tr>
					<td style="font-weight: bold;">${detail.title }</td>
					<td class="value">
						<c:choose>
							<%-- <c:when test="${detail.data_type eq 1 }"><fmt:formatNumber value="${detail.value}" minFractionDigits="2"/></c:when> --%>
							<c:when test="${detail.title eq '运营中心' && detail.value eq 0}">无</c:when>
							<c:when test="${detail.title eq '所属会员' && detail.value eq 0}">无</c:when>
							<c:when test="${detail.title eq '代理会员' && detail.value eq 0}">无</c:when>
							<c:when test="${detail.title eq '所属经纪人' && detail.value eq 0}">无</c:when>
							<c:when test="${detail.data_type eq 2 }"><fmt:formatDate value="${detail.value }" pattern="yyyy-MM-dd HH:mm:ss"/></c:when>
							<c:when test="${detail.data_type eq 3 }">
								<c:if test="${empty detail.value }">没有图片</c:if>
								<c:if test="${not empty detail.value }"><img style="width: 100px;height: 100px;" src="${detail.value }"/></c:if>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${not empty detail.dict_code }">
										<c:forEach items="${detail.items }" var="item" varStatus="status">
											<c:if test="${detail.value eq item.item_value}">${item.item_name }</c:if>
										</c:forEach>
									</c:when>
									<c:otherwise>${detail.value }</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>		
					</td>						
				</tr>
				</c:forEach>
				<tr>
					<td class="center" colspan="4">
			        	<a class="button button-action button-rounded button-small" onclick="parentDialog.close()">关&nbsp;闭</a>
					</td>
				</tr>
			</table>
		</form>
    </div>
</body>
</html>

