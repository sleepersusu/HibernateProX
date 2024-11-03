<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>導覽列</title>
</head>
<div id="left">
	<nav id="sidebar">
		<div id="logo">
			<img src="${pageContext.request.contextPath}/images/logo.jpg"
				alt="商店logo" />
		</div>
		<div id="sidebox">
			<div id="menubox">
				<ul class="menu">
					<li class="menuitem"><a href="/BistroBackstage/MembereCount.jsp"> <i
							class="fa-solid fa-house" style="color: #ffffff"></i> 首頁
					</a></li>


					<li class="menuitem"><a href="/BistroBackstage/FindAllMenuServlet.do">
							<i class="fa-solid fa-whiskey-glass"></i> 商品管理
					</a></li>

					<li class="menuitem"><a href="/BistroBackstage/MembersMainServlet.do">
							<i class="fa-solid fa-address-card"></i> 會員管理
					</a>
						<ul class="dropdown">

						</ul></li>

					<li class="menuitem"><a
						href="/BistroBackstage/ReservationAllqueryServlet.do"> <i
							class="fa-regular fa-calendar-check"></i> 訂位管理
					</a>
						<ul class="dropdown">
							<li>
								<!--下拉選項內容 li裡放a標籤-->
							</li>
						</ul></li>

					<li class="menuitem"><a href="/BistroBackstage/ShowAllOrderDetailsServlet.do"> <i
							class="fa-regular fa-clipboard"></i> 訂單管理
					</a> <!--下拉選項內容開始-->
						<ul class="dropdown">
							<li>
								<!--下拉選項內容 li裡放a標籤-->
							</li>
						</ul></li>

					<li class="menuitem"><a
						href="/BistroBackstage/TestShowAllCampaignServlet.do"> <i
							class="fa-regular fa-face-laugh-squint"></i> 活動管理
					</a> <!--下拉選項內容開始-->
						<ul class="dropdown">
							<li>
								<!--下拉選項內容 li裡放a標籤-->
							<li><a href="/BistroBackstage/TestShowAllCampaignServlet.do"><span class="arrow">&#9660;</span> 活動資訊
							</a></li>
							<li><a href="/BistroBackstage/ShowAllPrizeServlet.do"><span class="arrow">&#9660;</span> 獎品資訊 </a></li>
							<li><a href="/BistroBackstage/ShowAllEntriesServlet.do"><span class="arrow">&#9660;</span> 參與者資訊 </a></li>
						</ul></li>

					<li class="menuitem"><a
						href="/BistroBackstage/ShowAllReadSupplyServlet.do"> <i
							class="fa-solid fa-cart-flatbed"></i> 採購管理
					</a> <!--下拉選項內容開始-->
						<ul class="dropdown">
							<li>
								<!--下拉選項內容 li裡放a標籤-->
							</li>
						</ul></li>

					<li class="menuitem"><a href="#"> <i
							class="fa-solid fa-comment"></i> 評論管理
					</a> <!--下拉選項內容開始-->
						<ul class="dropdown">
							<li>
								<!--下拉選項內容 li裡放a標籤-->
							</li>
						</ul></li>

					<li class="menuitem"><a href="/BistroBackstage/ShowAllPointPrizesServlet.do"> <i
							class="fa-solid fa-user"></i> 點數管理
					</a> <!--下拉選項內容開始-->
						<ul class="dropdown">
							<li><a href="/BistroBackstage/ShowAllPointPrizesServlet.do"><span class="arrow">&#9660;</span> 點數資訊</a></li>
							<li><a href="/BistroBackstage/ShowAllPointsRecordServlet.do"><span class="arrow">&#9660;</span> 兌換歷史</a></li>
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
</html>