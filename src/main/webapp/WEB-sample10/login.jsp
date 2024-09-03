<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<jsp:useBean id="user" scope="session" class="websample10.LoginUserBean" />
<% // TODO:①useBeanタグを追加する %>
<%-- Java入門 ログイン機能 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="/Schoo/css/login.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<div class="login_pane">
			<h1>ログイン画面</h1>
			<h2>ログインIDとパスワードを入力して下さい</h2>
			<form action="../LoginNewServlet" method="post">
				<table class="table_form">
					<tbody>
						<tr>
							<%-- ログイン済みの場合はIDを表示 --%>
							<th>ログインID</th>
							<% // TODO:②valueにuser.getId()を追加する %>
							
							
							<% if(user.getId() == null) {%>
							<td><input type="text" name="id" value=""/></td>
							<% } else {%>
							<td><input type="text" name="id" value="<%= user.getId()%>"/></td>
							<% } %>
						</tr>
						<tr>
							<th>パスワード</th>
							<td><input type="password" name="pass"/></td>
						</tr>
					</tbody>
				</table>
				<input class="common_button" type="submit" name="submit"  value="ログイン"/>
				<%-- ログイン済みの場合はログアウトボタンを表示 --%>
				<% // TODO:③sessionからロイン状態が"login"であることを判定する %>
				<% if("login".equals(session.getAttribute("login"))) {%>
				<input class="common_button" type="submit" name="submit" value="ログアウト"/>
				<% } %>
			</form>
		</div>
	</body>
</html>