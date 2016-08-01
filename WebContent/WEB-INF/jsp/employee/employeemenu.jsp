<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<title>貸出機器管理システム</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
</head>
<body>
<h1 align = "center">メニュー</h1>
<table align = "center">
<tr>
<td>
<form action = "LoginServlet" method = "get">
<input type = "hidden" name = "action" value = "equipmentList">
<p align = "center"><input size = "35" type = "submit" value = "機器一覧(貸出)" style="height: 100px; width: 120px" class="ui-button ui-widget ui-corner-all"></p>
</form>
</td>
</tr>
</table>
</body>
</html>