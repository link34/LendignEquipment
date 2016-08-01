<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<title>貸出機器管理システム</title>
</head>
<body>
<h1 align = "center">ログイン</h1>
<form action = "LoginServlet" method = "post">
<table align = "center">
<tr>
<td><p>　　ID</p></td>
<td></td>
<td><input size = "35" type = "text" name = "memberId" required="required"></td>
</tr>
<tr>
<td><p>パスワード</p></td>
<td></td>
<td><input size = "36" type = "password" name = "pass" required="required"></td>
</tr>
<tr>
	<td></td>
	<td></td>
	<td><input type = "submit" value = "ログイン"></td>
</tr>
</table>
</form>
<p align = "center"><font color = "red">${requestScope.Msg}</font></p>
</body>
</html>