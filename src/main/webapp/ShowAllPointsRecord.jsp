<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.PointsRecordBean"%>
<%@ page import="bistro.bean.PointPrizesBean"%>
<%@ page import="bistro.bean.MembersDetailBean"%>
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

#save{
    background-color: #0071e3;
    border-radius: 10px;
    color: white;
    width: auto;
    border: none;
    font-size: 1rem;	
    padding: 0.3rem 1.35rem;
}

#save:hover{
	background-color: #2894FF;
}

#reset{
    border-radius: 10px;
    background-color: white;
    color: #0071e3;
    border: 1px solid #0071e3;
    height: auto;
    width: auto;
    font-size: 1rem;
    padding: 0.25rem 1.25rem;
}

#reset:hover{
	text-decoration: none;
}

#buttonbox{
	margin-top: 0.5rem
}
</style>


</head>

<body>
	<!-- datatable  -->
	<script src="${pageContext.request.contextPath}/jquery/jquery-3.7.1.js"></script>
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>


	<div id="page">
		<%@ include file="nav.jsp" %>

		<div id="right">
			<header>
				<button id="logout"><a href="login.jsp"><i class="fa-solid fa-arrow-right-from-bracket"></i> Log Out</a></button>
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
									<th>兌換ID</th>
									<th>會員ID</th>
									<th>會員名稱</th>
									<th>獎品ID</th>
									<th>獎品名稱</th>
									<th>兌換所用積分</th>
									<th>兌換日期</th>
									<th>編輯動作</th>
								</tr>
							</thead>
							<tbody id="event-list">
								<%
								List<PointsRecordBean> recordList = (ArrayList<PointsRecordBean>) request.getAttribute("allRecord");
								if (recordList != null) {
									for (PointsRecordBean record : recordList) {
								%>
								<tr>
									<td><%=record.getPointsRecords_id()%></td>
									
									<% MembersDetailBean membersDetailBean = record.getMembersDetail(); %>
									<td><%=membersDetailBean.getMembers_id()%></td>
									<td><%=membersDetailBean.getMembersD_name()%></td>								
									
									<% PointPrizesBean pointPrizesBean = record.getPointPrizes(); %>
									<td><%=pointPrizesBean.getPointPrizes_id()%></td>
									<td><%=pointPrizesBean.getPointPrizes_name()%></td>
									<td><%=pointPrizesBean.getPointPrizes_points()%></td>
									<td><%=record.getRecords_date()%></td>
									<td>
										<button type="button" id="edit"
											onclick='openEditModal({
										        members_id: "<%= membersDetailBean.getMembers_id() %>",
										        prize_id: "<%= pointPrizesBean.getPointPrizes_id() %>",
										        records_date: "<%=record.getRecords_date()%>",
										        PointsRecords_id: "<%=record.getPointsRecords_id()%>"
										    })'>編輯</button>
										<form action="DeletePointsRecordServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="recordsId"
												value="<%=record.getPointsRecords_id()%>">
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

			<!--  新增兌換歷史視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增兌換歷史</h1>
					<form action="CreatePointsRecordServlet.do" method="post" id="dataForm">
						<fieldset>
							<!-- 區域1 -->
							<legend style="margin-bottom: 1rem; font-weight: bold">
								新曾兌換歷史 </legend>
							<div class="question">
								<span class="title">會員ID</span> <input type="text" id=""
									name="memberId" value="" placeholder="請輸入會員ID" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">獎品ID</span> <input type="text" id=""
									name="prizeId" value="" placeholder="請輸入獎品ID" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">兌換日期</span> <input type="date"
									id="" name="recordsDate" value="" required aria-required="true" />
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


			<!--  編輯兌換歷史視窗 -->
			<div id="addEventModal2" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯兌換歷史</h1>
					<form action="UpdatePointsRecordServlet.do" method="post" id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								編輯兌換歷史 </legend>

							<input type="hidden" id="pointsRecordsId" name="pointsRecordsId"
								value="">

							<div class="question">
								<span class="title">會員ID</span> <input type="text" id="memberId"
									name="memberId" value="" required aria-required="true" />
							</div>

							<div class="question">
								<span class="title">獎品ID</span> <input type="number"
									id="prizeId" name="prizeId" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">兌換日期</span> <input type="date"
									id="recordsDate" name="recordsDate" value="" required
									aria-required="true" />
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
      
	    function openEditModal(record) {
	        document.getElementById("addEventModal2").style.display = "block";

	        // 填充編輯表單的數據
	        document.getElementById("pointsRecordsId").value = record.PointsRecords_id;
	        document.getElementById("memberId").value = record.members_id;
	        document.getElementById("prizeId").value = record.prize_id;
	        document.getElementById("recordsDate").value = record.records_date;
	    }
	      
	      
      
      
	</script>
</body>
</html>