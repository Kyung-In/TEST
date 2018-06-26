<%@page import="vo.Board"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#updateFormArea{
		width : 600px;
		height : 450px;
		margin : auto;
		text-align : center;
		border : 2px solid gold;
	}
	h1{
		text-align : center;
	}
	table{
		width : 600px;
		margin : auto;
		text-align : center;
	}
	.td_left{
		width : 200px;
		background: gold;
		
	}
	.td_right{
		width : 400px;
		background: red;
	}
</style>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	
	BoardDAO boardDAO = BoardDAO.getInstance();
	Board article = boardDAO.selectUpdateArticle(num);
%>
</head>
<body>
<section id = "updateFormArea">
	<h1>게시판 글쓰기</h1>
	<form action="boardUpdatePro.bo" method = "POST">
	<input type = "hidden" name = "num" value = "<%=num %>">
	<input type = "hidden" name = "pageNum" value = "<%=pageNum %>">
	<input type = "hidden" name = "writer" value = "<%=article.getWriter() %>">
	<table>
	<tr>
		<td class = "td_left"><label for = "writer">이상규 바보 : </label></td>
		<td class = "td_right"><%=article.getWriter() %></td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "subject">글제목 : </label></td>
		<td class = "td_right">
		<input type = "text" name = "subject" id = "subject"
		value = "<%=article.getSubject()%>"/>
		</td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "email">이메일 : </label></td>
		<td class = "td_right"><input type = "email" name = "email" id = "email"
		value = "<%=article.getEmail()%>"/></td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "content">글내용 : </label></td>
		<td class = "td_right"><textarea rows = "13" cols = "30" name = "content" id = "content"><%=article.getContent() %></textarea></td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "passwd">비밀번호 : </label></td>
		<td class = "td_right"><input type = "password" name = "passwd" id = "passwd"/></td>
	</tr>
	<tr>
		<td colspan = "2"><input type = "submit" value = "글수정"/>
		<input type = "reset" value = "다시작성"/></td>
	</tr>
	</table>
	</form>
</section>
</body>
</html>






