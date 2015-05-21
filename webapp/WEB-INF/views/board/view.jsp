<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/mysite3/assets/css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script type="text/javascript" src="/mysite3/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(function(){
		$("#listBtn").click(function(){
			location.href="/mysite3/board/list";
		})
	});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/WEB-INF/views/include/header.jsp"/>
		</div>
		<div id="content">
			<table id="insertTable">
				<tr>
					<th>title</th>
					<td colspan="23" id="titleTd"><input type="text" id="title" name="title" value="${board.title }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>name</th>
					<td id="nameTd"><input type="text" id="name" value="${board.memberName }" readonly="readonly"></td>
					<th>hit</th>
					<td id="viewCntTd"><input type="text" id="viewCnt" name="viewCnt" value="${board.viewCnt }" readonly="readonly"></td>
				</tr>
				<tr>
					<td colspan="4" id=textareaTd>
						<textarea id="textarea" name="content" readonly="readonly">${board.content}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" id="submitTd">
						<input type="button" id="listBtn" value="목록으로" >
					</td>
				</tr>
			</table>
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