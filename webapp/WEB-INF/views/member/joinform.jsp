<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<title>mysite3</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		</div>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="post" action="/mysite3/member/join">
					<div id="formDiv">
					<label class="block-label" for="name">이름*</label>
					<input id="name" name="name" type="text" required="required">

					<label class="block-label" for="email">이메일*</label>
					<input id="email" name="email" type="text" required="required">
					
					<label class="block-label">패스워드*</label>
					<input name="password" type="password" required="required">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					</div>
					
					<input type="submit" value="가입하기">
					
				</form>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp"/>
		</div>
	</div>
</body>
</html>