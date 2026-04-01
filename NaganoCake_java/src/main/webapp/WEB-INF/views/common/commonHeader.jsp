<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<!-- ログイン状態の表示スタブ -->
		<c:choose>
			<c:when test="${memberId != null}">
				<p>ようこそ、${sessionScope.memberFirstName}
					${sessionScope.memberLastName}さん！</p>
				<!-- ToDo: ログアウトコントローラーかjsでの処理を追加する必要あり。-->
				<form
					action="${pageContext.request.contextPath}/CustomerCartController?action=list"method="post" class="cart-form">
					<button type="submit" class="add-cart-btn">カート[${cartItem.amount}]</button>
				</form>
				<a href="${pageContext.request.contextPath}/logout">	ログアウト	</a>
				<a href="${pageContext.request.contextPath}/CustomerMypageForm">	マイページ	</a>
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/CustomerLoginForm">ログイン</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>