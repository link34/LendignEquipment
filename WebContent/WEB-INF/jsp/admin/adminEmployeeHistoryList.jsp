<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="ja">
<head>
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
	function borrowMsg() {
		alert("機器一つを貸出ました。");
	}
	function deleteMsg() {
		confirm("貸出た機器社員を削除しまか？");
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
		<div>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
			<hr>
		</div>
		<div align="left">
			<h3>管理者の社員管理パージ</h3>
		</div>
		<br>
		<!-- 検索オプション -->
		<div align="center">
			<table height="100">
				<tr>
					<td><label>新しい社員 :</label></td>
					<td><button style="width: 100px" type="submit"
							onclick="popupCenter('PageChangeServlet?action=addEmployee&employeeId=${equipment.equipmentId}', 'myPop1',450,450);"
							href="javascript:void(0);">登録</button></td>
					<form action="memberServlet" method="get">
						<td width="300" align="center"><input id="datepicker"
							name="date" placeholder="貸出日付"></input></td>
						<td width="200" align="center"><input type="text"
							name="equipment" placeholder="機器名"></input></td>
						<td width="200" align="center"><input type="text"
							name="equipment" placeholder="部門名"></input></td>
						<td width="100"><input type="submit" value="検索"
							style="width: 100px"></input></td>
					</form>
				</tr>
			</table>
		</div>
		<!-- table の表示 -->
		<div align="center">
			<table border="1">
				<tr>
					<th>社員ID</th>
					<th>社員名</th>
					<th>機器名</th>
					<th>部門名</th>
					<th>貸出日付</th>
					<th>返却日付</th>
					<th>機器の状況</th>
					<th>機器異動理由</th>
				</tr>
				<c:forEach items="${employeeList}" var="employeeList">
					<tr>
						<td width="150"><c:out value="${employeeList.memberId}" /></td>
						<td width="150"><c:out value="${employeeList.memberName}" /></td>
						<td width="150"><c:out value="${employeeList.equipmentName}" /></td>
						<td width="200"><c:out value="${employeeList.depertmentName}" /></td>
						<td width="150"><c:out value="${employeeList.borrowDate}" /></td>
						<td width="150"><c:out value="${employeeList.returnDate}" /></td>
						<td width="150"><c:out value="${employeeList.status}" /></td>
						<td width="200"><c:out value="${employeeList.comment}" /></td>
						<td><button style="width: 100px" type="submit"
								onclick="popupCenter('PageChangeServlet?action=employeeChange&memberId=${employeeList.memberId}', 'myPop1',450,450);"
								href="javascript:void(0);">変更</button></td>
						<td><form action="EmployeeServlet" method="get">
						         <input type="hidden" value="${employeeList.memberId}" name="memberId"/>
								<input  style="width: 100px" type="submit" onclick="deletemsg()" value="削除"/>
							</form>
						</td>
					</tr>
				</c:forEach>

			</table>
		</div>
	</div>
</body>
</html>