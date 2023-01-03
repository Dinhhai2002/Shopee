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
								<h3 style="font-size: 30px; color: red;">${mess}</h3>
								<div class="row">
									<div class="col-sm-6-shop">
										<a href="#" class="table-title-header-title"> Thông tin
											của shop </a>
										<div class="table-content">
											<div class="table-content-img">
												<c:url value="/image?fname=${shop.shopImage}" var="imgUrl"></c:url>
												<c:if test="${fn:contains(shop.shopImage, 'https')}">
													<img src="${shop.shopImage}" alt=" ${shop.shopName}">
												</c:if>
												<c:if test="${fn:contains(shop.shopImage, 'shop')}">
													<img src="${imgUrl}" alt=" ${shop.shopImage}">
												</c:if>
											</div>
											<div class="table-content-container">
												<h3 class="table-content-container-name">
													${shop.shopName}</h3>
												<input type="hidden" id="shopId"
													class="table-content-container-name" value="${shop.shopId}" />


												<p class="table-content-container-description">
													${shop.shopDecription}</p>
											</div>
										</div>

									</div>


								</div>
							</div>

							<div class="table-title">

								<div class="row">
									<div class="col-sm-6">
										<a href="#" class="table-title-header"> Quản lý sản phẩm </a>
										<a href="/managerProduct/orderManager" class="table-title-header"
											data-toggle="modal"> <span>Đơn hàng</span></a> <a
											href="" onclick="editShopModal(${shop.shopId})"
											id="btn_model"class="table-title-header" data-toggle="modal"> <span>Chỉnh
												sửa thông tin shop</span></a>
												<a href="/managerProduct/stats" class="table-title-header"
											data-toggle="modal"> <span>Thống kê</span></a>
									</div>
									<div class="col-sm-6 btn-add">
										<a href="#addProductModal" class="btn btn-info"
											data-toggle="modal"><i class="material-icons">&#xE147;</i>
											<span>Thêm sản phẩm mới</span></a>



									</div>

								</div>
							</div>
							<c:if test="${productPage.totalPages>0}">
								<div aria-label="Page navigation" class="clearfix">

									<ul class="pagination">
										<li
											class="${1==productPage.number+1 ? 'page-item active':'page-item' } "><a
											href="<c:url value="/managerProduct?name=${name}&size=${productPage.size}&page=${1}" />"
											tabindex="1" aria-disabled="true">First</a></li>
										<c:forEach items="${pageNumbers}" var="pageNumber">
											<c:if test="${productPage.totalPages >1}">

												<li
													class="${pageNumber==productPage.number+1 ? 'page-item active':'page-item' } "><a
													href="<c:url value="/managerProduct?name=${name}&size=${productPage.size}&page=${pageNumber}"/> "
													class="page-link">${pageNumber}</a></li>

											</c:if>
										</c:forEach>
										<li
											class="${productPage.totalPages==productPage.number+1 ? 'page-item active':'page-item' } "><a
											href="<c:url value="/managerProduct?name=${name}&size=${productPage.size}&page=${productPage.totalPages}"/> ">Last</a></li>
									</ul>
								</div>
							</c:if>
							<div class="clearfix">
								<form action="">
									<label for="size">size</label> <select name="size" id="size"
										onchange="this.form.submit()">
										<option ${productPage.size==10? 'selected': ''} value="10">10</option>
										<option ${productPage.size==20? 'selected': ''} value="20">20</option>
										<option ${productPage.size==30? 'selected': ''} value="30">30</option>
										<option ${productPage.size==40? 'selected': ''} value="40">40</option>
									</select>
								</form>
							</div>
							<script type="text/javascript">
						function chooseFile(fileInput) {
							if (fileInput.files && fileInput.files[0]) {
								var reader = new FileReader();
								reader.onload = function(e) {
									$('#images').attr('src', e.target.result);
								}
								reader.readAsDataURL(fileInput.files[0]);
							}

						}
					</script>
							<table class="table table-striped table-hover">
								<thead>
									<tr>


										<th>Tên sản phẩm</th>
										<th>hình ảnh</th>
										<th>Giá</th>
										<th>Thao tác</th>
									</tr>
								</thead>
								<tbody id="tbody">
									<c:forEach items="${productPage.content}" var="o">
										<tr id="product${o.PId}"style="margin: 8px 0;">


											<td>${o.PName}</td>
											<c:if test="${o.PImage !=null }">
												<c:choose>
													<c:when test="${o.PImage.substring(0,5)=='https' }">
														<td><img
															style="width: 150px; height: 150px; object-fit: contain;"
															id="images" src="${o.PImage}" alt=" ${o.PName}"></td>
													</c:when>
													<c:otherwise>
														<td><img
															style="width: 150px; height: 150px; object-fit: contain;"
															id="images" src="/product/images/${o.PImage}"
															alt=" ${o.PName}"></td>
													</c:otherwise>
												</c:choose>

											</c:if>
											<c:if test="${o.PImage==null }">
												<td><img
													style="width: 150px; height: 150px; object-fit: contain;"
													id="images" src="/images/noname.jpg" alt=" ${o.PName}">

												</td>
											</c:if>




											<td><fmt:formatNumber type="number" pattern="#,###"
													value="${o.PPrice}"></fmt:formatNumber>đ</td>
											<td><a href="" onclick="editProductModal(${o.PId})"
												class="edit" data-toggle="modal"><i
													class='bx bxs-pencil'></i></a> <a
												onclick="deleteProduct(${o.PId})" style="cursor: pointer;"
												class="delete" data-toggle="modal"><i
													class='bx bx-trash'></i></a></td>
										</tr>

									</c:forEach>
								</tbody>
							</table>

							<c:choose>
								<c:when test="${empty listP}">
									<div style="text-align: center;" class="home-product-shipping">
										<img
											src="https://ucarecdn.com/796ac032-6154-40cd-ad78-ad87a3069ed2/7102456.png"
											alt="" style="width: 90px; height: 90px;">
										<p>Chưa có hàng đăng bán</p>
									</div>
								</c:when>
								<c:otherwise>
									<div style="text-align: center;" class="home-product-shipping">

									</div>
								</c:otherwise>
							</c:choose>

						</div>

					</div>
				</div>
			</div>
		</div>

		<!-- Edit Modal HTML -->
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
		<div id="editShopModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<form>
						<div class="modal-header">
							<h4 class="modal-title">Edit Employee</h4>
							<button type="button" id="btn_close"class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input id="cateIdShop" name="id" hidden="" />
							</div>
							<div class="form-group">
								<input id="userIdShop" name="id" hidden="" />
							</div>
							<div class="form-group">
								<input id="editshopId" name="id" hidden="" />
							</div>
							<div class="form-group">
								<label>Name</label> <input name="Name" id="editpNameShop" type="text"
									class="form-control" required>
							</div>
							<div class="form-group form-group-col">
								<img id="editpImageShop" class="img-responsive" width="100px" src="/image?fname=image" alt=""> <label for="idImage">
								
								<label for="idImage"> Chọn thêm hình ảnh </label> <select
									name="selectImage" id="idImageEdit">

									<option value="file">Thêm bằng file</option>
									<option value="link">Thêm bằng url</option>

								</select>
								<!-- <label style="margin-top:10px">Ảnh đại diện</label> -->
								<input id="textEditShop" name="image1" type="text"
									class="form-control" /> <input type="file" id="fileEdit"
									name="image" />
							</div>
							<div class="form-group">
							<label>Mô tả</label>
							<textarea id="editShoppDescription" name="description"
								class="form-control" required></textarea>
						</div>
							</div>
							
						
						
						
						<div class="modal-footer">
							<input type="button" class="btn btn-default" data-dismiss="modal"
								value="Cancel"> 
								<input onclick="updateShop()"type="button" class="btn btn-info" value="Save">
								
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- Delete Modal HTML -->
		<div id="deleteProductModal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<form action="deleteP" method="post">
						<div class="modal-header">
							<h4 class="modal-title">Delete Product</h4>
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
						</div>
						<input id="deleteId" value="0" name="id" hidden="" />
						<div class="modal-body">
							<p>Bạn có chắc muốn xóa sản phẩm này?</p>
							<p class="text-warning">
								<small>Hành động này không thể hoàn tác</small>
							</p>
						</div>
						<div class="modal-footer">
							<input onclick="returnValue()" type="button"
								class="btn btn-default" data-dismiss="modal" value="Cancel">
							<input type="submit" class="btn btn-danger" value="Delete">
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>




	<script src="<c:url value="/js/managerProduct.js"/>"></script>
	<script src="<c:url value="/js/home.js"/>"></script>

</body>
</html>