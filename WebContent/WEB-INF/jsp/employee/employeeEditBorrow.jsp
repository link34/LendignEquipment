<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<script type="text/javascript">
	function check() {
		alert("機器の変更は完了しました。");	
		window.opener.location.reload(true);
		window.close();
	}
</script>
<title>貸出機器管理システム</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
</head>
<body>
	<div align="center">
		<form action="BorrowServlet" method="post">
			<table height="300">
				<tr>
					<td>機器ＩＤ</td>
					<td width="100" align="center">:</td>
					<td><c:out value="${sessionScope.equipment.equipmentId}" /></td>
				</tr>
				<tr>
					<td>カテゴリ名</td>
					<td align="center">:</td>
					<td><c:out value="${sessionScope.equipment.categoryName}" /></td>
				</tr>
				<tr>
					<td>機器名</td>
					<td align="center">:</td>
					<td><c:out value="${sessionScope.equipment.equipmentName}" /></td>
				</tr>
				<tr>
					<td>貸出日付</td>
					<td align="center">:</td>
					<td><c:out value="${sessionScope.equipment.borrowDate}" /></td>
				</tr>
				<tr>
					<td>機器の状況</td>
					<td align="center">:</td>
					<td><select name="status">
							<option>使用中</option>
							<option>返却</option>
					</select></td>
				</tr>
				<tr>
					<td>機器異動理由</td>
					<td align="center">:</td>
					<td><textarea name="textarea"></textarea> </td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td>
					   <input type="hidden" name="changeId"
                       value="${equipment.equipmentId}">
				       <input type="hidden" name="borrowId"
                       value="${equipment.borrowId}"> <input
                       onclick="check()" type="submit" value="変更" class="ui-button ui-widget ui-corner-all">                     						
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>