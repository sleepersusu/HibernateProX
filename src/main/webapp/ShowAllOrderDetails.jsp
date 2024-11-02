<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.OrderDetailsBean"%>
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

		<%@ include file="nav.jsp"%>
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
									<th>顧客姓名</th>
									<th>座位號碼</th>
									<th>訂單狀況</th>
									<th>特殊備註</th>
									<th>建立時間</th>
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
									<td><%=detail.getOrders().getOrdersId()%></td>
									<td><%=detail.getProduct().getMenuid()%></td>
									<td><%=detail.getProduct().getProductName()%></td>
									<td><%=detail.getProduct().getProductPrice()%></td>
									<td><%=detail.getProduct_quantity()%></td>
									<td><%=detail.getTotal_price()%></td>
									<td><%=detail.getOrders().getCustomerName()%></td>
									<td><%=detail.getOrders().getSeatId()%></td>
									<td><%=detail.getOrders().getOrderStatus()%></td>
									<td><%=detail.getSpecial_requests()%></td>
									<td><%=detail.getCreated_at()%></td>
									<td>
										<button type="button" id="edit"
											onclick='openEditModal({
									           orderDetails_id:"<%=detail.getOrderDetails_id()%>",
												orders:"<%=detail.getOrders().getOrdersId()%>",
									            productid: "<%=detail.getProduct().getMenuid()%>",
									            productName:"<%=detail.getProduct().getProductName()%>",
									            
									            
									            productQuantity: "<%=detail.getProduct_quantity()%>",
									            totalQuantity: "<%=detail.getTotal_quantity()%>",
									            totalPrice: "<%=detail.getTotal_price()%>",
									            specialRequest: "<%=detail.getSpecial_requests()%>",
									            createdAt: "<%=detail.getCreated_at()%>",
									        })'>編輯</button>

										<form action="DeleteOrderDetailsServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="orderDetails_id"
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

			<!--  新增視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增訂單詳情</h1>
					<form action="CreateOrderDetailsServlet.do" method="post"
						id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								訂單詳情資訊 </legend>

							<div class="question">
								<span class="title">訂單編號</span> <input type="text" id="orders"
									name="orders" value="" placeholder="請輸入訂單編號" required
									aria-required="true" />
							</div>

							<div class="question">
								<label for="productid" class="title">餐點</label> <select
									name="productid" id="productid" required>
									<option value="0" data-price="0" selected>選擇餐點</option>
									<option data-name="牛排" data-price="500" value="1">牛排</option>
									<option data-name="沙拉" data-price="150" value="2">沙拉</option>
									<option data-name="義大利麵" data-price="300" value="3">義大利麵</option>
									<option data-name="飲料" data-price="100" value="4">飲料</option>
									<option data-name="甜點" data-price="200" value="5">甜點</option>
									<option data-name="比薩" data-price="400" value="6">比薩</option>
									<option data-name="燉飯" data-price="350" value="7">燉飯</option>
									<option data-name="炸物拼盤" data-price="450" value="8">炸物拼盤</option>
									<option data-name="海鮮拼盤" data-price="600" value="9">海鮮拼盤</option>
									<option data-name="烤雞" data-price="300" value="10">烤雞</option>
									<option data-name="果汁" data-price="120" value="11">果汁</option>
									<option data-name="冰淇淋" data-price="150" value="12">冰淇淋</option>
									<option data-name="春捲" data-price="180" value="13">春捲</option>
									<option data-name="牛肉麵" data-price="250" value="14">牛肉麵</option>
									<option data-name="咖啡" data-price="180" value="15">咖啡</option>
									<option data-name="羊排" data-price="700" value="16">羊排</option>
									<option data-name="炸雞" data-price="230" value="17">炸雞</option>
									<option data-name="蛋糕" data-price="250" value="18">蛋糕</option>
									<option data-name="海鮮炒飯" data-price="320" value="19">海鮮炒飯</option>
									<option data-name="奶昔" data-price="160" value="20">奶昔</option>
								</select>
							</div>


							<div class="question">
								<label for="productName" class="title">商品名稱</label> <input
									type="text" name="productName" id="productName" value=""
									required readonly>
							</div>
							
							<div class="question">
								<span class="title">價格</span> <input type="text"
									id="productPrice" name="productPrice" value=""
									placeholder="" required aria-required="true" readonly />
							</div>

							<div class="question">
								<span class="title">數量</span> <input type="number"
									id="productQuantity" name="productQuantity" value=""
									placeholder="請輸入數量" required aria-required="true" min="0" />
							</div>

							<div class="question">
								<span class="title">總數量</span> <input type="number"
									id="totalQuantity" name="totalQuantity" value="" required
									aria-required="true" readonly />
							</div>
							
							

							<div class="question">
								<span class="title">總價格</span> <input type="number"
									id="totalPrice" name="totalPrice" value="" required
									aria-required="true"  readonly/>
							</div>

							<div class="question">
								<span class="title">特殊備註</span> <input type="text"
									id="specialRequest" name="specialRequest" value="" />
							</div>
							
							<div class="question">
								<label for="createdAt" class="title">建立時間</label> <input
									type="datetime-local" name="createdAt" id="createdAt" value=""
									required step=60>
							</div>
						</fieldset>

						<!---------------------------底下為按鈕區---------------------------------------------- -->
						<div id="buttonbox">
							<button type="submit" id="save" class="formbutton">儲存</button>
							<button type="reset" id="reset" class="formbutton">重設</button>
						</div>
					</form>
				</div>
			</div>
			<!-- modal-content -->


			<!--  編輯視窗 -->

			<div id="addEventModal2" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯訂單詳情</h1>
					<form action="UpdateOrderDetailsServlet.do" method="post"
						id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend style="margin-bottom: 1rem; font-weight: bold">
								訂單詳情資訊 </legend>

							<div class="question">
								<span class="title">訂單編號</span> <input type="text" id="orders2"
									name="orders" value="" placeholder="請輸入訂單編號" required
									aria-required="true" />
							</div>

							<div class="question">
								<label for="productid2" class="title">餐點</label> <select
									name="productid" id="productid2" required>
									<option value="0" data-price="0" selected>選擇餐點</option>
									<option data-name="牛排" data-price="500" value="1">牛排</option>
									<option data-name="沙拉" data-price="150" value="2">沙拉</option>
									<option data-name="義大利麵" data-price="300" value="3">義大利麵</option>
									<option data-name="飲料" data-price="100" value="4">飲料</option>
									<option data-name="甜點" data-price="200" value="5">甜點</option>
									<option data-name="比薩" data-price="400" value="6">比薩</option>
									<option data-name="燉飯" data-price="350" value="7">燉飯</option>
									<option data-name="炸物拼盤" data-price="450" value="8">炸物拼盤</option>
									<option data-name="海鮮拼盤" data-price="600" value="9">海鮮拼盤</option>
									<option data-name="烤雞" data-price="300" value="10">烤雞</option>
									<option data-name="果汁" data-price="120" value="11">果汁</option>
									<option data-name="冰淇淋" data-price="150" value="12">冰淇淋</option>
									<option data-name="春捲" data-price="180" value="13">春捲</option>
									<option data-name="牛肉麵" data-price="250" value="14">牛肉麵</option>
									<option data-name="咖啡" data-price="180" value="15">咖啡</option>
									<option data-name="羊排" data-price="700" value="16">羊排</option>
									<option data-name="炸雞" data-price="230" value="17">炸雞</option>
									<option data-name="蛋糕" data-price="250" value="18">蛋糕</option>
									<option data-name="海鮮炒飯" data-price="320" value="19">海鮮炒飯</option>
									<option data-name="奶昔" data-price="160" value="20">奶昔</option>
								</select>
							</div>


							<div class="question">
								<label for="productName2" class="title">商品名稱</label> <input
									type="text" name="productName" id="productName2" value=""
									required readonly>
							</div>
							
							<div class="question">
								<span class="title">價格</span> <input type="text"
									id="productPrice2" name="productPrice" value=""
									placeholder="" required aria-required="true" readonly />
							</div>

							<div class="question">
								<span class="title">數量</span> <input type="number"
									id="productQuantity2" name="productQuantity" value=""
									placeholder="請輸入數量" required aria-required="true" min="0" />
							</div>

							<div class="question">
								<span class="title">總數量</span> <input type="hidden"
									id="totalQuantity2" name="totalQuantity" value="" required
									aria-required="true" readonly />
							</div>
							
							

							<div class="question">
								<span class="title">總價格</span> <input type="number"
									id="totalPrice2" name="totalPrice" value="" required
									aria-required="true"  readonly/>
							</div>

							<div class="question">
								<span class="title">特殊備註</span> <input type="text"
									id="specialRequest2" name="specialRequest" value="" />
							</div>
							
							<div class="question">
								<label for="createdAt2" class="title">建立時間</label> <input
									type="datetime-local" name="createdAt" id="createdAt2" value=""
									required step=60>
							</div>
						</fieldset>

						<!---------------------------底下為按鈕區---------------------------------------------- -->
						<div id="buttonbox">
							<button type="submit" id="save" class="formbutton">儲存</button>
							<button type="reset" id="reset" class="formbutton">重設</button>
						</div>
					</form>
				</div>
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
	      
	      
	      
	     
	      

	      //--------------------------------新增表單----------------------------------------------//
	   // 當餐點選項改變時，更新價格
	      document.getElementById('productid').addEventListener('change', function() {
	              let selectedOption = this.options[this.selectedIndex];
	              let price = selectedOption.getAttribute('data-price') || '';
	              let name = selectedOption.getAttribute('data-name') || '';
	              document.getElementById('productPrice').value = price;
	              document.getElementById('productName').value =name;
	              updateTotalPrice2();
	          });

	          // 當數量改變時，更新總價
	          document.getElementById('productQuantity').addEventListener('input', updateTotalPrice);
				
	          
	          document.getElementById('productQuantity').addEventListener('change', function() {
	  	        let productQuantity=document.getElementById('productQuantity').value;
	  			let totalQuantity=document.getElementById('totalQuantity')
	  			totalQuantity.value=productQuantity;
	  	        	
	  	        })
	          // 更新總價
	          function updateTotalPrice() {
	              let price = document.getElementById("productPrice").value;
	              let quantity = document.getElementById("productQuantity").value;
	              let totalPrice = document.getElementById("totalPrice");
	              let regex = new RegExp("^[0-9]+$");
	              let test=regex.test(quantity);
	              if (!isNaN(price) && !isNaN(quantity)&&test) {
	                  totalPrice.value = (price*quantity);
	              } else {
	                  totalPrice.value = "";
	              }

	          }
	        //--------------------------------新增表單----------------------------------------------//
	          
	        
	        
	      //--------------------------------編輯表單----------------------------------------------//
	          // 當餐點選項改變時，更新價格
		      document.getElementById('productid2').addEventListener('change', function() {
		              let selectedOption = this.options[this.selectedIndex];
		              let price = selectedOption.getAttribute('data-price') || '';
		              let name = selectedOption.getAttribute('data-name') || '';
		              document.getElementById('productPrice2').value = price;
		              document.getElementById('productName2').value =name;
		              updateTotalPrice2();
		          });

		          // 當數量改變時，更新總價
		          document.getElementById('productQuantity2').addEventListener('input', updateTotalPrice2);
					
		          
		          document.getElementById('productQuantity2').addEventListener('change', function() {
		  	        let productQuantity=document.getElementById('productQuantity2').value;
		  			let totalQuantity=document.getElementById('totalQuantity2')
		  			totalQuantity.value=productQuantity;
		  	        	
		  	        })
		          // 更新總價
		          function updateTotalPrice2() {
		              let price = document.getElementById("productPrice2").value;
		              let quantity = document.getElementById("productQuantity2").value;
		              let totalPrice = document.getElementById("totalPrice2");
		              let regex = new RegExp("^[0-9]+$");
		              let test=regex.test(quantity);
		              if (!isNaN(price) && !isNaN(quantity)&&test) {
		                  totalPrice.value = (price*quantity);
		              } else {
		                  totalPrice.value = "";
		              }

		          }
		          //--------------------------------編輯表單----------------------------------------------//
		          
		          
		          
		           //--------------------------------顯示編輯表單預設值----------------------------------------------//
	      function openEditModal(orderDetails) {
		    	document.getElementById("addEventModal2").style.display = "block";
		    	document.getElementById("orders2").value = orderDetails.orders;
		    	
		    	
	    	    document.getElementById("productid2").value = orderDetails.productid;
	    	    
	    	    document.getElementById("productid2").dispatchEvent(new Event('change'));

	    	    document.getElementById("productQuantity2").value = orderDetails.productQuantity;
	    	    document.getElementById("totalQuantity2").value = orderDetails.totalQuantity;
	    	    document.getElementById("totalPrice2").value = orderDetails.totalPrice;
	    	    document.getElementById("specialRequest2").value = orderDetails.specialRequest;
	    	    document.getElementById("createdAt2").value = orderDetails.crearedAt;
		      }
	      //--------------------------------顯示編輯表單預設值----------------------------------------------//
      
	</script>
</body>
</html>