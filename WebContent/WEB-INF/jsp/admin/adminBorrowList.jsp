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
	function submitForm() {
		document.getElementById("myForm").submit();
	}
	function borrowMsg() {
		alert("機器一つを貸出ました。");
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
		<div>
			<jsp:include page="/WEB-INF/jsp/header.jsp" />
			<hr>
		</div>
		<div align="left">
			<h3>管理者の貸出機器パージ</h3>
		</div>
		<br>
		<!--Search Form -->
		<div align="center">
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<form id="myForm" action="LoginServlet" method="get">
									<td width="200"><input type="radio" name="action"
										value="equipmentList" onclick="submitForm()"> 全体機器</td>
									<td width="200"><input type="radio" name="action"
										value="borrowList" onclick="submitForm()" checked="checked">
										貸出</td>
								</form>
							</tr>
						</table>
					</td>
					<td>
						<form action="BorrowServlet" method="get">
							<table height="100">
								<tr>
									<td width="200" align="center"><input id="datepicker"
										name="date" placeholder="貸出日付"></input></td>
									<td width="200" align="center"><input type="text"
										id="equipmentName" name="equipmentName" placeholder="機器名"></input></td>

									<td width="200"><input type="submit" value="検索"
										style="width: 100px"></input></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>

		</div>
		<!--Employees List-->
		<div align="center">
			<c:choose>
				<c:when test="${not empty borrowList }">
					<table border="1">
						<tr>
							<th width="200">機器ID</th>
							<th width="200">カテゴリ名</th>
							<th width="200">機器名</th>
							<th width="200">貸出日付</th>
							<th width="200">機器の状況</th>
							<th width="200">機器異動理由</th>
						</tr>
						<c:forEach items="${borrowList}" var="borrowList">
							<tr>
								<td><c:out value="${borrowList.equipmentId}"></c:out></td>
								<td><c:out value="${borrowList.categoryName}"></c:out></td>
								<td><c:out value="${borrowList.equipmentName}"></c:out></td>
								<td><c:out value="${borrowList.borrowDate}"></c:out></td>
								<td><c:out value="${borrowList.status}"></c:out></td>
								<td><c:out value="${borrowList.comment}"></c:out></td>
								<td width="200">
									<button style="width: 100px" type="submit"
										onclick="popupCenter('PageChangeServlet?action=popup&equipmentId=${borrowList.equipmentId}', 'myPop1',450,450);"
										href="javascript:void(0);">変更</button>
								</td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<div>申し訳ありませんが、その検索したのはありまぜん。</div>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- footer -->
		<div>
			<!-- footer -->
			<div>
				<hr>
				<jsp:include page="/WEB-INF/jsp/footer.jsp" />
			</div>
		</div>
</body>
</html>