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
		alert("社員の登録完了しました。");
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
					<td>社員ＩＤ</td>
					<td width="100" align="center">:</td>
					<td><input type="text" name="employeeId" placeholder="社員ID"></td>
				</tr>
				<tr>
					<td>社員名</td>
					<td align="center">:</td>
					<td width="100"><input type="text" name="employeeName"
						placeholder="社員の名前"></td>
				</tr>
				<tr>
                    <td>パスワート</td>
                    <td align="center">:</td>
                    <td width="100"><input type="password" name="pass"
                        placeholder="社員のパスワート"></td>
                </tr>
				<tr>
					<td>部門名</td>
					<td align="center">:</td>
					<td width="100">
					<select style="width: 140px;" name="depertmentId">					
							<c:forEach items="${departmentList}" var="departmentList">
								<option value="${departmentList.depertmentId}"><c:out value="${departmentList.depertmentName}"/></option>							
							</c:forEach>
					</select></td>
				</tr>
				<tr>
				<td>管理者</td>
				<td align="center">:</td>
				<td><input type="checkbox" name="cboAdmin"/></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><button onclick="check();" type="submit">登録</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>