<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="/mysite3/assets/css/main.css" rel="stylesheet" type="text/css">
<ul id="navigation">
		<c:choose>
		<c:when test="${param.type == 'guestbook' }">
			<li>${sessionScope.authMember.name }</li>
			<li class="selected"><a href="/mysite3/guestbook/list">방명록</a></li>
			<li><a href="/mysite3/board?a=boardlist">게시판</a></li>		
		</c:when>
		<c:when test="${param.type == 'board' }">
			<li>${sessionScope.authMember.name }</li>
			<li><a href="/mysite3/guestbook/list">방명록</a></li>
			<li class="selected"><a href="/mysite3/board?a=boardlist">게시판</a></li>		
		</c:when>
		<c:otherwise>
			<li class="selected">${sessionScope.authMember.name }</li>
			<li><a href="/mysite3/guestbook/list">방명록</a></li>
			<li><a href="/mysite3/board?a=boardlist">게시판</a></li>				
		</c:otherwise>
	</c:choose>
</ul>