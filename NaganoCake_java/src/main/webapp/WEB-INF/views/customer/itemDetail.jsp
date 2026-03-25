<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    com.naganocake.model.Item item = (com.naganocake.model.Item) request.getAttribute("item");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>${item.name} | 商品詳細</title>
    <link rel="stylesheet" href="./css/item-detail.css">
    <script src="./js/item-detail.js" defer></script>
</head>
<body>

<div class="item-detail-container">

    <div class="item-image">
        <c:choose>
            <c:when test="${not empty item.imagePath}">
                <img src="${pageContext.request.contextPath}/images/${item.imagePath}" alt="${item.name}">
            </c:when>
            <c:otherwise>
                <img src="${pageContext.request.contextPath}/images/no-image.png" alt="画像なし">
            </c:otherwise>
        </c:choose>
    </div>

    <div class="item-info">
        <h1 class="item-name">${item.name}</h1>

        <p class="item-price">
            ¥<fmt:formatNumber value="${item.price}" pattern="#,###" />
        </p>

        <p class="item-introduction">${item.introduction}</p>

        <p class="item-status">
            <c:choose>
                <c:when test="${item.active}">
                    <span class="active">販売中</span>
                </c:when>
                <c:otherwise>
                    <span class="inactive">販売停止中</span>
                </c:otherwise>
            </c:choose>
        </p>

        <form action="${pageContext.request.contextPath}/cart/add" method="post" class="cart-form">
            <input type="hidden" name="itemId" value="${item.id}">
            <label for="quantity">数量：</label>
            <select name="quantity" id="quantity">
                <c:forEach var="i" begin="1" end="10">
                    <option value="${i}">${i}</option>
                </c:forEach>
            </select>

            <button type="submit" class="add-cart-btn">カートに追加</button>
        </form>

    </div>

</div>

</body>
</html>
