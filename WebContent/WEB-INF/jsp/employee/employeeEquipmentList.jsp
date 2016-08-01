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
        dateFormat: "yy-mm-dd" }).val()
});
</script>
<script>
	function borrowMsg() {
		alert("機器一つを貸出しました。");
		window.opener.location.reload(true);
	}

	function changeTable(radioNo) {
		if (radioNo == 1) {
			href = "file:///P:/new project/employee/EmployeeBorrow.html";
		} else {
			href = "file:///P:/new project/employee/EmployeeEquipmentList.html";
		}
	}

	function submitForm() {
		document.getElementById("myForm").submit();
	}
</script>
<script>
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
			<jsp:include page = "/WEB-INF/jsp/header.jsp" />
			<hr>
		</div>
		<div>
			<h2>機器一覧</h2>
		</div>

		<!--Search Form -->
		<div align="center">
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<form id="myForm" action="LoginServlet" method="get">
									<td width="200"><input type="radio" name="action"
										value="equipmentList" onclick="submitForm()" checked="checked">
										全体機器</td>
									<td width="200"><input type="radio" name="action"
										value="borrowList" onclick="submitForm()"> 貸出</td>		
								</form>						
							</tr>
						</table>
					</td>
					<td>
						<form action="EquipmentServlet" method="get">
							<table height="200">
								<tr>
									<td width="200" align="center"><input id="datepicker"
										name="date" disabled="disabled" placeholder="貸出日付"></input></td>
									<td width="200" align="center"><input type="text"
										id="equipmentName" name="equipementName" placeholder="機器名"></input></td>

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
				<c:when test="${not empty equipmentList }">
					<table border="1">
						<thead>
							<tr>
								<th>機器ID</th>
								<th>カテゴリ名</th>
								<th>機器名</th>
								<th>機器の状況</th>
								<th></th>
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
									<td width="200">
										<form action="BorrowServlet" method="post">
										    <input type="hidden" name="borrowId"
                                                value="${equipment.borrowId}">
											<input type="hidden" name="equipmentId"
												value="${equipment.equipmentId}"> <input style="width: 100px"
												onclick="borrowMsg()" type="submit" value="貸出">
										</form>
									</td>
								</tr>
							</c:forEach>
						</tbody>

					</table>
				</c:when>
				<c:otherwise>
					<div>申し訳ありませんが、その検索した機器は会社にありまぜん。</div>
				</c:otherwise>
			</c:choose>

		</div>
		<!-- footer -->		
		<div>
        <hr>
            <jsp:include page="/WEB-INF/jsp/footer.jsp"/>            
        </div>
	</div>
</body>
</html>