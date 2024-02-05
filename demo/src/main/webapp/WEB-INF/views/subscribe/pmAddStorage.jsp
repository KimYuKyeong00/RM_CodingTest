<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/JS/jQuery.js"></script>
<script type="text/javascript" src="/resources/JS/subscribe/pmAddStorage.js"></script>
</head>
<body>
<h1>스토리지 추가</h1>
추가비용 : <input readonly="readonly" id="extra_cost">

<form action="psub.expand" method="post">
	<select id="extra_storage"  name="extra_storage">
		<c:forEach var="i" begin="1" end="10">
			<option value=${i }> ${i }TB </option>
		</c:forEach>
	</select>
	<button>스토리지 추가하기</button>
</form>
</body>
</html>