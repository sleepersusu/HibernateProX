<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.CampaignBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>活動管理 - DINEEASE餐廳管理系統</title>

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
	min-width: 700px;
	height: 700px;
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
    padding: 0.4rem 2rem;
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
    padding: 0.3rem 2rem;
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
				<h2>活動管理</h2>
				<section id="workspace">
					<div>
						<button type="submit" class="dataButton" id="addData">
							新增活動</button>
					</div>
				</section>



				<section>
					<div>
						<!--套件功能 class="display" 務必保留 如果資料需要跳行顯示可刪除nowrap-->
						<table id="table" class="display">
							<thead>
								<tr>
									<th>活動ID</th>
									<th>活動名稱</th>
									<th>活動內容</th>
									<th>活動類別</th>
									<th>開始日期</th>
									<th>結束日期</th>
									<th>備註</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="event-list">
								<%
								List<CampaignBean> campaignList = (List<CampaignBean>) request.getAttribute("allCampaign");
								if (campaignList != null) {
									for (CampaignBean campaign : campaignList) {
								%>
								<tr>
									<td><%=campaign.getCampaign_id()%></td>
									<td><%=campaign.getCampaign_title()%></td>
									<td><%=campaign.getCampaign_description()%></td>
									<td><%=campaign.getCampaign_type()%></td>
									<td><%=campaign.getCampaign_start_date()%></td>
									<td><%=campaign.getEnd_date()%></td>
									<td><%=campaign.getNote()%></td>
									<td>
										<button type="button" id="edit"
											onclick='openEditModal({
									            id: "<%=campaign.getCampaign_id()%>",
									            title: "<%=campaign.getCampaign_title()%>",
									            description: "<%=campaign.getCampaign_description()%>",
									            type: "<%=campaign.getCampaign_type()%>",
									            startDate: "<%=campaign.getCampaign_start_date()%>",
									            endDate: "<%=campaign.getEnd_date()%>",
									            note: "<%=campaign.getNote()%>",
									            createAt: "<%=campaign.getCreated_at() %>"
									        })'>編輯</button>
									        
										<form action="DeleteCampaignServlet1.do" method="post"
											style="display: inline;">
											<input type="hidden" name="campaignId"
												value="<%=campaign.getCampaign_id()%>">
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

			<!--  新增活動視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增活動</h1>
					<form action="TestCreateCampaignServlet.do" method="post"
						id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								活動資訊 </legend>

							<div class="question">
								<span class="title">活動名稱</span> <input type="text" id=""
									name="campaignTitle" value="" placeholder="請輸入活動名稱" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">活動內容</span>
								<textarea name="campaignDescription" required></textarea>
							</div>

							<div class="question">
								<span class="title">活動類別</span> <input type="text" id=""
									name="campaignType" value="" placeholder="請輸入活動類別" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">開始日期</span> <input type="datetime-local"
									id="" name="campaignStartDate" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">結束日期</span> <input type="datetime-local"
									id="" name="campaignEndDate" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">備註</span> <input type="text" id=""
									name="note" value="" required aria-required="true" />
							</div>
						</fieldset>


						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								參與條件 </legend>
							<div class="question">
								<span class="title">最小訂單金額</span> <input type="text" id=""
									name="minAmount" value="" required aria-required="true" />
							</div>
							<div class="question">
								<span class="title">身分限制</span> <select name="verification"
									id="product_category">
									<option value="0" selected>選擇身分</option>
									<option value="會員限定" required aria-required="true">會員限定</option>
									<option value="所有顧客" required aria-required="true">所有顧客</option>
								</select>
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

			
			<!--  編輯活動視窗 -->
			<div id="addEventModal2" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯活動</h1>
					<form action="UpdateCampaignServlet.do" method="post"
						id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								編輯活動資訊 </legend>
							
							
							<input type="hidden" name="campaignId" id="campaignId" value="">
							<input type="hidden" name="createAt" id="createAt" value="">
							<div class="question">
								<span class="title">活動名稱</span> <input type="text" id="campaignTitle"
									name="campaignTitle" value="" placeholder="請輸入活動名稱" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">活動內容</span>
								<textarea name="campaignDescription" id="campaignDescription" required></textarea>
							</div>

							<div class="question">
								<span class="title">活動類別</span> <input type="text" id="campaignType"
									name="campaignType" value="" placeholder="請輸入活動類別" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">開始日期</span> <input type="datetime-local"
									id="campaignStartDate" name="campaignStartDate" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">結束日期</span> <input type="datetime-local"
									id="campaignEndDate" name="campaignEndDate" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">備註</span> <input type="text" id="note"
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
	      
	      function openEditModal(campaign) {
	    	document.getElementById("addEventModal2").style.display = "block";
	    	
    	    document.getElementById("campaignTitle").value = campaign.title;
    	    document.getElementById("campaignDescription").value = campaign.description;
    	    document.getElementById("campaignType").value = campaign.type;
    	    document.getElementById("campaignStartDate").value = campaign.startDate;
    	    document.getElementById("campaignEndDate").value = campaign.endDate;
    	    document.getElementById("note").value = campaign.note;
    	    document.getElementById("campaignId").value = campaign.id; 
    	    document.getElementById("createAt").value = campaign.createAt; 
    	    }	      
	      
      
      
	</script>
</body>
</html>