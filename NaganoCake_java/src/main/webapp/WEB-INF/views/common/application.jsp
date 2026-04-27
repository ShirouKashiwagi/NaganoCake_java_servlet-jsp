<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>NaganoCake</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/application.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

	<header class="shadow">
		<nav class="navbar navbar-expand-lg navbar-light">
			<div class="container">

				<a class="navbar-brand" href="${pageContext.request.contextPath}/">
					<img
					src="${pageContext.request.contextPath}/images/items/no-image.png"
					alt="Nagano Cake" class="logo" style="max-width: 200px; max-height: 100px;">
				</a>

				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarNavDropdown">
					<ul class="navbar-nav ml-auto">

						<c:choose>

							<c:when test="${memberId != null}">
								<li class="nav-item">
								  <span class="navbar-welcome"">
								    ようこそ、${sessionScope.memberFirstName} ${sessionScope.memberLastName}さん！
								  </span>
								</li>
								
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.request.contextPath}/CustomerCartController?action=list">
									  カート[${cartItem.amount}]
									</a>
								</li>
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.request.contextPath}/CustomerItemListForm">商品一覧</a
								</li>
								<li class="nav-item">
									<a class="nav-link" href="/customer_orders">注文履歴</a
								</li>
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.request.contextPath}/CustomerMypageForm">マイページ</a>
								</li>
								<li class="nav-item">
									<a class="nav-link" href="${pageContext.request.contextPath}/logout">ログアウト</a>
								</li>

							</c:when>

							<c:otherwise>
								<li class="nav-item"><a class="nav-link"
									href="${pageContext.request.contextPath}/LogoutController">ログイン</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</nav>
	</header>
</body>
</html>