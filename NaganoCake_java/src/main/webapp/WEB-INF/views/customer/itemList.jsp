<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品一覧 - Nagano Cake</title>
    <style>
        /* 最低限のスタイル */
        body { font-family: Arial, sans-serif; margin: 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        .page-title { color: #333; border-bottom: 2px solid #ff6b8b; padding-bottom: 10px; }
        .item-list { display: grid; grid-template-columns: repeat(3, 1fr); gap: 20px; margin-top: 20px; }
        .item-card { border: 1px solid #ddd; padding: 15px; border-radius: 5px; }
        .item-name { font-size: 18px; font-weight: bold; margin: 10px 0; }
        .item-price { color: #e60000; font-size: 16px; font-weight: bold; }
        .item-image { 
            height: 150px; 
            background-color: #f9f9f9; 
            display: flex; 
            align-items: center; 
            justify-content: center; 
            color: #888;
        }
        .no-image { text-align: center; }
        .genre-filter { margin: 20px 0; }
        .genre-filter a { 
            margin-right: 10px; 
            padding: 5px 10px; 
            background: #f0f0f0; 
            border-radius: 3px; 
            text-decoration: none; 
        }
        .genre-filter a.active { background: #ff6b8b; color: white; }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="page-title">商品一覧</h1>
        
        <!-- ジャンルフィルター（シンプル版） -->
        <div class="genre-filter">
            <a href="${pageContext.request.contextPath}/items" class="${empty param.genre ? 'active' : ''}">すべて</a>
            <c:forEach var="genre" items="${genres}">
                <a href="${pageContext.request.contextPath}/items?genre=${genre.id}" 
                   class="${param.genre == genre.id ? 'active' : ''}">${genre.name}</a>
            </c:forEach>
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
                                             alt="${item.name}" style="max-width:100%; max-height:150px;">
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
                                <a href="${pageContext.request.contextPath}/item/detail?id=${item.id}" 
                                   style="background: #4CAF50; color: white; padding: 8px 12px; text-decoration: none; border-radius: 3px;">
                                    詳細を見る
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>