<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品一覧 | Nagano Cake</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
</head>
<body>
    <!-- 背景画像 -->
    <div class="background-overlay"></div>
    
    <main class="main">
        <div class="container">
            <!-- ページタイトル -->
            <div class="page-header">
                <h1 class="page-title">商品一覧</h1>
                <p class="page-subtitle">全 ${totalProducts} 件</p>
            </div>

            <!-- ジャンル検索 -->
            <div class="genre-filter">
                <span class="filter-label">ジャンル検索：</span>
                <a href="productList.jsp" class="filter-link ${empty param.genre ? 'active' : ''}">すべて</a>
                <c:forEach var="genre" items="${genres}">
                    <a href="productList.jsp?genre=${genre.id}" 
                       class="filter-link ${param.genre == genre.id ? 'active' : ''}">
                        ${genre.name}
                    </a>
                </c:forEach>
            </div>

            <!-- 商品一覧 -->
            <div class="product-grid">
                <c:forEach var="product" items="${products}">
                    <div class="product-card">
                        <div class="product-image">
                            <c:choose>
                                <c:when test="${not empty product.imageId}">
                                    <img src="${pageContext.request.contextPath}/images/products/${product.imageId}" 
                                         alt="${product.name}" class="product-img">
                                </c:when>
                                <c:otherwise>
                                    <div class="no-image">
                                        <i class="cake-icon">🎂</i>
                                        <span>画像準備中</span>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${product.isNew}">
                                <span class="new-badge">NEW</span>
                            </c:if>
                        </div>
                        
                        <div class="product-info">
                            <h3 class="product-name">${product.name}</h3>
                            <p class="product-price">¥${product.price}</p>
                            <p class="product-description">
                                ${product.description}
                            </p>
                            
                            <div class="product-actions">
                                <form action="${pageContext.request.contextPath}/AddToCart" method="post" class="cart-form">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <div class="quantity-wrapper">
                                        <label for="quantity-${product.id}" class="quantity-label">数量：</label>
                                        <input type="number" id="quantity-${product.id}" name="quantity" 
                                               value="1" min="1" max="10" class="quantity-input">
                                    </div>
                                    <button type="submit" class="add-to-cart-btn">
                                        <i class="cart-icon">🛒</i>カートに入れる
                                    </button>
                                </form>
                                <a href="${pageContext.request.contextPath}/productDetail.jsp?id=${product.id}" class="detail-link">
                                    <i class="detail-icon">🔍</i>詳細を見る
                                </a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <!-- ページネーション -->
            <c:if test="${totalPages > 1}">
                <div class="pagination">
                    <c:if test="${currentPage > 1}">
                        <a href="productList.jsp?page=${currentPage - 1}${not empty param.genre ? '&genre=' : ''}${param.genre}" 
                           class="page-link prev-link">
                            <i class="pagination-icon">←</i>前へ
                        </a>
                    </c:if>
                    
                    <div class="page-numbers">
                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <c:choose>
                                <c:when test="${i == currentPage}">
                                    <span class="page-link active">${i}</span>
                                </c:when>
                                <c:otherwise>
                                    <a href="productList.jsp?page=${i}${not empty param.genre ? '&genre=' : ''}${param.genre}" 
                                       class="page-link">${i}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    
                    <c:if test="${currentPage < totalPages}">
                        <a href="productList.jsp?page=${currentPage + 1}${not empty param.genre ? '&genre=' : ''}${param.genre}" 
                           class="page-link next-link">
                            次へ<i class="pagination-icon">→</i>
                        </a>
                    </c:if>
                </div>
            </c:if>
            
            <!-- カートへの遷移ボタン -->
            <div class="cart-section">
                <a href="${pageContext.request.contextPath}/cart.jsp" class="go-to-cart-btn">
                    <i class="cart-icon">🛒</i>カートを確認する
                    <c:if test="${cartCount > 0}">
                        <span class="cart-badge">${cartCount}</span>
                    </c:if>
                </a>
            </div>
        </div>
    </main>
</body>
</html>