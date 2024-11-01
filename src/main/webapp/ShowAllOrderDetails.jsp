<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.OrderDetailsBean"%>
<%@ page import="bistro.bean.OrdersBean"%>
<%@ page import="bistro.bean.MenuBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>訂單詳情管理 - DINEEASE餐廳管理系統</title>

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

#save {
	background-color: #0071e3;
	border-radius: 10px;
	color: white;
	width: auto;
	border: none;
	font-size: 1rem;
	padding: 0.4rem 2rem;
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
	padding: 0.3rem 2rem;
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

		<%@ include file="nav.jsp" %>
		<div id="right">
			<header>

				<button id="logout">
					<a href="login.jsp"><i
						class="fa-solid fa-arrow-right-from-bracket"></i> Log Out</a>
				</button>
			</header>

			<main>
				<h2>訂單詳情管理</h2>
				<section id="workspace">
					<div>
						<button type="submit" class="dataButton" id="addData">
							新增訂單詳情</button>
					</div>
				</section>



				<section>
					<div>
						<!--套件功能 class="display" 務必保留 如果資料需要跳行顯示可刪除nowrap-->
						<table id="table" class="display">
							<thead>
								<tr>
									<th>ID</th>
									<th>訂單編號</th>
									<th>產品編號</th>
									<th>產品名稱</th>
									<th>產品價格</th>
									<th>產品數量</th>
									<th>總價格</th>
									<th>座位號碼</th>
									<th>特殊備註</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="event-list">
								<%
								List<OrderDetailsBean> allOrderDetails = (List<OrderDetailsBean>) request.getAttribute("allOrderDetails");
								if (allOrderDetails != null) {
									for (OrderDetailsBean detail : allOrderDetails) {
								%>
								<tr>
									<td><%=detail.getOrderDetails_id()%></td>
									<td><%=detail.getOrders()%></td>
									<td><%=detail.getProduct()%></td>
									<td><%=detail.getProduct().getProductName()%></td>
									<td><%=detail.getProduct().getProductPrice()%></td>									
									<td><%=detail.getProduct_quantity()%></td>
									<td><%=detail.getTotal_price()%></td>
									<td><%=detail.getOrders().getSeatId()%></td>
									<td><%=detail.getSpecial_requests()%></td>
									<td>
										<button type="button" id="edit"
											onclick='openEditModal({
											
												orderDetails_id:"<%=detail.getOrderDetails_id()%>",
												orders:"<%=detail.getOrders() %>",
									            product: "<%=detail.getProduct()%>",
									            product_quantity: "<%=detail.getProduct_quantity()%>",
									            total_quantity: "<%=detail.getTotal_quantity()%>",
									            total_price: "<%=detail.getTotal_price()%>",
									            special_requests: "<%=detail.getSpecial_requests()%>",
									        })'>編輯</button>

										<form action="DeleteOrderDetailsServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="orderId"
												value="<%=detail.getOrderDetails_id()%>">
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
									<td colspan="9">沒有詳細資料</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</section>
			</main>

			<!--  新增訂單視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增訂單詳情</h1>
					<form action="CreateOderDetailsServlet.do" method="post"
						id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								訂單詳情資訊 </legend>

							<div class="question">
								<span class="title">訂單編號</span> <input type="text" id=""
									name="orders" value="" placeholder="請輸入訂單編號" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品編號</span> <input type="text" id=""
									name="menuId" value="" placeholder="請輸入菜單編號" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品數量</span> <input type="text" id=""
									name="productQuantity" value="" placeholder="請輸入產品數量" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">總數量</span> <input type="number"
									id="" name="totalQuantity" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">總價格</span> <input type="number"
									id="" name="totalPrice" value="" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">特殊備註</span> <input type="text" id=""
									name="specialRequest" value="" required aria-required="true" />
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


			<!--  編輯活動視窗 -->
			<div id="addEventModal2" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯訂單詳情</h1>
					<form action="UpdateOrderDetailsServlet.do" method="post" id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								訂單詳情資訊 </legend>


							<input type="hidden" name="orderDetailsId" id="orderDetailsId" value="">
							
							<div class="question">
								<span class="title">產品編號</span> <input type="text"
									id="menuId" name="menuId" value=""
									placeholder="請輸入活動名稱" required aria-required="true" />
							</div>

							<div class="question">
								<span class="title">產品數量</span>
								<input type="text"
									id="productQuantity" name="productQuantity" value=""
									placeholder="請輸入活動名稱" required aria-required="true" />
							</div>
							
							<div class="question">
								<span class="title">總數量</span>
								<input type="text"
									id="totalQuantity" name="totalQuantity" value=""
									placeholder="請輸入活動名稱" required aria-required="true" />			
							</div>
							
							<div class="question">
								<span class="title">總價格</span>
								<input type="number"
									id="totalPrice" name="totalPrice" value=""
									placeholder="請輸入活動名稱" required aria-required="true" />
							</div>

							<div class="question">
								<span class="title">特殊備註</span> <input type="text"
									id="specialRequest" name="specialRequest" value=""
									placeholder="請輸入活動類別" required aria-required="true" />
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
	      
	      function openEditModal(orderDetails) {
	    	document.getElementById("addEventModal2").style.display = "block";
	    	
	    	document.getElementById("orderDetailsId").value = orderDetails.orders;
    	    document.getElementById("menuId").value = orderDetails.menuId;
    	    document.getElementById("productQuantity").value = orderDetails.productQuantity;
    	    document.getElementById("totalQuantity").value = orderDetails.totalQuantity;
    	    document.getElementById("totalPrice").value = orderDetails.totalPrice;
    	    document.getElementById("specialRequest").value = orderDetails.specialRequest;
	      }
	            
      
	</script>
</body>
</html>