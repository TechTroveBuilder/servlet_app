<%@ page import="java.util.ArrayList"%>
<%@ page import="websample13_14.ItemBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%-- Java入門 商品一覧画面 --%>
<!DOCTYPE html>
<html>
	<head>
		<title>Java入門</title>
		<link href="/schoo/WEB-sample13_14/css/shopping.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<% //TODO:2-① jsp:includeでヘッダー画面を読み込む%>
		<jsp:include page="header.jsp"/>
		
		<h1>商品一覧</h1>
		<%-- リクエストスコープからBeanクラスの配列を取得（eclipseの警告が出ても今回は大丈夫です） --%>
		<% ArrayList<ItemBean> itemList = (ArrayList<ItemBean>)request.getAttribute("itemList"); %>
		<form action="../websample13_14/BuyItemServlet">
			<table class="table_list">
				<tbody>
					<tr>
						<th>商品ID</th>
						<th>商品名</th>
						<th>価格</th>
						<th>在庫数</th>
						<th>数量</th>
					</tr>
					
					<%-- Beanの要素数分（商品の種類分）テーブルを作成 --%>
					<% for(ItemBean bean : itemList) { %>
					<tr>
						<%-- 商品ID --%>
						<td><%= bean.getItemId() %></td>
						<%-- 商品名 --%>
						<td><%= bean.getItemName() %></td>
						<%-- 価格 --%>
						<td class="int"><%= bean.getPrice() %></td>
						<%-- 数量（在庫） --%>
						<td class="int"><%= bean.getQuantity() %></td>
						
						<% //TODO:2-② 在庫が0の場合はリストボックスと購入ボタンを表示しない処理を入れる%>
						<% if(bean.getQuantity() != 0) { %>
						<td>
							<select class="list" name="<%=bean.getItemId() %>list">
								<% for(int i = 0; i <= bean.getQuantity(); i++) { %>
									<option value ="<%=i %>"><%=i %></option>
								<% } %>
							</select>
						</td>
						<td>
							<input class="common_button" type="submit" value="購入" name="<%=bean.getItemId() %>"/>
						</td>
						<% } else {%>
							<td></td>
							<td class="button">SoldOut!</td>
						<% } %>
					</tr>
				<% } %>
				</tbody>
			</table>
			<input class="common_button" type="button" onclick="location.href='../WEB-sample13_14/login.jsp'" value="戻る">
		</form>
	</body>
</html>