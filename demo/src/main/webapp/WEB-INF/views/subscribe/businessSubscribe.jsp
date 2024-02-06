<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/JS/jQuery.js"></script>
<script type="text/javascript" src="/resources/JS/subscribe/businessSubscribe.js"></script>
</head>
<body>
<h1>기업 구독</h1>
<table border="1">
	<tr>
		<td>
			서비스 종류 :
			<select id="service_id" name="commodity" form="businessSubscribeOrder" >
			</select>
		</td>
		<td>
			스토리지 용량(TB) :
			<select id = "storage_capacity" name="capacity" form="businessSubscribeOrder">
				<c:forEach var="i" begin="1" end="10" step="1">
					<option value="${i }"> ${i } </option>
				</c:forEach>
			</select>
		</td>
		<td>
			구독 기간(개월) :
			<select id = "period" name="period" form="businessSubscribeOrder">
				<c:forEach var="j" begin="1" end="12" step="1">
					<option value=${j }> ${j } </option>
				</c:forEach>
			</select>
		</td>
		<td>
			사용인원(관리자 포함) :
			<input id="countMember" name="countMember" form="businessSubscribeOrder" value="1">
		</td>
	</tr>
	<tr>
		<td colspan="4">비용 : <input id="cost" readonly="readonly"></td>
	</tr>
	<tr>
		<td colspan="4">
			<form id="businessSubscribeOrder" action="bsub.subscribe" method="post">
				<button>구독하기</button>
			</form>
		</td>
	</tr>
</table>
</body>
</html>