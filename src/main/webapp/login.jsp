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
	href="${pageContext.request.contextPath}/styles/login.css" />
</head>
<body>
	<script>	
        window.onload = function() { 
            const options = {
                strings: [
                    "一鍵登入，簡化餐廳經營，讓美味更輕鬆！",
                    "一鍵登入，簡化餐廳經營，讓服務更流暢！",
                    "一鍵登入，簡化餐廳經營，讓顧客更滿意！"
                ],
                typeSpeed: 50,      
                backSpeed: 50,      
                backDelay: 1000,    
                startDelay: 500,    
                loop: true,
                showCursor: false
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
			<form action="LoginServlet.do">
				<h1>登入</h1>
				<div class="input-box">
					<input type="text" name="userName" id="name" placeholder="使用者帳號" required/>
					<i class="bx bxs-user"></i>
				</div>
				<div class="input-box">
					<input type="password" name="userPassword" id="password"
						placeholder="密碼" required /> <i class="fa-solid fa-eye" id="lock" style="cursor: pointer"></i>
				</div>
				<div class="remember-forget">
					<label for=""><input type="checkbox" />記住密碼</label> <a href="#">忘記密碼</a>
				</div>
				<button type="submit" class="btn">登入</button>
				<div class="register">
					<p>
						還沒有帳號？<a href="Register.jsp">點擊註冊</a>
					</p>
				</div>
			</form>
		</div>
	</div>
	<script>
	document.getElementById("lock").addEventListener("click", () => {
	    let input = document.getElementById("password");
	    let iTag = document.getElementById("lock");

	    if (input.type === "password" && iTag.classList.contains("fa-eye")) {
	        input.type = "text";
	        iTag.classList.remove("fa-eye");
	        iTag.classList.add("fa-eye-slash");
	    } else {
	        input.type = "password";
	        iTag.classList.remove("fa-eye-slash");
	        iTag.classList.add("fa-eye");
	    }
	});
	</script>
</body>
</html>
