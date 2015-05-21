<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<title>mysite3</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		</div>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="/mysite3/guestbook/delete">
					<input type='hidden' name="no" value="${vo.no }">
					<div id="deleteform">
						<label>비밀번호</label>
						<input type="password" name="password">
						<input  TYPE="IMAGE" src="/mysite3/assets/images/check.png" name="Submit" value="Submit" >
						<span style="color: blue">${msg }</span>
					</div>
				</form>
				<a href="/mysite3/guestbook/index">방명록 리스트로</a>
			</div>
		</div>
		<div id="navigation">
			<c:import url="/WEB-INF/views/include/navigation.jsp">
				<c:param name="type" value="guestbook"></c:param>
			</c:import>
		</div>
		<div id="footer">
			<c:import url="/WEB-INF/views/include/footer.jsp"/>
		</div>
	</div>
</body>
</html>