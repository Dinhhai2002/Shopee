<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý danh mục</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <!-- ======= Styles ====== -->
    <link rel="stylesheet" href="<c:url value="/css/categoryAdmin.css"/>">
</head>
<body>
	 <!-- =============== Navigation ================ -->
    <div class="container">
         <jsp:include page="navagation.jsp"></jsp:include>
	
        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <!-- <div class="search">
                    <label>
                        <input type="text" placeholder="Search here">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>

                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div> -->
            </div>

            <!-- ======================= Cards ================== -->
           
			<div style="width:30%;" class="cardBox">
				<h3>Thống kê danh mục</h3>
				<a href="/admin/statistic/category">Thống kê theo danh mục</a>
				<a href="/admin/statistic/product">Thống kê theo sản phẩm</a>
				<a href="/admin/statistic/month">Thống kê theo tháng</a>
				<a href="/admin/statistic/shop">Thống kê theo shop</a>
			</div>
            <!-- ================ Order Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Thống kê theo danh mục</h2>
                        	
                        <!-- <a href="#" class="btn">Xem tất cả</a> -->
                    </div>
                    <div class="row">
								<form action="/admin/statistic/product" method="get">
								<div class="form-group">
								<label>Tên sản phẩm</label> <input id="pName" name="productName"
									type="text" class="form-control" required />
									
									
							</div>
							<div class="form-group">
								<label>Từ thời điểm</label> <input id="pName" name="fromDate"
									type="date" class="form-control" required />
									
									
							</div>
							<div class="form-group">
								<label>Đến thời điểm</label> <input id="pName" name="toDate"
									type="date" class="form-control" required />
									
									
							</div>
							<div class="form-group">
							 <input id="pName" name="submit"
									type="submit" class="form-control" value="submit" />
							</div>
							
								</form>
							</div>
                   <div class="col-sm-6">
									<canvas id="myProductChart"></canvas>
								</div>
								<table class="col-sm-6-table table table-striped table-hover">
								<thead>
									<tr>
										<th>Tên Danh mục</th>

										<th>số lượng</th>

									</tr>
								</thead>
								<tbody id="tbody">
									<c:forEach items="${listP}" var="o">
										<tr style="margin: 8px 0;">


											<td>${o[0]}</td>
											<td>${o[1]}</td>

										</tr>

									</c:forEach>
								</tbody>
							</table>
							

                    
                </div>

                <!-- ================= New Customers ================ -->
                
            </div>
        </div>
    </div>
    <script type="text/javascript">
	let cateLabels=[],cateInfo=[],ProductLabels=[],ProductInfo=[],ProductMonthLabels=[],ProductMonthInfo=[]
	/* <c:forEach items="${list}" var="o">
	cateLabels.push('${o[0]}')
	cateInfo.push(${o[1]})
	</c:forEach> */
	 <c:forEach items="${listP}" var="o">
	ProductLabels.push('${o[0]}')
	ProductInfo.push(${o[1]})
	</c:forEach>
	/*
	//statistic by month
	<c:forEach items="${listPMonth}" var="o">
	ProductMonthLabels.push('${o[0]}')
	ProductMonthInfo.push(${o[1]})
	</c:forEach> */
	
		window.onload=function()
		{
			//cateChart("myCateChart",cateLabels,cateInfo)
			ProductChart("myProductChart",ProductLabels,ProductInfo)
			//ProductMonthChart("myProductMonthChart",ProductMonthLabels,ProductMonthInfo)
		}
	</script>
  
   
    <!-- =========== Scripts =========  -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="<c:url value="/js/admin.js"/>"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>