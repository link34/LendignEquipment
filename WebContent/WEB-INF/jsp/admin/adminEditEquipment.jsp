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
	alert("機器の変更完了しました。");
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
			<td>機器ＩＤ</td>
			<td width="100" align="center">:</td>
			<td><input type="text" name="equipmentId" placeholder="機器のＩＤ"></input></td>
		</tr>
		<tr>
			<td>カテゴリ名</td>
			<td align="center">:</td>
			<td>
				<select>
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
			<td>
				<select>
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
			<td>機器の状況</td>
			<td align="center">:</td>
			<td>
				<select>
					<option>未使用</option>
					<option>貸出中</option>
					<option>廃棄</option>
					<option>返却</option>
				</select>
			</td>
		</tr>		
		<tr>
			<td></td>
			<td></td>
			<td><button onclick="check()">変更</button></td>
		</tr>			
	</table>
	</form>	
	</div>	
</body>
</html>