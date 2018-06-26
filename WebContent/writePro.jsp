<%@page import="dao.BoardDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean id="article" class = "vo.Board"></jsp:useBean>
<jsp:setProperty property="*" name="article"/>
<%
		
%>