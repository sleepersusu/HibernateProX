<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.SupplyBean"%>
<%@ page import="bistro.bean.SupplyOriBean"%>
<%@ page import="bistro.bean.EmployeeBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>採購管理 - DINEEASE餐廳管理系統</title>

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
			<h2>供應商管理</h2>
				<section id="workspace">
					<div>
						<button 
							type="submit" 
							class="dataButton" 
							id="addData">
							新增進貨資料
						</button>
					</div>
				</section>

				<section>
					<div>
						<!--套件功能 class="display" 務必保留 如果資料需要跳行顯示可刪除nowrap-->
						<table id="table" class="display">
							<thead>
								<tr>
									<th>編號</th>
									<th>廠商名稱</th>
									<th>產品名稱</th>
									<th>產品數量</th>
									<th>產品價格</th>
									<th>經手人</th>
									<th>建立日期</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="event-list">
							
								<c:forEach var="item" items="${allSupplies}">
    							<tr>
							        <td>${item.supplyId}</td>
							        <td>${item.supplyOriBean.supplyOriName}</td>
					                <td>${item.supplyProduct}</td>
								    <td>${item.supplyCount}</td>
								    <td>${item.supplyPrice}</td>
					                <td>${item.employeeBean.employeeName}</td>
								    <td>${item.createdAt}</td>
								    
								        
								        
								        <td>
								            <button 
					                    	type="button" 
								                id="edit"
								                onclick='openEditModal({
								                    supplyId: "${item.supplyId}", 
								                    supplyOriId: "${ori.supplyOriId}", 
								                    supplyName: "${ori.supplyOriName}", 
								                    supplyProduct: "${item.supplyProduct}",
								                    supplyCount: "${item.supplyCount}",
								                    supplyPrice: "${item.supplyPrice}",
								                    employeeId: "${emp.employeeId}" 
								                })'>編輯</button>
								            <form 
								                action="DeleteSupplyServlet.do" 
								                method="post"
								                style="display: inline;">
								                <input 
								                    type="hidden" 
								                    name="supplyId"
								                    value="${item.supplyId}"
								                />
								                
								                <button 
								                    type="submit" 
								                    id="delete"
								                    onclick="return confirm('確定要刪除嗎？');">
								                    刪除
												</button>
												
											</form>
					                    </td>
					                </tr>
					            </c:forEach>
					            
							</tbody>
						</table>
					</div>
				</section>
			</main>




			<!--  新增活動視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增供應商</h1>
					<form 
						action="CreateSupplyServlet.do" 
						method="post"
						id="dataForm">
					
						<fieldset>
							<!-- 區域1 -->

							<legend 
								style="margin-bottom: 1rem; font-weight: bold">
								供應商資訊 
							</legend>

							<div class="question">
								<span class="title">廠商編號</span> 
								<input 
									type="number" 
									id=""
									name="supplyOriId" 
									value="" 
									placeholder="請輸入廠商編號" 
									required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品名稱</span> 
								<input 
									type="text" 
									id=""
									name="supplyProduct" 
									value="" 
									placeholder="請輸入產品名稱" 
									required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品數量</span> 
								<input 
									type="number" 
									id=""
									name="supplyCount" 
									value="" 
									placeholder="請輸入產品數量" 
									required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品價錢</span> 
								<input 
									type="number" 
									id=""
									name="supplyPrice" 
									value="" 
									placeholder="請輸入產品價錢" 
									required
									aria-required="true" />
							</div>
							
							<div class="question">
								<span class="title">經手人</span> 
								<input 
									type="number" 
									id=""
									name="employeeId" 
									value="" 
									placeholder="請輸入員工編號" 
									required
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

			
			<!--  編輯活動視窗 -->
			<div 
				id="addEventModal2" 
				class="modal">
				
				<div 
					class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯供應商</h1>
					
					<form 
						action="UpdateSupplyServlet.do" 
						method="post"
						id="dataForm">
						
						<fieldset>
							<!-- 區域1 -->
							<legend style="margin-bottom: 1rem; font-weight: bold">
								編輯供應商資訊 
							</legend>
							
							
							<input 
								type="hidden" 
								name="supplyId" 
								id="supplyId" 
								value="">
								
								
							<div class="question">
								<span class="title">廠商編號</span> 
								<input 
									type="text" 
									id="supplyOriId"
									name="supplyOri_id" 
									value="" 
									placeholder="請輸入廠商編號" 
									required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品名稱</span> 
								<input 
									type="text" 
									id="supplyProduct"
									name="supply_product" 
									value="" 
									placeholder="請輸入產品名稱" 
									required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品數量</span> 
								<input 
									type="text" 
									id="supplyCount"
									name="supply_count" 
									value="" 
									placeholder="請輸入產品數量" 
									required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品價錢</span> 
								<input 
									type="text" 
									id="supplyPrice"
									name="supply_price" 
									value="" 
									placeholder="請輸入產品價錢" 
									required
									aria-required="true" />
							</div>
							
							<div class="question">
								<span class="title">經手人</span> 
								<input 
									type="text" 
									id="employeeId"
									name="employee_id" 
									value="" 
									placeholder="請輸入員工編號" 
									required
									aria-required="true" />
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
	      
	      function openEditModal(supply) {
	    	document.getElementById("addEventModal2").style.display = "block";
	    	
    	    document.getElementById("supplyId").value = supply.supplyId;
    	    document.getElementById("supplyOriId").value = supply.supplyOriId;
    	    document.getElementById("supplyProduct").value = supply.supplyProduct;
    	    document.getElementById("supplyCount").value = supply.supplyCount;
    	    document.getElementById("supplyPrice").value = supply.supplyPrice;
    	    document.getElementById("employeeId").value = supply.employeeId;
    	    
    	    }	      
	      
      
	</script>
</body>
</html>