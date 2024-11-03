<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bistro.bean.MenuBean"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>商品管理 - DINEEASE餐廳管理系統</title>

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
				<h2>商品管理</h2>
				<section id="workspace">
					<div>
						<button type="submit" class="dataButton" id="addData">
							新增商品</button>
					</div>
				</section>

				<section>
					<div>
						<table id="table" class="display ">
							<thead>
								<tr>
									<th>ID</th>
									<th>名稱</th>
									<th>照片</th>
									<th>價格</th>
									<th>分類</th>
									<th>描述</th>
									<th>操作</th>
								</tr>
							</thead>

							<tbody>
								<%
								List<MenuBean> queryAllProductList = (List<MenuBean>) request.getAttribute("queryAllProduct");
								if (queryAllProductList != null) {
									for (MenuBean menu : queryAllProductList) {
								%>
								<tr class="fade-out">
									<td><%=menu.getMenuid()%></td>
									<td><%=menu.getProductName()%></td>


									<%
									// 将字节数据转换为 Base64 字符串
									byte[] imgData = menu.getProductImage();
									String imgBase64 = (imgData != null) ? Base64.getEncoder().encodeToString(imgData) : "";
									%>
									<td><img src="data:image/png;base64,<%=imgBase64%>"
										alt="Product Image" style="width: 100px; height: auto;" /></td>
									<td><%=menu.getProductPrice()%></td>
									<td><%=menu.getProductCategory()%></td>
									<td><%=menu.getProductDescription()%></td>

									<td>
										<button type="button" id="edit"
											onclick='openEditModal({
									           menuid:"<%=menu.getMenuid()%>",
									            productCategory: "<%=menu.getProductCategory()%>",
									            productImage: "<%=imgBase64%>",
									            productName: "<%=menu.getProductName()%>",
									            productPrice: "<%=menu.getProductPrice()%>",
									          productDescription: "<%=menu.getProductDescription()%>",
									            
									        })'>編輯</button>

										<form action="DeleteMenuByIdServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="menuid"
												value="<%=menu.getMenuid()%>">
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
									<td colspan="7">沒有活動資料</td>
								</tr>
								<%
								}
								%>


							</tbody>
						</table>
					</div>
				</section>
			</main>

			<!-- -------------------------跳出表單 新增商品------------------------ -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增商品資料</h1>
					<form action="MenuCreateServlet.do" method="post"
						enctype="multipart/form-data" id="dataForm">
						<fieldset>
							<!-- 區域1 -->

							<legend>商品資訊</legend>

							<div class="question">
								<label for="productCategory" class="title">商品分類</label> <select
									id="productCategory" name="productCategory" required
									aria-required="true">
									<option value="0" selected>選擇商品分類</option>
									<option value="主菜">主菜</option>
									<option value="沙拉">沙拉</option>
									<option value="飲料">飲料</option>
									<option value="小吃">小吃</option>
									<option value="甜點">甜點</option>
								</select>
							</div>

							<div class="question">
								<label for="productImage" class="title">商品照片</label> <input
									type="file" id="productImage" name="productImage" required
									aria-required="true" accept="image/*" />
							</div>

							<div class="question">
								<label for="productName" class="title">商品名稱</label> <input type="text"
									id="productName" name="productName" value=""
									placeholder="請輸入商品名稱" required aria-required="true" />
							</div>



							<div class="question">
								<label for="productPrice"  class="title">商品單價</label> <input type="number"
									id="productPrice" name="productPrice" value=""
									placeholder="請輸入商品單價" required aria-required="true" />
							</div>

							<div class="question">
								<label for="productDescription"   class="title">商品製作</label>
								<textarea name="productDescription" placeholder="請輸入商品如何製作"
									required aria-required="true" id="productDescription" ></textarea>
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
			<!-- addEventModal 跳出表單 -->
			<!-- -------------------------跳出表單 新增商品------------------------ -->





			<!-- -------------------------跳出表單 編輯商品------------------------ -->

			<div id="addEventModal2" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯商品資料</h1>
					<form action="EditMenuServlet.do" method="post"
						enctype="multipart/form-data" id="dataForm">
						<fieldset>
							<!-- 區域1 -->
							<legend>商品資訊</legend>
							
							<label for="menuid2"></label>
							<input type="text" name="menuid" id="menuid2" readonly>
							<div class="question">
								<label for="productCategory2" class="title">商品分類</label> <select
									id="productCategory2" name="productCategory" required
									aria-required="true">
									<option value="0" selected>選擇商品分類</option>
									<option value="主菜">主菜</option>
									<option value="沙拉">沙拉</option>
									<option value="飲料">飲料</option>
									<option value="小吃">小吃</option>
									<option value="甜點">甜點</option>
								</select>
							</div>

							<div class="question">
								<label for="productImage2" class="title">商品照片</label> <input
									type="file" id="productImage2" name="productImage" required
									aria-required="true" accept="image/*" />
							</div>

							<div class="question">
								<label for="productName2" class="title">商品名稱</label> <input type="text"
									id="productName2" name="productName" value=""
									placeholder="請輸入商品名稱" required aria-required="true" />
							</div>



							<div class="question">
								<label for="productPrice2"  class="title">商品單價</label> <input type="number"
									id="productPrice2" name="productPrice" value=""
									placeholder="請輸入商品單價" required aria-required="true" />
							</div>

							<div class="question">
								<label for="productDescription2"   class="title">商品製作</label>
								<textarea name="productDescription" placeholder="請輸入商品如何製作"
									required aria-required="true" id="productDescription2" ></textarea>
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
			<!-- addEventModal 跳出表單 -->

			<!-- -------------------------跳出表單 編輯商品------------------------ -->
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
      
      function openEditModal(menu) {
    	document.getElementById("addEventModal2").style.display = "block";
    	document.getElementById("menuid2").value =menu.menuid;
	   	document.getElementById("productCategory2").value = menu.productCategory;
	   	
	    document.getElementById("productName2").value = menu.productName;
	    document.getElementById("productPrice2").value = menu.productPrice;
	    document.getElementById("productDescription2").value = menu.productDescription;
	    
	    }
      

	</script>


</body>
</html>