<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新規会員登録 - Nagano Cake</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registration.css">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>新規会員登録</h1>
            <p>Nagano Cakeのご利用には会員登録が必要です</p>
        </div>

        <form action="CustomerRegistrationComplete" method="post">
            <!-- 姓名（必須） -->
            <div class="form-row">
                <div class="form-group">
                    <label for="lastName">姓 <span class="required">*</span></label>
                    <input type="text" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="firstName">名 <span class="required">*</span></label>
                    <input type="text" id="firstName" name="firstName" required>
                </div>
            </div>

            <!-- 姓名カナ -->
            <div class="form-row">
                <div class="form-group">
                    <label for="lastNameKana">姓（カナ）</label>
                    <input type="text" id="lastNameKana" name="lastNameKana">
                </div>
                <div class="form-group">
                    <label for="firstNameKana">名（カナ）</label>
                    <input type="text" id="firstNameKana" name="firstNameKana">
                </div>
            </div>

            <!-- メールアドレス（必須） -->
            <div class="form-group">
                <label for="email">メールアドレス <span class="required">*</span></label>
                <input type="email" id="email" name="email" required>
            </div>

            <!-- パスワード（必須） -->
            <div class="form-group">
                <label for="password">パスワード <span class="required">*</span></label>
                <input type="password" id="password" name="password" required minlength="8">
            </div>

            <!-- 郵便番号 -->
            <div class="form-group">
                <label for="postalCode">郵便番号(ハイフンなし)</label>
                <input type="text" id="postalCode" name="postalCode" pattern="\d{7}" placeholder="1234567">
            </div>

            <!-- 住所 -->
            <div class="form-group">
                <label for="address">住所</label>
                <input type="text" id="address" name="address">
            </div>

            <!-- 電話番号 -->
            <div class="form-group">
                <label for="phoneNumber">電話番号</label>
                <input type="tel" id="phoneNumber" name="phoneNumber">
            </div>

            <button type="submit" class="btn-submit">会員登録</button>

            <div class="login-link">
                <p>既にアカウントをお持ちですか？ <a href="loginForm.jsp">ログイン</a></p>
            </div>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/js/registration.js"></script>
</body>
</html>