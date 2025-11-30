<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./css/registrarion.css">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>新規会員登録</title>
	<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>

	<div class="wrapper">
		<form action="RegistrationsController" method="post">
		
			<h1>Login</h1>
			<div class="input-box">
				<input type="text" placeholder="Username" name="username" required>
				<i class='bx bxs-user'></i>
			</div>
			<div class="input-box">
				<input type="password" placeholder="password" name="password" required>
				<i class='bx bxs-lock-alt' ></i>
			</div>
			
			<div class="remember-forgot">
				<label><input type="checkbox"> Remember me</label>
				<a href1="#">Forgot passwoed?</a>
			</div>
			
			<button type="submit" value="送信" class="btn">Login</button>
			
			<div class="register-link">
				<p>Don't have an accout? <a href="#"></a>Register</p>
			</div>
			
		</form>
	</div>
</body>
</html>