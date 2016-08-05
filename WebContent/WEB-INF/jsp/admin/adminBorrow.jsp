<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
<style>
table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    text-align: left;
    padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}

th {
    background-color: #4CAF50;
    color: white;
}
</style>
<meta charset="utf-8" />
<title>貸出機器管理システム</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stylesheet" href="/demos/style.css" />
<script>
	$(function() {
		$("#datepicker").datepicker({
			onSelect : function(date, picker) {
				alert(date);
			}
		});
	})
</script>
<script>
	function submitForm() {
		document.getElementById("myForm").submit();
	}
	function borrowMsg() {
		alert("機器一つを貸出ました。");
		window.opener.location.reload(true);
	}
	function deleteMsg() {
		alert("貸出た機器を削除しました。");
	}
	function popupCenter(url, title, w, h) {
		var left = (screen.width / 2) - (w / 2);
		var top = (screen.height / 2) - (h / 2);
		return window
				.open(
						url,
						title,
						'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width='
								+ w
								+ ', height='
								+ h
								+ ', top='
								+ top
								+ ', left=' + left);
	}
</script>
</head>
<body>
	<div>
		<!-- header -->
		<div>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
			<hr>
		</div>
		<div align="left">
			<h3>管理者の機器管理パージ</h3>
		</div>
		<br>
		<!--Search Form -->
		<div align="center">
			<table height="100">
				<tr>
				    <!-- 登録 ボタン -->
					<td><label>新しい機器 :</label></td>
					<td><button style="width: 100px" type="submit"
							onclick="popupCenter('PageChangeServlet?action=add&equipmentId=${equipment.equipmentId}', 'myPop1',450,450);"
							href="javascript:void(0);">登録</button></td>
					
					<!-- radio ボタン -->
					<form id="myForm" action="LoginServlet" method="get">
						<td width="200"><input type="radio" name="action"
							value="equipmentList" onclick="submitForm()" checked="checked">
							全体機器</td>
						<td width="200"><input type="radio" name="action"
							value="borrowList" onclick="submitForm()"> 貸出</td>
					</form>
					
					<!-- 検索 ボタン -->
					<form action="EquipmentServlet" method="get">
						<td width="200" align="center"><input id="datepicker"
							name="date" disabled="disabled" placeholder="貸出日付"></input></td>
						<td width="200" align="center"><input type="text"
							id="equipmentName" name="equipementName" placeholder="機器名"></input></td>

						<td width="200"><input type="submit" value="検索"
							style="width: 100px"></input></td>
					</form>
				</tr>
			</table>
		</div>
		<!-- table の表示 -->
		<div align="center">
			<table border="1">
				<thead>
					<tr>
						<th>機器ID</th>
						<th>カテゴリ名</th>
						<th>機器名</th>
						<th>機器の状況</th>
						<th>貸出</th>
						<th>変更</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${equipmentList}" var="equipment">
						<tr>
							<td width="200"><c:out value="${equipment.equipmentId}" /></td>
							<td width="200"><c:out value="${equipment.categoryName}" /></td>
							<td style="display: none"><c:out
									value="${equipment.categoryId}" /></td>
							<td width="200"><c:out value="${equipment.equipmentName}" /></td>
							<td width="200"><c:out value="${equipment.status}" /></td>

							<form action="BorrowServlet" method="post">
								<td width="200" align="center"><input type="hidden"
									name="borrowId" value="${equipment.borrowId}"> <input
									type="hidden" name="equipmentId"
									value="${equipment.equipmentId}"> <input
									style="width: 100px" onclick="borrowMsg()" type="submit"
									value="貸出"></td>
									
								<td width="200" align="center">
									<button style="width: 100px" type="submit"
										onclick="popupCenter('PageChangeServlet?action=equipChange&equipmentId=${equipment.equipmentId}', 'myPop1',450,450);"
										href="javascript:void(0);">変更</button>
								</td>
							</form>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>