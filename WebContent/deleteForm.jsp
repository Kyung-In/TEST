<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#deleteFormArea{
		width : 300px;
		height : 200px;
		border : 1px solid gold;
	}
</style>
</head>
<body>
	<section id = "deleteFormArea">
		<form action="deletePro.bo" method = "POST">
		    <input type = "hidden" name = "num" value = "${num }"/>
		    <input type = "hidden" name = "pageNum" value = "${pageNum }"/>
			<label for = "passwd">비밀번호 : </label>
			<input type = "password" name = "passwd" id = "passwd"><br>
			<input type = "submit" value = "글삭제">
		</form>
	</section>
</body>
</html>






