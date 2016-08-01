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
	opener.reload();
	window.close();
	}
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
			<td><input type="text" name="employeeId" placeholder="社員ID"></td>
		</tr>
		<tr>
			<td>社員名</td>
			<td align="center">:</td>
			<td width="100">
				<input type="text" name="employeeName" placeholder="社員の名前">
			</td>
		</tr>
		<tr>
			<td>部門名</td>
			<td align="center">:</td>
			<td width="100">
				<select style="width:140px;">
					<option>社会基盤システム事業部</option>
					<option>管理本部</option>
					<option>ソリューション事業部</option>
					<option>ICTサービスセンター</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>カテゴリ名</td>
			<td align="center">:</td>
			<td width="100">
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
			<td width="100">
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
			<td><label>-</label></td>
		</tr>
		<tr>
			<td>返却日付</td>
			<td align="center">:</td>
			<td><label>2016/05/12</label></td>
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
			<td><button onclick="check()">登録</button></td>
		</tr>			
	</table>
	</form>	
	</div>	
</body>
</html>