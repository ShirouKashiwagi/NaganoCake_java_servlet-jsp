<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>ショッピングカート</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartList.css">
</head>
<body class="cart-bg">

    <%@ include file="/WEB-INF/views/common/application.jsp"%>

    <div class="cart-container fade-in">
        <h2 class="cart-title">ショッピングカート</h2>

        <c:if test="${empty cartList}">
            <p class="empty">カートは空です。</p>
        </c:if>

        <c:if test="${not empty cartList}">
            <div class="cart-card">

                <table class="cart-table">
                    <tr>
                        <th>商品名</th>
                        <th>単価</th>
                        <th>数量</th>
                        <th>小計</th>
                        <th>操作</th>
                    </tr>

                    <c:forEach var="ci" items="${cartList}">
                        <tr>
                            <td>${ci.name}</td>
                            <td><fmt:formatNumber value="${ci.price}" type="number" /></td>

                            <td>
                                <form action="CartUpdateServlet" method="post" class="inline-form">
                                    <input type="hidden" name="cartItemId" value="${ci.id}">
                                    <input type="number" name="amount" value="${ci.amount}" min="1" class="amount-input">
                                    <button type="submit" class="btn btn-update">変更</button>
                                </form>
                            </td>

                            <td><fmt:formatNumber value="${ci.price * ci.amount}" type="number" /></td>

                            <td>
                                <form action="CartDeleteServlet" method="post" class="inline-form">
                                    <input type="hidden" name="cartItemId" value="${ci.id}">
                                    <button type="submit" class="btn btn-delete">削除</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="cart-actions">
                    <form action="CartDeleteAllServlet" method="post">
                        <button type="submit" class="btn btn-danger">カートを空にする</button>
                    </form>

                    <form action="OrderConfirmServlet" method="get">
                        <button type="submit" class="btn btn-primary">情報入力に進む</button>
                    </form>
                </div>
            </div>
        </c:if>
    </div>

	<!-- 共通レイアウト -->
    <%@ include file="/WEB-INF/views/common/footer.jsp"%>

    <script src="${pageContext.request.contextPath}/js/cartList.js"></script>
</body>
