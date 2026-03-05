<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>マイページ</title>
    <link rel="stylesheet" href="css/myPage.css">
</head>
<body>
<div class="container">
    <h2>マイページ</h2>
    <h3>会員情報</h3>
    <form action="updateMember" method="post">
        <div class="item">
            <label>ID：</label>
            <span class="readonly">${member.id}</span>
        </div>

        <div class="item">
            <label>メールアドレス：</label>
            <input type="email" name="email" value="${member.email}" required>
        </div>

        <div class="item">
            <label>姓：</label>
            <input type="text" name="lastName" value="${member.lastName}" required>
        </div>

        <div class="item">
            <label>名：</label>
            <input type="text" name="firstName" value="${member.firstName}" required>
        </div>

        <div class="item">
            <label>姓（カナ）：</label>
            <input type="text" name="lastNameKana" value="${member.lastNameKana}">
        </div>

        <div class="item">
            <label>名（カナ）：</label>
            <input type="text" name="firstNameKana" value="${member.firstNameKana}">
        </div>

        <div class="item">
            <label>郵便番号：</label>
            <input type="text" name="postalCode" value="${member.postalCode}">
        </div>

        <div class="item">
            <label>住所：</label>
            <input type="text" name="address" value="${member.address}">
        </div>

        <div class="item">
            <label>電話番号：</label>
            <input type="text" name="phoneNumber" value="${member.phoneNumber}">
        </div>

        <div class="item">
            <label>ステータス：</label>
            <span class="readonly">
                <c:choose>
                    <c:when test="${member.isActive}">有効</c:when>
                    <c:otherwise>退会済み</c:otherwise>
                </c:choose>
            </span>
        </div>

        <div class="item">
            <label>登録日時：</label>
            <span class="readonly">${member.createdAt}</span>
        </div>

        <div class="item">
            <label>更新日時：</label>
            <span class="readonly">${member.updatedAt}</span>
        </div>

        <div class="item">
            <button type="submit" class="submit-btn">更新する</button>
        </div>
    </form>
</div>
</body>
</html>
