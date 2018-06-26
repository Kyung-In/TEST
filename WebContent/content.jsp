<%@page import="vo.Board"%>
<%@page import="dao.BoardDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#contentArea{
		width : 600px;
		height : 450px;
		margin : auto;
		text-align : center;
		border : 2px solid gold;
	}
	h1{
		text-align : center;
	}
	#basicContent{
		height : 70px;
		border-bottom : 3px double orange;
		text-align: center;
	}
	#detailContent{
		background: green;
		color: white;
		overflow: auto;
	}
	#commandArea{
		width : 600px;
		margin : auto;
	    margin-top : 10px;
		text-align : center;
	}
</style>
</head>
<body>
	<section id = "contentArea">
		<h1>글내용</h1>
		<section id = "basicContent">
			작성자 : ${article.writer } | 글제목 : ${article.subject }
			작성일 : <fmt:formatDate var = "regDate" value="${article.reg_date }" pattern = "yyyy.MM.dd"/>
					<c:out value="${regDate }"></c:out> 
			| 조회수 : ${article.readcount }
		</section>
		<section id = "detailContent">
			<pre>${article.content}</pre>
		</section>
	</section>
	<section id = "commandArea">
		<a href = "modifyForm.bo?num=${article.num}&pageNum=${pageNum}">글수정</a>
		<a href = "removeForm.bo?num=${article.num}&pageNum=${pageNum}">글삭제</a>
		<a href = "writeForm.jsp?num=${article.num}&ref=${article.ref}&re_step=${article.re_step}&re_level=${article.re_level}">답글쓰기</a>
		<a href = "list.jsp?pageNum=${pageNum}">목록보기</a>
	</section>
</body>
</html>










