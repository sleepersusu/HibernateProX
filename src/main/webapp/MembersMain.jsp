<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bistro.bean.MembersBean"%>
<%@ page import="bistro.bean.MembersDetailBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>會員管理 - DINEEASE餐廳管理系統</title>

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

.radiomenu {
	display: flex;
	height: 30px;
	margin-bottom: 16px;
	padding: 5px 10px;
	align-items: center;
}

.radiomenu .radiomenuitem {
	padding: 5px;
	margin-right: 10px;
}

.radiomenu .radiomenuitem>label {
	display: inline-block;
	vertical-align: middle;
}

.radiomenu .radiomenuitem>input {
	vertical-align: middle;
	position: relative;
	top: 8px
}

#tdtype {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
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
				<h2>會員資訊</h2>
				<section id="workspace">
					<div>
						<button type="submit" class="dataButton" id="addData">
							新增會員</button>
					</div>
				</section>



				<section>
					<div>
						<!--套件功能 class="display" 務必保留 如果資料需要跳行顯示可刪除nowrap-->
						<table id="table" class="display">
							<thead>
								<tr>
									<th>ID</th>
									<th>帳號</th>
									<th>密碼</th>
									<th>姓名</th>
									<th>性別</th>
									<th>出生日期</th>
									<th>興趣</th>
									<th>地址</th>
									<th>聯絡電話</th>
									<th>電子信箱</th>
									<th>會員圖片</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="event-list">
								<%
								List<MembersBean> memberList = (List<MembersBean>) request.getAttribute("allMembers");
								if (memberList != null) {
									for (MembersBean member : memberList) {
										MembersDetailBean membersDetail = member.getMembersDetailBean();
										String userimg = "";
										if (membersDetail.getMembersD_img() != null) {
									String base64Img = Base64.getEncoder().encodeToString(membersDetail.getMembersD_img());
									userimg = "data:image/jpeg;base64," + base64Img;
										}
								%>
								<tr>
									<td><%=member.getMembers_id()%></td>
									<td><%=member.getMember_account()%></td>
									<td><%=member.getMember_password()%></td>
									<td id="tdtype"><%=membersDetail.getMembersD_name()%></td>
									<td id="tdtype"
										style="color: <%=membersDetail.getMembersD_sex() == 1 ? "#0072E3" : "red"%>;">
										<%=membersDetail.getUserSexStr()%>
									</td>
									<td id="tdtype"><%=membersDetail.getMembersD_birthday()%></td>
									<td id="tdtype"><%=membersDetail.getUserFavorStr()%></td>
									<td id="tdtype"><%=membersDetail.getMembersD_address()%></td>
									<td id="tdtype"><%=membersDetail.getMembersD_phone()%></td>
									<td id="tdtype"><%=membersDetail.getMembersD_email()%></td>
									<td><img src="<%=userimg%>" alt="N/A" /></td>
									<td>
										<button type="button" id="edit"
											onclick='openEditModal({
									            userid: "<%=member.getMembers_id()%>",
									            useraccount: "<%=member.getMember_account()%>",
									            userpwd: "<%=member.getMember_password()%>",
									            username: "<%=membersDetail.getMembersD_name()%>",
									            usersex: "<%=membersDetail.getMembersD_sex()%>",
									            userbirthday: "<%=membersDetail.getMembersD_birthday()%>",
									            userfavor: "<%=membersDetail.getMembersD_favor()%>",
									            useraddress: "<%=membersDetail.getMembersD_address()%>",
									            userphone: "<%=membersDetail.getMembersD_phone()%>",
									            useremail: "<%=membersDetail.getMembersD_email()%>",
									            userimg: "<%=membersDetail.getMembersD_img()%>",
									        })'>編輯</button>

										<form action="DeleteMembersServlet.do" method="post"
											style="display: inline;">
											<input type="hidden" name="userid"
												value="<%=member.getMembers_id()%>">
											<button type="submit" id="delete"
												onclick="return confirm('確定要刪除嗎？');">刪除</button>
										</form>
									</td>
								</tr>
								<%
								} //迴圈結束
								} else {
								%>
								<tr>
									<td colspan="12">沒有資料</td>
								</tr>
								<%
								}
								%>
							</tbody>
						</table>
					</div>
				</section>
			</main>

			<!--  新增會員視窗 -->
			<div id="addEventModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>新增會員</h1>
					<form action="CreateMembersServlet.do" method="post" id="dataForm"
						enctype="multipart/form-data">
						<fieldset>
							<legend style="margin-bottom: 1rem; font-weight: bold">
								會員基本資料 </legend>

							<div class="question">
								<span class="title">會員帳號</span> <input type="text" id="useraccount"
									name="useraccount" value="" placeholder="請輸入會員帳號" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">會員密碼</span> <input type="text" id="userpwd"
									name="userpwd" value="" placeholder="請輸入會員密碼" required
									aria-required="true" />
							</div>
							<div class="question">
								<span class="title">會員姓名</span> <input type="text" id="username"
									name="username" value="" placeholder="請輸入會員姓名" required
									aria-required="true" />
							</div>

							<div class="question">
								<span class="title">會員性別:</span>
								<div class="question">
									<div class="radiomenu">
										<div class="radiomenuitem">
											<label for="male">男</label> <input type="radio"
												name="usersex" id="male" value="1" required />
										</div>
										<div class="radiomenuitem">
											<label for="female">女</label> <input type="radio"
												name="usersex" id="female" value="0" required />
										</div>
									</div>
								</div>
							</div>

							<div class="question">
								<span class="title">出生日期</span> <input type="DATE"
									id="userbirthday" name="userbirthday" value="" required />
							</div>

							<div class="question">
								<span class="title">興趣</span>
								<div class="radiomenu">
									<div class="radiomenuitem">
										<label for="introver">內向</label> <input type="radio"
											name="userfavor" id="introver" value="0" required />
									</div>
									<div class="radiomenuitem">
										<label for="extrovert">外向</label> <input type="radio"
											name="userfavor" id="extrovert" value="1" required />
									</div>
								</div>
							</div>

							<div class="question">
								<span class="title">會員地址</span> <input type="text"
									id="useraddress" name="useraddress" value=""
									placeholder="請輸入會員地址" />
							</div>

							<div class="question">
								<span class="title">聯絡電話</span> <input type="text"
									id="userphone" name="userphone" value="" placeholder="請輸入會員電話"
									required />
							</div>

							<div class="question">
								<span class="title">電子信箱</span> <input type="text"
									id="useremail" name="useremail" value="" placeholder="請輸入會員信箱"
									required />
							</div>
							<div class="question">
								<span class="title">會員頭像</span> <input type="file" id="userimg"
									name="userimg" value="" accept="image/*" />
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

			<!--  編輯會員視窗 -->
			<div id="addEventModal2" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h1>編輯會員</h1>
					<form action="UpdateMembersServlet.do" method="post" id="dataForm"
						enctype="multipart/form-data">
						<fieldset>
							<legend style="margin-bottom: 1rem; font-weight: bold">
								會員基本資料 </legend>
							<input type="hidden" name="edituserid" id="edituserid" value="">
							<div class="question">
								<div class="question">
									<span class="title">會員帳號</span> <input type="text"
										id="edituseraccount" name="edituseraccount" value=""
										placeholder="請輸入會員帳號" required aria-required="true" />
								</div>
								<div class="question">
									<span class="title">會員密碼</span> <input type="text"
										id="edituserpwd" name="edituserpwd" value=""
										placeholder="請輸入會員密碼" required aria-required="true" />
								</div>
								<div class="question">
									<span class="title">會員姓名</span> <input type="text"
										id="editusername" name="editusername" value=""
										placeholder="請輸入會員姓名" required aria-required="true" required />
								</div>

								<div class="question">
									<span class="title">會員性別:</span>
									<div class="question">
										<div class="radiomenu">
											<div class="radiomenuitem">
												<label for="male">男</label> <input type="radio"
													name="editusersex" id="editmale" value="1" required />
											</div>
											<div class="radiomenuitem">
												<label for="female">女</label> <input type="radio"
													name="editusersex" id="editfemale" value="0" required />
											</div>
										</div>
									</div>
								</div>

								<div class="question">
									<span class="title">出生日期</span> <input type="DATE"
										id="edituserbirthday" name="edituserbirthday" value=""
										required />
								</div>

								<div class="question">
									<span class="title">興趣:</span>
									<div class="radiomenu">
										<div class="radiomenuitem">
											<label for="introver">內向</label> <input type="radio"
												name="edituserfavor" id="editintrover" value="0" required />
										</div>
										<div class="radiomenuitem">
											<label for="extrovert">外向</label> <input type="radio"
												name="edituserfavor" id="editextrovert" value="1" required />
										</div>
									</div>
								</div>

								<div class="question">
									<span class="title">會員地址</span> <input type="text"
										id="edituseraddress" name="edituseraddress" value=""
										placeholder="請輸入會員地址" />
								</div>

								<div class="question">
									<span class="title">聯絡電話</span> <input type="text"
										id="edituserphone" name="edituserphone" value=""
										placeholder="請輸入會員電話" required />
								</div>

								<div class="question">
									<span class="title">電子信箱</span> <input type="text"
										id="edituseremail" name="edituseremail" value=""
										placeholder="請輸入會員信箱" required />
								</div>
								<div class="question">
									<span class="title">會員頭像</span> <input type="file"
										id="edituserimg" name="edituserimg" value="" accept="image/*" />
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
	        "columnDefs": [
	            {"width": "25px","targets": 0},
	            {"width": "75px","targets": 1},
	            {"width": "75px","targets": 2},
	            {"width": "60px","targets": 3},
	            {"width": "40px","targets": 4},
	            {"width": "70px","targets": 5},
	            {"width": "40px","targets": 6},
	            {"width": "70px","targets": 8},
	            {"width": "70px","targets": 10},
	            {"width": "50px","targets": 11},
	        ]
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
	      
	      function openEditModal(date) {
	    	  	console.log(date);
		    	document.getElementById("addEventModal2").style.display = "block";
		    	document.getElementById("edituserid").value = date.userid; 
	    	    document.getElementById("edituseraccount").value = date.useraccount;
	    	    document.getElementById("edituserpwd").value = date.userpwd;
	    	    document.getElementById("editusername").value = date.username;
	    	    
	    	    if(date.usersex==0){
	    	    	document.getElementById("editfemale").checked=true;
	    	    }else if(date.usersex==1){
	    	    	document.getElementById("editmale").checked=true;
	    	    }else{
	    	    	document.getElementById("editfemale").checked=false;
	    	    	document.getElementById("editmale").checked=false;
	    	    }
	    	    
	    	    document.getElementById("edituserbirthday").value = date.userbirthday;
	    	    
	    	    if(date.userfavor==0){
	    	    	document.getElementById("editintrover").checked=true;
	    	    }else if(date.userfavor==1){
	    	    	document.getElementById("editextrovert").checked=true;
	    	    }else{
	    	    	document.getElementById("editintrover").checked=false;
	    	    	document.getElementById("editextrovert").checked=false;
	    	    }
	    	    
	    	    document.getElementById("edituseraddress").value = date.useraddress; 
	    	    document.getElementById("edituserphone").value = date.userphone;
	    	    document.getElementById("edituseremail").value = date.useremail;
	    	    }

	    </script>


</body>
</html>