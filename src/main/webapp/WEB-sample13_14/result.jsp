<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- Java入門 購入結果画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="/WEB-sample13_14/css/shopping.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<h1>購入結果</h1>
		<p>購入しました。<img src="/schoo/WEB-sample13_14/img/Thankyou.jpg" width="100" height="100"/></p>
		<form action="../websample13_14/ShoppingServlet" method="post">
		
			<input class="common_button" type="submit" name="back"  value="一覧に戻る">
		</form>
	</body>
</html>