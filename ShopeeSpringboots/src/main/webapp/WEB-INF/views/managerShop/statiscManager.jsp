<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Trang người bán</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese"
	rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'
	rel='stylesheet'>
<link href="<c:url value="/css/base.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/grid.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/responsive.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/ManagerProduct.css"/>" rel="stylesheet"
	type="text/css" />

<style type="text/css">
.footer-logo-background {
	/* background: url("<c:url value="/img/bg-icon.png"/>"); */
	background: url("${pageContext.request.contextPath}/img/bg-icon.png");
}

body {
	margin: 0;
}
</style>
</head>
<body>

	<div class="app">
		<jsp:include page="header.jsp"></jsp:include>

		<div class="container">
			<div class="grid wide">

				<!-- Body -->
				<div class="row sm-gutter body-content">


					<div class="col l-12 lo-12 m-12 c-12">

						<div class="table-wrapper">


							<div class="table-title">

								<div class="row">
								<div class="col-sm-6">
									<canvas id="myCateChart"></canvas>
								</div>
								<table class="col-sm-6-table table table-striped table-hover">
								<thead>
									<tr>
										<th>Tên Danh mục</th>

										<th>số lượng</th>

									</tr>
								</thead>
								<tbody id="tbody">
									<c:forEach items="${list}" var="o">
										<tr style="margin: 8px 0;">


											<td>${o[0]}</td>
											<td>${o[1]}</td>

										</tr>

									</c:forEach>
								</tbody>
							</table>

								</div>
								<div class="row">
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
								<div class="row">
								<form action="/managerProduct/stats" method="get">
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
							<div class="row">
								<div class="col-sm-6">
									<canvas id="myProductMonthChart"></canvas>
								</div>
								<table class="col-sm-6-table table table-striped table-hover">
								<thead>
									<tr>
										<th>Tên Ngày</th>

										<th>số lượng</th>

									</tr>
								</thead>
								<tbody id="tbody">
									<c:forEach items="${listPMonth}" var="o">
										<tr style="margin: 8px 0;">


											<td>${o[0]}</td>
											<td>${o[1]}</td>

										</tr>

									</c:forEach>
								</tbody>
							</table>

								</div>
								<div class="row">
								<form action="/managerProduct/stats" method="get">
								
							<div class="form-group">
								<label>Từ thời điểm</label> <input id="pName" name="fromDateMonth"
									type="date" class="form-control" required />
									
									
							</div>
							<div class="form-group">
								<label>Đến thời điểm</label> <input id="pName" name="toDateMonth"
									type="date" class="form-control" required />
									
									
							</div>
							<div class="form-group">
							 <input id="pName" name="submit"
									type="submit" class="form-control" value="submit" />
							</div>
							
								</form>
							</div>
							</div>

						</div>

					</div>
				</div>
			</div>
		</div>



		<!-- Delete Modal HTML -->

		<jsp:include page="footer.jsp"></jsp:include>
	</div>

	<script type="text/javascript">
	let cateLabels=[],cateInfo=[],ProductLabels=[],ProductInfo=[],ProductMonthLabels=[],ProductMonthInfo=[]
	<c:forEach items="${list}" var="o">
	cateLabels.push('${o[0]}')
	cateInfo.push(${o[1]})
	</c:forEach>
	<c:forEach items="${listP}" var="o">
	ProductLabels.push('${o[0]}')
	ProductInfo.push(${o[1]})
	</c:forEach>
	
	//statistic by month
	<c:forEach items="${listPMonth}" var="o">
	ProductMonthLabels.push('${o[0]}')
	ProductMonthInfo.push(${o[1]})
	</c:forEach>
	
		window.onload=function()
		{
			cateChart("myCateChart",cateLabels,cateInfo)
			ProductChart("myProductChart",ProductLabels,ProductInfo)
			ProductMonthChart("myProductMonthChart",ProductMonthLabels,ProductMonthInfo)
		}
	</script>

	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script src="<c:url value="/js/managerProduct.js"/>"></script>
	<script src="<c:url value="/js/home.js"/>"></script>
	
</body>
</html>