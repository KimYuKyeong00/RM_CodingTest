<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<table>
	
	<tr>
		<td><a href="/">home</a></td>
		<td>
			<c:choose>
				<c:when test="${sessionScope.LoginMember == null }">
					<a href="go.login">로그인</a>
				</c:when>
				<c:otherwise>
					<a href="logout">로그아웃</a>
				</c:otherwise>
			</c:choose>	
		</td>
		<td>
			<c:choose>
				<c:when test="${sessionScope.LoginMember == null }">
					<a href="go.signup">회원가입</a>
				</c:when>
				<c:otherwise>
					<a>${sessionScope.LoginMember.getId() }</a>
				</c:otherwise>
			</c:choose>	
				
	</tr>
	<tr>
		<td colspan="3">
			<jsp:include page="${subPage }" />
		</td>
	</tr>
</table>

</body>
</html>