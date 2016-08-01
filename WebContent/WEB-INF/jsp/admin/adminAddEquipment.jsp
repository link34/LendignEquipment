<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<script type="text/javascript">
	function borrowMsg() {
		alert("機器一つを貸出ました。");
	}
	function check() {
		alert("機器の登録完了しました。");
		window.opener.location.reload(true);
		window.close();
	}
</script>
<title></title>
</head>
<body>
	<div align="center">
		<form action="AddServlet" method="post">
			<table height="300">
				<tr>
					<td>機器ＩＤ</td>
					<td width="100" align="center">:</td>
					<td><input type="text" name="equipmentId" placeholder="機器ID"></td>
				</tr>
				<tr>
					<td>カテゴリ名</td>
					<td align="center">:</td>
					<td width="100"><select style="width: 140px;" name="categoryId">
					        <option>カテゴリ名選択</option>
							<c:forEach items="${equipmentList}" var="equipmentList">
								<option value="${equipmentList.categoryId}">
								<c:out value="${equipmentList.categoryName}"/> 
								</option>								
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>機器名</td>
					<td align="center">:</td>
					<td width="100"><input type="text" name="equipmentName"
						placeholder="機器名を入力して"></td>
				</tr>				
				<tr>
					<td>貸出日付</td>
					<td align="center">:</td>
					<td><label>なし</label></td>
				</tr>
				<tr>
					<td>機器の状況</td>
					<td align="center">:</td>
					<td>未使用</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><button onclick="check()" type="submit">登録</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>