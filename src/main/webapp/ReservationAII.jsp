<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.Reservation"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Events</title>

<link rel="icon"
	href="${pageContext.request.contextPath}/images/favicon.ico" />

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100..900&display=swap"
	rel="stylesheet" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />

<link rel="stylesheet"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/demo.css" />
<style>
body {
	font-family: "Noto Sans TC", sans-serif;
}

#edit, #delete {
	padding: 0.3rem 0.75rem;
	cursor: pointer;
	color: white;
	border: none;
	border-radius: 10px
}

#edit {
	background-color: #0071e3;
	margin-bottom: 0.3rem;
}

#edit:hover {
	background-color: #2894FF;
}

#delete {
	background-color: #CE0000;
}

#delete:hover {
	background-color: #FF5151;
}

#dt-search-0 {
	margin-right: 2rem
}

#dt-search-0:focus {
	outline: none;
}

#addData {
	padding: 0.5rem 1.25rem !important;
	font-size: 1rem !important;
	width: auto;
	height: auto;
	color: white;
	background-color: #0071e3;
	border: none;
	border-radius: 0;
	font-weight: normal;
}

#addData:hover {
	background-color: #2894FF;
}

.modal-content {
	min-width: 400px;
	overflow: auto;
}

body .modal .modal-content form textarea {
	width: 350px
}
</style>


</head>

<body>
	<!-- datatable  -->
	<script src="${pageContext.request.contextPath}/jquery/jquery-3.7.1.js"></script>
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>


	<div id="page">
		<div id="left">
			<nav id="sidebar">
				<div id="logo">
					<img src="${pageContext.request.contextPath}/images/logo.jpg"
						alt="商店logo" />
				</div>
				<div id="sidebox">


					<div id="menubox">
						<ul class="menu">
							<li class="menuitem"><a href="./index.html"> <i
									class="fa-solid fa-house" style="color: #ffffff"></i> 首頁
							</a></li>


							<li class="menuitem"><a href="Menu.html"> <i
									class="fa-solid fa-whiskey-glass"></i> 商品管理
							</a></li>

							<li class="menuitem"><a href="#"> <i
									class="fa-solid fa-address-card"></i> 會員管理
							</a>
								<ul class="dropdown">
									<li><a href="Member_info.html"> 會員資訊 </a></li>
									<li><a href=""> 會員優惠 </a></li>
								</ul></li>

							<li class="menuitem"><a href="Reservations.html"> <i
									class="fa-regular fa-calendar-check"></i> 訂位管理
							</a>
								<ul class="dropdown">
									<li>
										<!--下拉選項內容 li裡放a標籤-->
									</li>
								</ul></li>

							<li class="menuitem"><a href="Orders.html"> <i
									class="fa-regular fa-clipboard"></i> 訂單管理
							</a> <!--下拉選項內容開始-->
								<ul class="dropdown">
									<li>
										<!--下拉選項內容 li裡放a標籤-->
									</li>
								</ul></li>

							<li class="menuitem"><a
								href="/Test/TestShowAllCampaignServlet.do"> <i
									class="fa-regular fa-face-laugh-squint"></i> 活動管理
							</a> <!--下拉選項內容開始-->
								<ul class="dropdown">
									<li>
										<!--下拉選項內容 li裡放a標籤-->
									<li><a href="/Test/TestShowAllCampaignServlet.do">
											活動資訊 </a></li>
									<li><a href="/Test/ShowAllPrizeServlet.do"> 獎品資訊 </a></li>
								</ul></li>

							<li class="menuitem"><a href="Supply.html"> <i
									class="fa-solid fa-cart-flatbed"></i> 採購管理
							</a> <!--下拉選項內容開始-->
								<ul class="dropdown">
									<li>
										<!--下拉選項內容 li裡放a標籤-->
									</li>
								</ul></li>

							<li class="menuitem"><a href="Reviews.html"> <i
									class="fa-solid fa-comment"></i> 評論管理
							</a> <!--下拉選項內容開始-->
								<ul class="dropdown">
									<li>
										<!--下拉選項內容 li裡放a標籤-->
									</li>
								</ul></li>

							<li class="menuitem"><a href="Website.html"> <i
									class="fa-solid fa-desktop"></i> 網站管理
							</a> <!--下拉選項內容開始-->
								<ul class="dropdown">
									<li>
										<!--下拉選項內容 li裡放a標籤-->
									</li>
								</ul></li>

							<li class="menuitem"><a href="Users.html"> <i
									class="fa-solid fa-user"></i> 員工系統
							</a> <!--下拉選項內容開始-->
								<ul class="dropdown">
									<li>
										<!--下拉選項內容 li裡放a標籤-->
									</li>
								</ul></li>

						</ul>
						<!-- menu 結束-->
					</div>
					<!-- menubox 結束-->
				</div>
				<!-- sidebox 結束-->
			</nav>
			<!-- sidebar 側邊欄 結束-->

		</div>
		<!-- left 結束-->

		<div id="right">
			<header>
				<button>登出</button>
			</header>

			<main>
				<h2>獎品管理</h2>
				<section id="workspace">
					<div>
						<button type="submit" class="dataButton" id="addData">
							新增訂位</button>
					</div>
				</section>



				<section>
					<div>
						<!--套件功能 class="display" 務必保留 如果資料需要跳行顯示可刪除nowrap-->
						<table id="table" class="display">
							<thead>
								<tr>
									<th>id</th>
									<th>會員</th>
									<th>姓名</th>
									<th>性別</th>
									<th>電話</th>
									<th>訂位日期</th>
									<th>訂位人數</th>
									<th>桌號</th>									
									<th>訂位狀況</th>
									<th>備註</th>
									<th>建立時間</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="event-list">
								<%
								List<Reservation> prizeList = (List<Reservation>) request.getAttribute("allReservation");
								if (prizeList != null) {
									for (Reservation prize : prizeList) {
										String gender=null;
										
										if(prize.getCustomerGender()){
											gender="男";
										}else{
											gender="女";
										}
										
										
								%>
								<tr>
									<td><%=prize.getReservationId()%></td>
									<td><%=prize.getMemberId()%></td>
									<td><%=prize.getCustomerName()%></td>
									<td><%=gender%></td>
									<td><%=prize.getContactPhone()%></td>
									<td><%=prize.getReservationDateTime()%></td>
									<td><%=prize.getNumberPeople()%></td>
									<td><%=prize.getSeatsId()%></td>
									<td><%=prize.getReservationStatus()%></td>
									<td><%=prize.getNotes()%></td>
									<td><%=prize.getCreatedAt()%></td>
									<td>
										<button type="button" id="edit"
											onclick='openEditModal({
												reservationId: "<%=prize.getReservationId()%>",         
									            CustomerName: "<%=prize.getCustomerName()%>",
									            CustomerGender: "<%=prize.getCustomerGender()%>",
									            ContactPhone: "<%=prize.getContactPhone()%>",
									            ReservationDateTime: "<%=prize.getReservationDateTime()%>",
									            NumberPeople: "<%=prize.getNumberPeople()%>",
									            SeatsId: "<%=prize.getSeatsId()%>",									          
									            ReservationStatus: "<%=prize.getReservationStatus()%>",
									            Notes: "<%=prize.getNotes()%>",
									            CreatedAt: "<%=prize.getCreatedAt()%>",
									        })'>編輯</button>
										<form action="ReservationDeleteServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="reservationsId"
												value="<%=prize.getReservationId()%>">
											<button type="submit" id="delete"
												onclick="return confirm('確定要刪除嗎？');">刪除</button>
										</form>
									</td>
								</tr>
								<%
								}
								} else {
								%>
								<tr>
									<td colspan="6">沒有資料</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</section>
			</main>

			<!--  新增獎品視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增訂位</h1>
					<form action="ReservationInsertServlet.do" method="post"
						id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								訂位資訊 </legend>

							<div class="question">
								<span class="title">姓名</span> <input type="text" id=""
									name="customerName" value="" placeholder="請輸入姓名" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">性別</span> <input type="radio" id="male"
									name="customerGender" value=1 required> <label
									for="male">男</label> <input type="radio" id="female"
									name="customerGender" value=0 required> <label
									for="female">女</label>
							</div>

							

							<div class="question">
								<span class="title">電話</span> <input type="tel" id=""
									name="contactPhone" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">人數</span> <input type="number" id=""
									name="numberPeople" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">時間日期</span> <input type="datetime-local"
									id="" name="reservationDateTime" value="" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">桌號</span> <input type="number" id=""
									name="seatsId" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">備註</span> <input type="text" id=""
									name="notes" value="" required aria-required="true" />
							</div>
						</fieldset>
						<!-- 區域1 -->

						<!---------------------------底下為按鈕區---------------------------------------------- -->
						<div id="buttonbox">
							<button type="submit" id="save" class="formbutton">儲存</button>
							<button type="reset" id="reset" class="formbutton">重設</button>
						</div>
					</form>
				</div>
				<!-- modal-content -->
			</div>


			<!--  編輯獎品視窗 -->
			<div id="addEventModal2" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯訂位</h1>
					<form action="ReservationUpdateServlet.do" method="post" id="dataForm">
						<fieldset>
							<!-- 區域1 -->
							<input type="text" name="reservationId" id="reservationId" value="">
							
							<div class="question">
								<span class="title">姓名</span> <input type="text" id="customerName"
									name="customerName" value="" placeholder="請輸入姓名" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">性別</span> <input type="radio" id="male1"
									name="customerGender" value=1 required> <label
									for="male">男</label> <input type="radio" id="female1"
									name="customerGender" value=0 required> <label
									for="female">女</label>
							</div>

							<div class="question">
								<span class="title">電話</span> <input type="tel" id="contactPhone"
									name="contactPhone" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">人數</span> <input type="number" id="numberPeople"
									name="numberPeople" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">時間日期</span> <input type="datetime-local"
									id="reservationDateTime" name="reservationDateTime" value="" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">桌號</span> <input type="number" id="seatsId"
									name="seatsId" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">訂位狀況</span> <input type="text" id="reservationStatus"
									name="reservationStatus" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">備註</span> <input type="text" id="notes"
									name="notes" value="" required aria-required="true" />
							</div>
						</fieldset>
						<!-- 區域1 -->

						<!---------------------------底下為按鈕區---------------------------------------------- -->
						<div id="buttonbox">
							<button type="submit" id="save" class="formbutton">儲存</button>
							<button type="reset" id="reset" class="formbutton">重設</button>
						</div>
					</form>
				</div>
				<!-- modal-content -->
			</div>
			<footer> &copy; Made by TeamWork </footer>
		</div>
	</div>



	<script>
		// jquery寫法 start
		$("#table").DataTable({
			scrollX : "90%",
			scrollY : "500px",
		})
		// jquery寫法 end
		
		document.getElementById("addData").addEventListener("click", () => {
        	document.getElementById("addEventModal").style.display = "block";
     	 });

		document.querySelectorAll(".close").forEach(closeButton => {
		    closeButton.addEventListener("click", () => {
		        document.getElementById("addEventModal").style.display = "none";
		        document.getElementById("addEventModal2").style.display = "none";
		    });
		});

	      window.onclick = function (event) {
	        const modal = document.getElementById("addEventModal");
	        const modal2 = document.getElementById("addEventModal2");
	        if (event.target == modal || event.target == modal2) {
	          document.getElementById("addEventModal").style.display = "none";
	          document.getElementById("addEventModal2").style.display = "none";
	        }
	      };
	      
	      function openEditModal(prize) {
	    	  
	    	document.getElementById("addEventModal2").style.display = "block";
	    	document.getElementById("reservationId").value = prize.reservationId;
	    	document.getElementById("customerName").value = prize.CustomerName; 
   			if(prize.CustomerGender=='true'){ 
   				document.getElementById("male1").checked=true;
   			}else{
   				document.getElementById("female1").checked=true;
   			} 
    	    document.getElementById("contactPhone").value = prize.ContactPhone; 
    	    document.getElementById("numberPeople").value = prize.NumberPeople; 
    	    document.getElementById("reservationDateTime").value = prize.ReservationDateTime; 
    	    document.getElementById("seatsId").value = prize.SeatsId;     	   
    	    document.getElementById("reservationStatus").value = prize.ReservationStatus; 
    	    document.getElementById("notes").value = prize.Notes; 
    	    consloe.log(prize);
    	    }	      
	             
         
       
      
      
	</script>
</body>
</html>