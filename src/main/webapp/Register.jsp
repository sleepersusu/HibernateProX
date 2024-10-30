<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>DINEEASE - 餐廳管理系統</title>

<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico" />
<link href="https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css"
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
	integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100..900&display=swap"
	rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/typed.js@2.0.12"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/register.css" />
</head>
<body>
	<script>
		window.onload = function() {
			const password = document.getElementById('password');
			const confirmPassword = document.getElementById('confirmpassword');

			confirmPassword.addEventListener('input', function() {
				let shield = document.getElementById("shield")
				if (password.value !== confirmPassword.value) {
					shield.style.color = "#FF0000";
				} else {
					shield.style.color = "#00DB00";
				}
			});
			const options = {
				strings : [ "一鍵登入，簡化餐廳經營，讓美味更輕鬆！", "一鍵登入，簡化餐廳經營，讓服務更流暢！",
						"一鍵登入，簡化餐廳經營，讓顧客更滿意！" ],
				typeSpeed : 50,
				backSpeed : 50,
				backDelay : 1000,
				startDelay : 500,
				loop : true,
				showCursor : false
			};
			const typed = new Typed("#typing-effect", options);
		};
	</script>
	<div class="img-container">
        <img src="${pageContext.request.contextPath}/images/logo.jpg" alt="">
      </div>
	<div class="triangle"></div>
	<div class="title">
		<div class="title-box">
			<h1>DINEEASE</h1>
			<h3>讓餐廳經營變簡單</h3>
			<h3 id="typing-effect"></h3>
		</div>
	</div>

	<div class="box">
		<div class="wrapper">
			<form action="RegisterServlet.do">
				<h1>註冊</h1>
				<div class="input-box">
					<input type="text" name="userName" id="name" placeholder="請輸入帳號"
						required autocomplete="new-password" /> <i class="bx bxs-user"></i>
				</div>
				<div class="input-box">
					<input type="password" name="userPassword" id="password"
						placeholder="密碼" required autocomplete="new-password" /> <i
						class="bx bxs-lock-alt"></i>
				</div>
				<div class="input-box">
					<input type="password" name="confirmPassword" id="confirmpassword"
						placeholder="確認密碼" required /><i class="fa-solid fa-shield-halved" id="shield"></i>
				</div>
				<div class="agree">
					<div class="agree1">
						<input type="checkbox" id="info" /><label for="info">我願意接受DINEEASE的最新優惠和推廣</label>
					</div>
					<div class="agree2">
						<input type="checkbox" id="privacy" /><label for="privacy">我同意網站<a
							href="#"> 服務條款 </a>及<a href="#"> 隱私政策 </a></label>
					</div>
				</div>
				<button type="submit" class="btn">註冊</button>
			</form>
		</div>
	</div>

</body>
</html>
