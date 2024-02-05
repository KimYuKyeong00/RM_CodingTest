<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/JS/jQuery.js"></script>
<script type="text/javascript" src="/resources/JS/subscribe/pmExtension.js"></script>
</head>
<body>
<h1>기간 연장</h1>
추가비용 : <input readonly="readonly" id="extension_cost">
현재 만료일 : <input readonly="readonly" id="expire">
연장시 만료일: <input readonly="readonly" id="extended"> 
<form action="psub.extense" method="post">
	<select id="private_extension"  name="extension_period">
		<c:forEach var="i" begin="1" end="12">
			<option value=${i }> ${i }개월(${i*30}일) </option>
		</c:forEach>
	</select>
	<button>연장하기</button>
</form>
</body>
</html>