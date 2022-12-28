<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Giỏ hàng</title>
<link href="<c:url value="/css/base.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/main.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/grid.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/responsive.css"/>" rel="stylesheet"
	type="text/css" />
<link href="<c:url value="/css/cart.css"/>" rel="stylesheet"
	type="text/css" />

<link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css'
	rel='stylesheet'>
<style type="text/css">
.footer-logo-background {
	/* background: url("<c:url value="/img/bg-icon.png"/>"); */
	background: url("${pageContext.request.contextPath}/img/bg-icon.png");
}
</style>
</head>

<body>
	<div class="app">

		<jsp:include page="header.jsp"></jsp:include>
		<div class="container">
			<div class="grid wide">
				<div class="row sm-gutter body-content">

					<div class="col l-10 lo-10 m-12 c-12">
						<c:if test="${carts!=null}">
							<div class="table-wrapper">
								<!-- <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <h2>Manage <b>Product</b></h2>
                                    </div>
                                    
                                </div>
                            </div> -->
								<table class="table table-striped table-hover">
									<thead>
										<tr>


											<th>sản phẩm</th>
											<th>hình ảnh</th>
											<th>Giá</th>
											<th>số lượng</th>
											<th>Xóa</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${carts}" var="c">
											<tr style="margin: 8px 0;" id="product${c.productId}">


												<td>${c.productName}</td>
												<td><img src="${c.productImage}"></td>

												<td><fmt:formatNumber type="number" pattern="#,###"
														value="${c.price}">
													</fmt:formatNumber></td>

												<td class="align-middle"><input
													onblur="updatetoCart(this,${c.productId})" type="number"
													value="${c.quantity}"></td>


												<td class="submit"><a href="#"
													onclick="deleteToCart(${c.productId})">Xóa</a></td>
											</tr>


										</c:forEach>
									</tbody>
								</table>




							</div>
						</c:if>
						<c:if test="${carts==null}">
							<div style="text-align: center;" class="home-product-shipping">
								<img
									src="https://icons.veryicon.com/png/o/miscellaneous/contribution/empty-box-1.png"
									alt="" style="width: 90px; height: 90px;">
								<p>Chưa có đơn hàng</p>
							</div>
						</c:if>

					</div>
				</div>




				<div class="row sm-gutter body-content">

					<div class="col l-10 lo-10 m-12 c-12">
						<h3 class="title">Thành tiền</h3>
						<div class="p-4">
							<ul class="list-unstyled mb-4">
								<li class="list-item"><strong class="text-muted">Tổng
										tiền hàng</strong> <strong id="amount"> <fmt:formatNumber type="number"
											pattern="#,###" value="${cartStats.amount}">
										</fmt:formatNumber> vnđ
								</strong></li>
								<!--  <li class="list-item"><strong
                                class="text-muted">Phí vận chuyển</strong><strong>10000 vnđ</strong></li> -->
								<!--  <li class="list-item"><strong
                                class="text-muted">VAT</strong><strong>10 $</strong></li> -->
								<%--  <li class="list-item list-item-total"><strong
                                class="text-muted">Tổng tiền hàng</strong>
                                <h5 class="font-weight-bold">${total}vnđ</h5></li> --%>
							</ul>
							<a href="/checkout"  class="btn_buy">Mua hàng</a>
						</div>
					</div>
				</div>






			</div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
	<script src="<c:url value="/js/cartApi.js"/>"></script>
	<script src="<c:url value="/js/home.js"/>"></script>
</body>

</html>
