<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
<link rel="stylesheet" href="/demos/style.css" />
<script>
	function check() {
		alert("社員の変更完了しました。");
		opener.reload();
		window.close();
	}
</script>

<title></title>
</head>
<body>
	<div align="center">
		<form action="EmployeeServlet" method="post">
			<table height="300">
				<tr>
					<td>社員ＩＤ</td>
					<td width="100" align="center">:</td>
					<td><c:out value="${member.memberId}" /></td>
				</tr>
				<tr>
					<td>社員名</td>
					<td align="center">:</td>
					<td width="100"><c:out value="${member.memberName}" /></td>
				</tr>
				<tr>
					<td>部門名</td>
					<td align="center">:</td>
					<td width="100"><c:out value="${member.depertmentName}" /></td>
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
					<td width="150"><input type="text" name="equipmentName" paceholder="機器名を入力してください。"/></td>
				</tr>

				<tr>
					<td>貸出日付</td>
					<td align="center">:</td>
					<td><c:out value="${member.borrowDate}" /></td>
				</tr>
				<tr>
					<td>返却日付</td>
					<td align="center">:</td>
					<td><c:choose>
							<c:when test="${not empty member.returnDate}">
								<c:out value="${member.returnDate}" />
							</c:when>
							<c:otherwise>
								<label> - </label>
							</c:otherwise>
						</c:choose></td>
				</tr>
				<tr>
					<td>機器の状況</td>
					<td align="center">:</td>
					<td><select name="status">
							<option>貸出中</option>
							<option>未使用</option>
							<option>廃棄</option>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><button style="width: 100px" onclick="check()"
							type="submit">変更</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>