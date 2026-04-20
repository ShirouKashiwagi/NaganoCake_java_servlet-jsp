<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>ショッピングカート</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartList.css">
</head>
<body>

	<%@ include file="/WEB-INF/views/common/application.jsp"%>

	<h2>ショッピングカート</h2>

	<c:if test="${empty cartItems}">
		<p>カートは空です。</p>
	</c:if>

	<c:if test="${not empty cartItems}">
		<table border="1" cellpadding="10">
			<tr>
				<th>商品名</th>
				<th>単価</th>
				<th>数量</th>
				<th>小計</th>
				<th>操作</th>
			</tr>

			<c:forEach var="ci" items="${cartItems}">
				<tr>
					<td>${ci.item.name}</td>

					<td><fmt:formatNumber value="${ci.item.price}" type="number" />
					</td>

					<td>
						<form action="CartUpdateServlet" method="post">
							<input type="hidden" name="cartItemId" value="${ci.cartItem.id}">
							<input type="number" name="amount" value="${ci.amount}" min="1">
							<button type="submit">変更</button>
						</form>
					</td>

					<td>
						<fmt:formatNumber value="${ci.item.price * ci.amount}" type="number" />
					</td>

					<td>
						<form action="CartDeleteServlet" method="post">
							<input type="hidden" name="cartItemId" value="${ci.id}">
							<button type="submit">削除</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>

		<br>

		<form action="CartDeleteAllServlet" method="post">
			<button type="submit">カートを空にする</button>
		</form>

		<br>

		<form action="OrderConfirmServlet" method="get">
			<button type="submit">情報入力に進む</button>
		</form>

	</c:if>

</body>
</html>
