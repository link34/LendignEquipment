<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8" />
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
  <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
  <link rel="stylesheet" href="/demos/style.css" />
  <script>
  	function borrowMsg() {
    	alert("機器一つを貸出ました。");
		}
	function check() {
	alert("社員の変更完了しました。");
	opener.reload();
	window.close();
	}
  </script>
</script>
	<title></title>
</head>
<body>
	<div align="center">
		<form>
		<table height="300">
		<tr>
			<td>社員ＩＤ</td>
			<td width="100" align="center">:</td>
			<td>EM-002</td>
		</tr>
		<tr>
			<td>社員名</td>
			<td align="center">:</td>
			<td width="100">
				桜
			</td>
		</tr>
		<tr>
			<td>部門名</td>
			<td align="center">:</td>
			<td width="100">
				管理本部
			</td>
		</tr>
		<tr>
			<td>カテゴリ名</td>
			<td align="center">:</td>
			<td width="150">
				<select style="width:140px;">
					<option>IT機器</option>
					<option>インターネット</option>
					<option>サーバ・ストレージ</option>
					<option>情報システム</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>機器名</td>
			<td align="center">:</td>
			<td width="150">
				<select style="width:140px;">
					<option>プリンタ</option>
					<option>UPS</option>
					<option>プロジェクタ</option>
					<option>KVMスイッチ</option>
					<option>複合機</option>
				</select>
			</td>			
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td><label>UPS</label></td>
			
		</tr>
		<tr>
			<td>貸出日付</td>
			<td align="center">:</td>
			<td><label>2016/07/23</label></td>
		</tr>
		<tr>
			<td>返却日付</td>
			<td align="center">:</td>
			<td><label>-</label></td>
		</tr>
		<tr>
			<td>機器の状況</td>
			<td align="center">:</td>
			<td>
				<select>
					<option>貸出中</option>
					<option>未使用</option>
					<option>廃棄</option>
				</select>
			</td>			
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td><button style="width: 100px" onclick="check()">変更</button></td>
		</tr>			
	</table>
	</form>	
	</div>	
</body>
</html>