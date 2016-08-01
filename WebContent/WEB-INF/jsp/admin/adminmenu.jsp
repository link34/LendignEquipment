<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<title>貸出機器管理システム</title>
</head>
<body>
<h1 align = "center">メニュー</h1>
<div align="center">
	<table align = "center">
<tr>
<td>
<form action = "LoginServlet" method = "get">
<input type = "hidden" name = "action" value = "adminBorrow">
<p align = "center"><input type = "submit" value = "     機器一覧     " width="200"></p>
</form>
<form action = "LoginServlet" method = "get">
<input type = "hidden" name = "action" value = "employeeHistory">
<p align = "center"><input type = "submit" value = "     社員一覧     " width="200"></p>
</form>
</td>
</tr>
</table>
</div>
</body>
</html>