<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));
	String pageNum = request.getParameter("pageNum");
	String passwd = request.getParameter("passwd");
	
	BoardDAO boardDAO = BoardDAO.getInstance();
	int deleteCheck = boardDAO.deleteArticle(num,passwd);
	
	if(deleteCheck == 1){
		response.sendRedirect("list.jsp?pageNum=" + pageNum);
	}
	else{
	
%>
	<script>
		alert("삭제실패");
		history.back();
	</script>
<%
	}
%>






