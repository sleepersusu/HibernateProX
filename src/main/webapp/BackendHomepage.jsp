<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>數據分析 - DINEEASE餐廳管理系統</title>

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

<script src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
	
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

#countdiv {
            display: colum;
            justify-content: space-around;
            margin: 20px;
        }
        #countdiv h1{
        width:400px;
    	color:black;
        text-shadow: 1px 1px 3px gray;
        font-size:40px
        }
        .countstat {
            padding: 20px;
            margin-left:100px;
            width:200px;
        }
        .countstat p{
        	font-size:180px;
        	font-weight: bold;
        	text-shadow: 2px 2px 3px gray;
        }
#piechart{
	position: relative;
	top:50px;
	width:800px;
	height:700px;
	background-color: white;

}
#right{
	width:100%;
	position: relative;
	background-color:white;
}
#main{
	display:flex;
	justify-content: space-around;
}

</style>


</head>




<body>
	<!-- datatable  -->
	<script src="${pageContext.request.contextPath}/jquery/jquery-3.7.1.js"></script>
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>


	<div id="page">
		<%@ include file="nav.jsp" %>

		<div id="right">
			<header>
				<button id="logout"><a href="login.jsp"><i class="fa-solid fa-arrow-right-from-bracket"></i> Log Out</a></button>
			</header>
			<main id="main">
				<div id="countdiv">
		    	<h1>目前會員人數</h1>
		    	<div class = "countstat" >
		    		<p id="memberCount" ></p>
		    	</div> <!-- 預設為 Loading... -->
		 		</div>
		 		<div id="piechart"></div>
	 		</main>
		</div>
		

 	
 	</div>
 	<script>
        // 使用 Fetch API 從伺服器獲取數據
        var chartDom = document.getElementById('piechart');
        var myChart = echarts.init(chartDom);
        var option;

        // 初始化图表
		var option = {
		    title: {
		        text: '會員',
		        subtext: ' ',
		        left: 'center',
		        textStyle: {
		            fontSize: 40 // 標題字體大小
		        },
		        subtextStyle: {
		            fontSize: 14 // 副標題字體大小
		        }
		    },
		    tooltip: {
		        trigger: 'item'
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        textStyle: {
		            fontSize: 16 // 圖例字體大小
		        }
		    },
		    series: [
		        {
		            name: '會員性別',
		            type: 'pie',
		            radius: '75%',
		            data: [
		                { value: 11, name: '女' },
		                { value: 9, name: '男' }
		            ],
		            itemStyle: {
		                color: function(params) {
		                    const colorList = [
		                        '#ff9999', // 第一個顏色
		                        '#66b3ff', // 第二個顏色
		                        '#99ff99', // 第三個顏色
		                        '#ffcc99', // 第四個顏色
		                        '#c2c2f0'  // 第五個顏色
		                    ];
		                    return colorList[params.dataIndex]; 
		                }
		            },
		            label: {
		                show: true,
		                fontSize: 50, // 普通狀態的字體大小
		                color: '#000' // 字體顏色
		            },
		            emphasis: {
		                itemStyle: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.7)'
		                },
		                label: {
		                    fontSize: 60, // 強調狀態的字體大小
		                    color: '#000', // 強調狀態的字體顏色
		                    backgroundColor: "rgba(255, 255, 255, 0.22)"
		                }
		            }
		        }
		    ]
		};
        
        async function fetchData() {
            try {
                const response = await fetch('MemberCountServlet.do'); // 替換成你的 API URL
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                
                // 解析 JSON 數據
                const data = await response.json();
                const countMembers = data.countMembers;
                const countMembersSexType = data.countMembersSexType;
                
                console.log(countMembers);
                document.getElementById('memberCount').textContent = countMembers;
                
                console.log(countMembersSexType);
                
                // 處理性別類型數據
        //        countMembersSexType.forEach(item => {
        //            item.value = item.membersSexTypecount; // 新增 value 屬性
        //            item.name = item.userSex === 1 ? '男' : '女'; // 根據 userSex 的值設置 name
        //            delete item.membersSexTypecount; // 刪除舊的 membersSexTypecount 屬性
        //            delete item.userSex; // 刪除舊的 userSex 屬性
        //        });

                // 查看修改後的結果
                console.log(countMembersSexType); // 這裡應該打印 countMembersSexType
                const pieData = countMembersSexType.map(item => ({
                    value: item.membersSexTypecount,
                    name: item.userSex
                }));
                
                console.log(pieData); // 修正這裡的拼寫錯誤
                myChart.setOption(option);
                myChart.setOption({
                    series: [
                        {
                            data: pieData
                        }
                    ]
                });

            } catch (error) {
                console.error('Fetch error:', error);
            }
        }

        // 調用 fetchData 函數
        fetchData();

    </script>
</body>
</html>
			
			
			
			
			
			