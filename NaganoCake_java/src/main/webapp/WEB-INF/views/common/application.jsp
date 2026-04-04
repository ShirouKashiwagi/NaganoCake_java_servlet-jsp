<!DOCTYPE html>
<html>
  <head>
    <title>NaganoCake</title>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/application.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  </head>
  <body>
   <header class= "shadow">
    <nav class="navbar navbar-expand-lg navbar-light">
     <div class= "container">
      <a class="navbar-brand" href="/" style="color: #eee;"><%= image_tag 'NaganoCale.png', class: "logo" %></a>
     <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
     </button>
     <div class="collapse navbar-collapse" id="navbarNavDropdown">

      <ul class= "navbar-nav ml-auto">

        <choice>
          <when test="${memberId != null}">
            <p>ようこそ、${sessionScope.memberFirstName} ${sessionScope.memberLastName}さん！</p>
            <li class="nav-item">
              <form action="${pageContext.request.contextPath}/CustomerCartController?action=list" method="post" class="cart-form">
                <button type="submit" class="add-cart-btn">カート[${cartItem.amount}]</button>
              </form>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/customer_items">商品一覧</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="/customer_orders">注文履歴</a>
            </li>
            <li class="nav-item">
              <a href="${pageContext.request.contextPath}/CustomerMypageForm">マイページ</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/logout">ログアウト</a>
            </li>
          </when>
          <otherwise>
            <a href="${pageContext.request.contextPath}/CustomerLoginForm">ログイン</a>
          </otherwise>
        </choice>

      </ul>
     </div>
     </div>
    </nav>
 </header>
 <%= yield %>
 <footer>
 </footer>
  </body>
</html>