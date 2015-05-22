<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<title>mysite3</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/user.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/mysite3/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#submitBtn").click(function(){
			var email = $("#email").val();
			$.ajax({
				method: "POST",
				url: "/mysite3/member/checkemail",
				data: { "email": email},
				success: function(response){
					if(response.flag){
						$("#join-form").submit();
					}else{
						$("#o-img").hide();
						$("#x-img").show();
						alert("중복된 아이디가 있습니다!");
						return;
					}
				},
				error: function(){
					alert("error");
					return;
				}
			}) 
		});
		$("#email").change(function(){
			var email = $(this).val();
			$.ajax({
				method: "POST",
				url: "/mysite3/member/checkemail",
				data: { "email": email},
				success: function(response){
					if(response.flag){
						$("#o-img").show();
						$("#x-img").hide();
					}else{
						$("#o-img").hide();
						$("#x-img").show();
					}
				},
				error: function(){
					alert("error");
				}
			}) 
		});
		
	});
</script>
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
					<input id="name" name="name" type="text" required="required" autofocus="autofocus">

					<label class="block-label" for="email">이메일*</label>
					<input id="email" name="email" type="text" required="required">
					<div id="imgs">
						<img src="/mysite3/assets/images/o.gif" id="o-img">
						<img src="/mysite3/assets/images/x.png" id="x-img">
					</div>
					<label class="block-label">패스워드*</label>
					<input name="password" type="password" required="required">
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
					</fieldset>
					</div>
					
					<input id="submitBtn" type="button" value="가입하기">
					
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