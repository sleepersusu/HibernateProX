<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.PointPrizesBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>獎品管理 - DINEEASE餐廳管理系統</title>

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

#save {
	background-color: #0071e3;
	border-radius: 10px;
	color: white;
	width: auto;
	border: none;
	font-size: 1rem;
	padding: 0.3rem 1.35rem;
}

#save:hover {
	background-color: #2894FF;
}

#reset {
	border-radius: 10px;
	background-color: white;
	color: #0071e3;
	border: 1px solid #0071e3;
	height: auto;
	width: auto;
	font-size: 1rem;
	padding: 0.25rem 1.25rem;
}

#reset:hover {
	text-decoration: none;
}

#buttonbox {
	margin-top: 0.5rem
}
</style>


</head>

<body>
	<!-- datatable  -->
	<script src="${pageContext.request.contextPath}/jquery/jquery-3.7.1.js"></script>
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>


	<div id="page">
		<%@ include file="nav.jsp"%>

		<div id="right">
			<header>
				<button id="logout">
					<a href="login.jsp"><i
						class="fa-solid fa-arrow-right-from-bracket"></i> Log Out</a>
				</button>
			</header>

			<main>
				<h2>獎品兌換歷史</h2>
				<section id="workspace">
					<div>
						<button type="submit" class="dataButton" id="addData">
							新增兌換紀錄</button>
					</div>
				</section>



				<section>
					<div>
						<!--套件功能 class="display" 務必保留 如果資料需要跳行顯示可刪除nowrap-->
						<table id="table" class="display">
							<thead>
								<tr>
									<th>獎品ID</th>
									<th>獎品名稱</th>
									<th>需要點數</th>
									<th>獎品描述</th>
									<th>有效日期</th>
									<th>狀態</th>
									<!-- 新增的狀態欄位 -->
									<th>編輯動作</th>
								</tr>
							</thead>
							<tbody id="event-list">
								<%
								List<PointPrizesBean> PointPrizesList = (ArrayList<PointPrizesBean>) request.getAttribute("allPointPrizes");
								if (PointPrizesList != null) {
									for (PointPrizesBean PointPrizes : PointPrizesList) {
								%>
								<tr>
									<td><%=PointPrizes.getPointPrizes_id()%></td>
									<td><%=PointPrizes.getPointPrizes_name()%></td>
									<td><%=PointPrizes.getPointPrizes_points()%></td>
									<td><%=PointPrizes.getPointPrizes_description()%></td>
									<td><%=PointPrizes.getPointPrizes_expiration()%></td>
									<td>
										<%
										if ("已過期".equals(PointPrizes.getRewardsStatus())) {
										%> <span
										style="color: red;"><%=PointPrizes.getRewardsStatus()%></span>
										<%
										} else {
										%> <span style="color: blue;"><%=PointPrizes.getRewardsStatus()%></span>
										<%
										}
										%>
									</td>
									<td>
										<button type="button" id="edit"
										    onclick='openEditModal({
										        RewardsId: "<%=PointPrizes.getPointPrizes_id()%>",
										        RewardsName: "<%=PointPrizes.getPointPrizes_name()%>",
										        RewardsPoints: "<%=PointPrizes.getPointPrizes_points()%>",
										        RewardsDescription: "<%=PointPrizes.getPointPrizes_description()%>",
										        RewardsDate: "<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(PointPrizes.getPointPrizes_expiration()) %>"
										    })'>編輯</button>
										<form action="DeletePointPrizesServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="recordsId"
												value="<%=PointPrizes.getPointPrizes_id()%>">
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
									<td colspan="6">沒有活動資料</td>
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
					<h1>新增兌換商品</h1>
					<form action="CreatePointPrizesServlet.do" method="post" id="dataForm">
						<fieldset>
							<!-- 區域1 -->
							<legend style="margin-bottom: 1rem; font-weight: bold">
								兌換商品資訊 </legend>
							<div class="question">
								<span class="title">獎品名稱</span> <input type="text" id=""
									name="rewardsName" value="" placeholder="請輸入獎品名稱" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">需求點數</span> <input type="text" id=""
									name="rewardsPoints" value="" placeholder="請輸入需要點數" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">獎品描述</span> <input type="text" id=""
									name="rewardsDescription" value="" placeholder="請輸入獎品描述"
									required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">有效日期</span> <input type="date"
									id="" name="rewardsDate" value="" required aria-required="true" />
							</div>
						</fieldset>
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
					<h1>編輯兌換獎品</h1>
					<form action="UpdatePointPrizesServlet.do" method="post" id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								編輯兌換獎品 </legend>

							<input type="hidden" name="rewardsId" value="" id="rewardsId">

							<div class="question">
								<span class="title">獎品名稱</span> <input type="text"
									id="rewardsName" name="rewardsName" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">需求點數</span> <input type="number"
									id="rewardsPoints" name="rewardsPoints" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">獎品描述</span> <input type="text"
									id="rewardsDescription" name="rewardsDescription" value=""
									required aria-required="true" />
							</div>

			                <div class="question">
			                    <span class="title">有效日期</span> 
			                    <input type="date" id="rewardsDate" name="rewardsDate" value="" required aria-required="true" />
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
      
	    function openEditModal(rewards) {
	        document.getElementById("addEventModal2").style.display = "block";
	        
	        // 使用 rewards 物件的屬性來獲取值
	        document.getElementById("rewardsId").value = rewards.RewardsId;
	        document.getElementById("rewardsName").value = rewards.RewardsName;
	        document.getElementById("rewardsPoints").value = rewards.RewardsPoints;
	        document.getElementById("rewardsDescription").value = rewards.RewardsDescription;
	        document.getElementById("rewardsDate").value = rewards.RewardsDate;
	    }
	
	      
	      
      
      
	</script>
</body>
</html>