<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/JS/jQuery.js"></script>
<script type="text/javascript" src="/resources/JS/storage/bmStorage.js"></script>
</head>
<body>
<h2>기업 저장소</h2>
<table id="bmStorage">
	<tr>
		<td>구독중인 서비스 :<input id="commodity" readonly="readonly"></td>
		<td><form action="go.buc" method="post"><button>업그레이드</button></form></td>
	</tr>
	<tr>
		<td>만료일 :<input id="expire_day" readonly="readonly"></td>
		<td><form action="go.bu" method="post"><button>구독 연장하기</button></form></td>
	</tr>
	<tr>
		<td>용량 상태(GB) :<input id="storage_status" readonly="readonly"></td>
		<td><form action="go.bas" method="post"><button>용량 추가하기</button></form></td>
	</tr>
	<tr>
		<td>
			파일 업로드 : <form action="bs.upload" method="post" enctype="multipart/form-data"><input type="file" name="file"><button>확인</button></form>
		</td>
	</tr>
</table>

<table border="1">
		<tr>
			<td>파일이름</td>
			<td>파일크기(byte)</td>
			<td>파일형식</td>
			<td></td>
		</tr>
	<c:forEach items="${bfl }" var="pf">
		
		<tr>
			<td> ${bf.getFile_name() }</td>
			<td> ${bf.getFile_size() }</td>
			<td> ${bf.getFile_extension() }</td>
			<td>
				<form action="bs.download" method="post">
					<input hidden="hidden" name="private_file_serial" value=${pf.getPrivate_file_serial() }>
					<button>다운로드</button>
				</form>
			</td>
			<td>
				<form action="bs.delete" method="post">
				<input hidden="hidden" name="private_file_serial" value=${pf.getPrivate_file_serial() }>
				<button>삭제</button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>