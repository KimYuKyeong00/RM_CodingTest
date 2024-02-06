<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/JS/jQuery.js"></script>
<script type="text/javascript" src="/resources/JS/subscribe/privateSubscribe.js"></script>
</head>
<body>
<h1>개인 구독</h1>
<table border="1">
	<tr>
		<td>
			서비스 종류 :
			<select id="service_id" name="commodity" form="privateSubscribeOrder" >
			</select>
		</td>
		<td>
			스토리지 용량(TB) :
			<select id = "storage_capacity" name="capacity" form="privateSubscribeOrder">
				<c:forEach var="i" begin="1" end="10" step="1">
					<option value="${i }"> ${i } </option>
				</c:forEach>
			</select>
		</td>
		<td>
			구독 기간(개월) :
			<select id = "period" name="period" form="privateSubscribeOrder">
				<c:forEach var="j" begin="1" end="12" step="1">
					<option value=${j }> ${j } </option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td colspan="3">비용 : <input id="cost" readonly="readonly"></td>
	</tr>
	<tr>
		<td colspan="3">
			<form id="privateSubscribeOrder" action="psub.subscribe" method="post">
				<button>구독하기</button>
			</form>
		</td>
	</tr>
</table>
</body>
</html>