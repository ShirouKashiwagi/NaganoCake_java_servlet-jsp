<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>注文内容の確認 | NaganoCake</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderConfirm.css">
</head>

<body class="order-confirm-page">

	<!--共通ヘッダ-->
	<%@ include file="/WEB-INF/views/common/application.jsp" %>

	<div class="wrapper">
	
	    <h1>注文内容の確認</h1>
	
	    <!-- 商品一覧 -->
	    <div class="section-title">購入商品一覧</div>
	
	    <table class="item-table">
	        <tr>
	            <th>商品名</th>
	            <th>単価</th>
	            <th>数量</th>
	            <th>小計</th>
	        </tr>
	
	        <!-- カート一覧 -->
	        <c:forEach var="ci" items="${cartList}">
	            <tr>
	                <td>${ci.name}</td>
	                <td>${ci.price}円</td>
	                <td>${ci.amount}</td>
	                <td>${ci.price * ci.amount}円</td>
	            </tr>
	        </c:forEach>
	
	        <!-- 合計 -->
	        <tr>
	            <th colspan="3">商品合計</th>
	            <td>${totalPrice}円</td>
	        </tr>
	        <tr>
	            <th colspan="3">送料</th>
	            <td>${shippingFee}円</td>
	        </tr>
	        <tr>
	            <th colspan="3">お支払い金額合計</th>
	            <td>${totalAmount + shippingFee}円</td>
	        </tr>
	    </table>
	
	    <!-- 支払い方法 -->
	    <div class="section-title">支払い方法</div>
	
	    <form action="${pageContext.request.contextPath}/customer/order" method="post">
	    	<input type="hidden" name="action" value="add">
	
	        <div class="radio-group">
	            <label><input type="radio" name="payment_method" value="クレジットカード" checked> クレジットカード</label>
	            <label><input type="radio" name="payment_method" value="銀行振込"> 銀行振込</label>
	        </div>
	
	        <!-- お届け先 -->
	        <div class="section-title">お届け先情報</div>
	
	        <div class="input-box">
	            <input type="text" name="postal_code" placeholder="郵便番号" value="${member.postalCode}" }>
	        </div>
	
	        <div class="input-box">
	            <input type="text" name="address" placeholder="住所" value="${member.address}">
	        </div>
	
	        <!-- hidden -->
	        <input type="hidden" name="shipping_fee" value="${shippingFee}">
	        <input type="hidden" name="total_amount" value="${totalAmount + shippingFee}">
	        <input type="hidden" name="action" value="add">
	
	        <button type="submit" class="btn" id="orderBtn">注文を確定する</button>
	    </form>
	
	</div>
		
		<%-- 共通レイアウト --%>
		<%@ include file="/WEB-INF/views/common/footer.jsp"%>
		
	<script src="../js/order_confirm.js"></script>
</body>
</html>
