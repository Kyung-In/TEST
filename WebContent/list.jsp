<%@page import="vo.PageInfo"%>
<%@page import="dao.BoardDAO"%>
<%@page import="vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	
    //한 페이지당 출력될 글의 개수
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#articleListArea{
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
		width : 560px;
		margin : auto;
		text-align : center;
	}
	.tr_title{
	background:  orange;
	}
	.td_num,.td_readcount {
		width : 70px;
	}
	.td_subject{
		width : 200px;
		text-align: left;
	}
	.td_writer,.td_regdate{
	  width : 110px;
	}
	
</style>
</head>
<body>
	<section id = "articleListArea">
		<h1>글목록 <a href="writeForm.jsp">글쓰기</a></h1>
		<table>
			<tr class = "tr_title">
				<td class = "td_num">번호</td>
				<td class = "td_subject">제목</td>
				<td class = "td_writer">작성자</td>
				<td class = "td_regdate">작성일</td>
				<td class = "td_readcount">조회수</td>
			</tr>
			<c:set var = "number" value = "${pageInfo.number }"></c:set>
			<c:forEach var = "article" items = "${articleList }">
			<tr>
				<td class = "td_num">${number }</td>
			<c:set var = "number" value = "${number - 1}"></c:set>
				<td class = "td_subject">
				<c:forEach begin="1" end="${article.re_level }" step="1">
				&nbsp;&nbsp;&nbsp;
				</c:forEach>
				<c:if test="${article.re_level > 0 }">
				re :
				</c:if> 
				<a href = "boardContent.bo?num=${article.num }&pageNum=${pageInfo.currentPage}">${article.subject }</a>
				</td>
				<td class = "td_writer">${article.writer}</td>
				<td class = "td_regdate">
				<fmt:formatDate var = "regDate" value="${article.reg_date }" pattern = "yyyy.MM.dd"/>
				<c:out value="${regDate }"></c:out>
				</td>
				<td class = "td_readcount">${article.readcount}</td>
			</tr>
			</c:forEach>
		</table>
		<c:if test="${pageInfo.count > 0 }">
		<c:if test="${pageInfo.startPage > 10 }">
				<a href = "list.jsp?pageNum=${pageInfo.startPage - 10 }">[이전]</a>
		</c:if>
		<c:forEach var = "i" begin="${pageInfo.startPage }" end="${pageInfo.endPage}">
				<a href = "list.jsp?pageNum=${i }">[${i }]</a>
		</c:forEach>		
		<c:if test="${pageInfo.endPage < pageInfo.pageCount }">
				<a href = "list.jsp?pageNum=${pageInfo.startPage + 10 }">[다음]</a>
		</c:if>
		</c:if>
	</section>
</body>
</html>










