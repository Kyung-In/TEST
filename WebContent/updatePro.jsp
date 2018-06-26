<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="article" class = "vo.Board"></jsp:useBean>
<jsp:setProperty property="*" name="article"/>

<%
	String pageNum = request.getParameter("pageNum");
	BoardDAO boardDAO = BoardDAO.getInstance();
	int updateCheck = boardDAO.updateArticle(article);
	
	if(updateCheck == 1){
		response.sendRedirect("list.jsp?pageNum=" + pageNum);
	}
	else{
%>
	<script>
		alert("수정실패");
		history.back();
	</script>

<%
	}
%>







