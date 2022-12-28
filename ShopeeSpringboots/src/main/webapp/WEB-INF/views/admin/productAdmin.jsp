<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quản lý sản phẩm</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese"
	rel="stylesheet">
<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'
	rel='stylesheet'>
<!-- ======= Styles ====== -->
<link rel="stylesheet" href="<c:url value="/css/categoryAdmin.css"/>">
<link href="<c:url value="/css/base.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/grid.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/responsive.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/ManagerProductAdmin.css"/>" rel="stylesheet"
	type="text/css" />
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

				<form style="margin-top:20px;"action=""class="search">
                    <label>
                        <input type="text" value="${name}" name="name" placeholder="Search here">
                       
                        <button type="submit"><ion-icon name="search-outline"></ion-icon></button>
                       
                    </label>
                    
                    
                </form>

				<div class="user">
					<img src="assets/imgs/customer01.jpg" alt="">
				</div> -->
			</div>

			<!-- ======================= Cards ================== -->
			<div class="cardBox">
				<a href="#addProductModal" class="btn btn-info" data-toggle="modal"><i
					class="material-icons">&#xE147;</i> <span>Thêm sản phẩm mới</span></a>
			</div>
			<c:if test="${productPage.totalPages>0}">
								<div aria-label="Page navigation" class="clearfix">

									<ul class="pagination">
										<li
											class="${1==productPage.number+1 ? 'page-item active':'page-item' } "><a
											href="<c:url value="/admin/product?name=${name}&size=${productPage.size}&page=${1}" />"
											tabindex="1" aria-disabled="true">First</a></li>
										<c:forEach items="${pageNumbers}" var="pageNumber">
											<c:if test="${productPage.totalPages >1}">

												<li
													class="${pageNumber==productPage.number+1 ? 'page-item active':'page-item' } "><a
													href="<c:url value="/admin/product?name=${name}&size=${productPage.size}&page=${pageNumber}"/> "
													class="page-link">${pageNumber}</a></li>

											</c:if>
										</c:forEach>
										<li
											class="${productPage.totalPages==productPage.number+1 ? 'page-item active':'page-item' } "><a
											href="<c:url value="/admin/product?name=${name}&size=${productPage.size}&page=${productPage.totalPages}"/> ">Last</a></li>
									</ul>
									<form style="margin-left:20px;" action="">
											<label for="size">size</label> <select name="size" id="size"
												onchange="this.form.submit()">
												<option ${productPage.size==10? 'selected': ''} value="10">10</option>
												<option ${productPage.size==20? 'selected': ''} value="20">20</option>
												<option ${productPage.size==30? 'selected': ''} value="30">30</option>
												<option ${productPage.size==40? 'selected': ''} value="40">40</option>
											</select>
										</form>
								</div>
							</c:if>
			<!-- ================ Order Details List ================= -->
			<div class="details">
				<div class="recentOrders">


					<table>
						<thead>
							<tr>
								<td>Tên sản phẩm</td>
								<td>Hình ảnh</td>
								<td>Giá bán</td>
								<td>Số lượng trong kho</td>
								<td>Hành động</td>
							</tr>
						</thead>

						<tbody>

							<c:forEach items="${productPage.content}" var="o">
								<tr>
									<td>${o.PName}</td>
								
									<td>
											<img
												style="width: 150px; height: 150px; object-fit: contain;"
												src="${o.PImage}" alt=" ${o.PName}">
										
										</td>
									<td>${o.PPrice}</td>
									<td>${o.PQuantity}</td>
									<td class="customerEdit"><a href=""
										onclick="editProductModal(${o.PId})" class="edit"
										data-toggle="modal"><i class="material-icons"
											data-toggle="tooltip" title="Edit">&#xE254;</i></a> <a href=""
										onclick="deleteProductModal(${o.PId})" class="delete"
										data-toggle="modal"><i class="material-icons"
											data-toggle="tooltip" title="Delete">&#xE872;</i></a></td>
								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>

				<!-- ================= New Customers ================ -->

			</div>
		</div>
	</div>
	<div id="addProductModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="addP" method="post" enctype="multipart/form-data">
						<div class="modal-header">
							<h4 class="modal-title">Thêm sản phẩm mới</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label>Tên sản phẩm</label> <input id="pName" name="pName"
									type="text" class="form-control" required />
							</div>

							<div class="form-group form-group-col">

								<label for="idImage"> Chọn thêm hình ảnh </label> <select
									name="selectImage" id="idImage">

									<option value="file">Thêm bằng file</option>
									<option value="link">Thêm bằng url</option>

								</select>
								<!-- <label style="margin-top:10px">Ảnh đại diện</label> -->
								<input id="image1" class="text" name="imageFile1" type="text"
									class="form-control" /> <input type="file" id="image"
									class="file" onchange="chooseFile(this)" accept=".jpg, .png"
									name="imageFile" />
							</div>

							<div class="form-group">
								<label>Giá sản phẩm</label> <input id="pPrice" name="pPrice"
									type="text" class="form-control" required required />
							</div>
							<div class="form-group">
								<label>Mô tả sản phẩm</label>
								<textarea id="pDescription" name="pDescription"
									class="form-control"></textarea>
							</div>
							<div class="form-group">
								<label>Số lương nhập vào kho</label> <input id="pQuantity"
									name="pQuantity" type="text" class="form-control" required />
							</div>
							<div class="form-group">
								<label>Danh mục sản phẩm</label> <select name="cateId"
									id="category" class="form-select"
									aria-label="Default select example">
									<c:forEach items="${listC}" var="o">
										<option value="${o.CId}">${o.CName}</option>
									</c:forEach>
								</select>
							</div>

						</div>
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="Cancel" /> <a onclick="addProduct()" href="#"
								class="btn btn-info" />Save</a>

						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="editProductModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<form>
						<div class="modal-header">
							<h4 class="modal-title">Edit Employee</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input id="editpId" name="id" hidden="" />
							</div>
							<div class="form-group">
								<input id="editshopId" name="id" hidden="" />
							</div>
							<div class="form-group">
								<label>Name</label> <input name="Name" id="editpName" type="text"
									class="form-control" required>
							</div>
							<div class="form-group form-group-col">
								<img id="editpImage" class="img-responsive" width="100px" src="/image?fname=image" alt=""> <label for="idImage">
								
								<label for="idImage"> Chọn thêm hình ảnh </label> <select
									name="selectImage" id="idImageEdit">

									<option value="file">Thêm bằng file</option>
									<option value="link">Thêm bằng url</option>

								</select>
								<!-- <label style="margin-top:10px">Ảnh đại diện</label> -->
								<input id="textEdit" name="image1" type="text"
									class="form-control" /> <input type="file" id="fileEdit"
									name="image" />
							</div>
							<div class="form-group">
								<label>price</label>
								<input name="price" id="editpPrice" type="text" class="form-control" required>
									
							
							</div>
							<div class="form-group">
								<label>Quantity</label>
								<input name="price" id="editpQuantity" type="text" class="form-control" required>
								
							</div>
							
							<div class="form-group">
							<label>Mô tả</label>
							<textarea id="editpDescription" name="description"
								class="form-control" required></textarea>
						</div>
						
						<div class="form-group">
								<label>Danh mục sản phẩm</label> <select name="cateId"
									id="editpCategory" class="form-select"
									aria-label="Default select example">
									<c:forEach items="${listC}" var="o">
										<option value="${o.CId}">${o.CName}</option>
									</c:forEach>
								</select>
							</div>
							</div>
						
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="Cancel"> 
								<input onclick="updateProduct()"type="button" class="btn btn-info" value="Save">
								
						</div>
					</form>
				</div>
			</div>
		</div>
	<!-- Delete Modal HTML -->
	<div id="deleteProductModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="product/delete" method="post">

					<div class="modal-header">
						<h4 class="modal-title">Xóa sản phẩm</h4>
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
					</div>
					<input id="deletepId" name="id" value="0" hidden="">
					<div class="modal-body">
						<p>Bạn có chắc muốn xóa?</p>
						<p class="text-warning">
							<small>Hành động này không thể hoàn tác</small>
						</p>
					</div>
					<div class="modal-footer">
						<input onclick="returnProductIdValue()" type="button" class="btn btn-default" data-dismiss="modal"
							value="Cancel"> <input type="submit"
							class="btn btn-danger" value="Delete">
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<!-- =========== Scripts =========  -->
	<script src="<c:url value="/js/admin.js"/>"></script>

	<!-- ====== ionicons ======= -->
	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
</body>
</html>