<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/itemList.css">
<title>商品一覧 - Nagano Cake</title>
</head>
<body>
	<div class="container">
		<!-- ログイン状態の表示スタブ -->
		<div class="login-status">
			<c:choose>
				<c:when test="${memberId != null}">
            ようこそ、${sessionScope.memberFirstName} ${sessionScope.memberLastName}さん！
						// ToDo: ログアウトコントローラーかjsでの処理を追加する必要あり。
            <a href="${pageContext.request.contextPath}/logout">ログアウト</a>

						// ToDo: マイページのコントローラを作成する必要あり。
						<a href="${pageContext.request.contextPath}/mypage">マイページ</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/login">ログイン</a>
				</c:otherwise>
			</c:choose>
		</div>

		<!-- 商品一覧表示スタブ -->
		<h1 class="page-title">商品一覧</h1>

		 ジャンルフィルター（シンプル版） 
		<div class="genre-filter">
<!--			<a href="${pageContext.request.contextPath}/items"-->
<!--				class="${empty param.genre ? 'active' : ''}">すべて</a>-->
<!--			<c:forEach var="genre" items="${genres}">-->
<!--				<a href="${pageContext.request.contextPath}/items?genre=${genre.id}"-->
<!--					class="${param.genre == genre.id ? 'active' : ''}">${genre.name}</a>-->
<!--			</c:forEach>-->
		</div>

		<!-- 商品一覧表示 -->
		<div class="item-list">
			<c:choose>
				<c:when test="${empty items}">
					<p>表示する商品がありません。</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${items}">
						<div class="item-card">
							<div class="item-image">
								<c:choose>
									<c:when test="${not empty item.imagePath}">
										<img src="${pageContext.request.contextPath}${item.imagePath}"
											alt="${item.name}"
											style="max-width: 100%; max-height: 150px;">
									</c:when>
									<c:otherwise>
										<div class="no-image">
											<div style="font-size: 40px;">🎂</div>
											<div>画像なし</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
							<h3 class="item-name">${item.name}</h3>
							<p class="item-price">¥${item.price}</p>
							<p>${item.introduction}</p>
							<div style="margin-top: 10px;">
								<!-- カート追加は後回し、今は詳細ページリンクのみ -->
								<a
									href="${pageContext.request.contextPath}/item/detail?id=${item.id}"
									style="background: #4CAF50; color: white; padding: 8px 12px; text-decoration: none; border-radius: 3px;">
									詳細を見る </a>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>