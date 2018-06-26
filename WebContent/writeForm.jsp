<%@page import="vo.ReplyInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#writeFormArea{
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
	ReplyInfo replyInfo = (ReplyInfo)request.getAttribute("replyInfo");
    int num = replyInfo.getNum();
    int ref = replyInfo.getRef();
    int re_step = replyInfo.getRe_step();
    int re_level = replyInfo.getRe_level();
%>
</head>
<body>
<section id = "writeFormArea">
	<h1>게시판 글쓰기</h1>
	<form action="boardWritePro.bo" method = "POST">
	<input type = "hidden" name = "num" value = "<%=num %>">
	<input type = "hidden" name = "ref" value = "<%=ref %>">
	<input type = "hidden" name = "re_step" value = "<%=re_step %>">
	<input type = "hidden" name = "re_level" value = "<%=re_level %>">
	<table>
	<tr>
		<td class = "td_left"><label for = "writer">이강호 바보 : </label></td>
		<td class = "td_right"><input type = "text" name = "writer" id = "writer" required="required"/></td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "subject">글제목 : </label></td>
		<td class = "td_right">
		<%if(request.getParameter("num") == null) {%>
		<input type = "text" name = "subject" id = "subject"/>
		<%
		}
		else{
		%>
		<input type = "text" name = "subject" id = "subject" 
		value = "[답변]"/>
		
		<%
		}
		%>
		</td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "email">이메일 : </label></td>
		<td class = "td_right"><input type = "email" name = "email" id = "email"/></td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "content">글내용 : </label></td>
		<td class = "td_right"><textarea rows = "13" cols = "30" name = "content" id = "content"></textarea></td>
	</tr>
	<tr>
		<td class = "td_left"><label for = "passwd">비밀번호 : </label></td>
		<td class = "td_right"><input type = "password" name = "passwd" id = "passwd"/></td>
	</tr>
	<tr>
		<td colspan = "2"><input type = "submit" value = "글등록"/>
		<input type = "reset" value = "다시작성"/></td>
	</tr>
	</table>
	</form>
</section>
</body>
</html>






