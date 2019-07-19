<%@page import="omikuji.Omikuji"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/css/style.css"rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
				<!-- ヘッダーの表示 -->
		<%@include file="/JSP/common/header.jsp" %>

		<div align= "center" ><h2>おみくじ結果</h2></div>

				<!-- おみくじの結果を表示 Unseiクラスのフィールド名を使う -->
		<table>
		<tr>
		<th>運勢: </th>
		<td> ${omikuji.unsei} </td>
		</tr>
		<tr>
		<th>願い事: </th>
		<td>${omikuji.negaigoto}</td>
		</tr>
		<tr>
		<th>商い:</th>
		<td>${omikuji.akinai}</td>
		</tr>
		<tr>
		<th>学問:</th>
		<td>${omikuji.gakumon}</td>
		</tr>
		</table>

					<!-- 誕生日入力画面に遷移 -->
			<div align = "center">
			<a class="padding" href ="<%=request.getContextPath() %>/InputBirthdayServlet">入力画面に戻る</a>
			</div>

</body>
</html>