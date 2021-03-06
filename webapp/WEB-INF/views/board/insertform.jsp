<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite3/assets/css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script type="text/javascript" src="/mysite3/jquery/jquery-1.9.0.js"></script>

</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		</div>
		<div id="content">
			<form action="/mysite3/board/insert" method="post">
				<table id="insertTable">
					<tr>
						<th>title</th>
						<td colspan="3" id="titleTd"><input type="text" id="title" name="title"></td>
					</tr>
					<tr>
						<th>name</th>
						<td id="nameTd"><input type="text" id="name" value="${sessionScope.authMember.name }" readonly="readonly"></td>
						<th>password</th>
						<td id="passwordTd"><input type="password" id="password" name="password" placeholder="로그인 시 비밀번호를 써주세요."></td>
					</tr>
					<tr>
						<td colspan="4" id=textareaTd>
							<textarea id="textarea" name="content">${param.content }</textarea>
						</td>
					</tr>
					<tr>
						<td colspan="4" id="submitTd">
							<input type="submit" id="submitBtn" value="확인" >
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation.jsp">
				<c:param name="type" value="board"></c:param>
			</c:import>
		</div>
		<div id="footer">
				<c:import url="/WEB-INF/views/include/footer.jsp"/>
		</div>
	</div>


</body>
</html>