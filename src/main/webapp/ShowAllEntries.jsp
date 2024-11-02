<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.LotteryEntriesBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>活動參與者管理 - DINEEASE餐廳管理系統</title>

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
				<h2>參與者管理</h2>
				<section id="workspace">
					<div>
						<button type="submit" class="dataButton" id="addData">
							手動新增</button>
					</div>
				</section>



				<section>
					<div>
						<!--套件功能 class="display" 務必保留 如果資料需要跳行顯示可刪除nowrap-->
						<table id="table" class="display">
							<thead>
								<tr>
									<th>參與者ID</th>
									<th>參與者姓名</th>
									<th>訂單編號</th>
									<th>訂單金額</th>
									<th>活動名稱</th>
									<th>會員身分</th>
									<th>創建時間</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="event-list">
								<%
								List<LotteryEntriesBean> allEntries = (List<LotteryEntriesBean>) request.getAttribute("allEntries");
								if (allEntries != null) {
									for (LotteryEntriesBean entry : allEntries) {
								%>
								<tr>
									<td><%=entry.getLottery_entries_id()%></td>
									<td><%=entry.getOrderDetailsBean().getOrders().getCustomerName()%></td>
									<td><%=entry.getOrderDetailsBean().getOrders().getOrdersId()%></td>
									<td><%=entry.getOrderDetailsBean().getTotal_price()%></td>
									<td><%=entry.getCampaign().getCampaign_title()%></td>
									<%
									    Integer memberId = entry.getOrderDetailsBean().getOrders().getMembersId();
									%>
									<td style="color: <%= memberId != null ? "#00BB00" : "red" %>;">
									    <%
									    if (memberId != null) {
									        out.print("會員"); 
									    } else {
									        out.print("非會員"); 
									    }
									    %>
									</td>
									<td><%=entry.getCreated_at()%></td>
									<td>
										<form action="DeleteEntryServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="entryId"
												value="<%=entry.getLottery_entries_id()%>">
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
									<td colspan="8">沒有活動資料</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</section>
			</main>

			<!--  新增活動視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增參與者</h1>
					<form action="CreateLotteryEntryServlet.do" method="post"
						id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								參與者資訊 </legend>

							<div class="question">
								<span class="title">活動ID</span> <input type="text" id=""
									name="campaignId" value="" placeholder="請輸入活動名稱" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">訂單ID</span> <input type="text" id=""
									name="orderId" value="" placeholder="請輸入活動類別" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">備註</span> <input type="text" id=""
									name="note" value="" required aria-required="true" />
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
		

		document.querySelectorAll(".close").forEach(closeButton => {
		    closeButton.addEventListener("click", () => {
		        document.getElementById("addEventModal").style.display = "none";
		    });
		});

	      window.onclick = function (event) {
	        const modal = document.getElementById("addEventModal");
	        if (event.target == modal) {
	          document.getElementById("addEventModal").style.display = "none";
	        }
	      };      
      
	</script>
</body>
</html>