<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link  rel="stylesheet" href="<c:url value="/css/admin.css"/>" >
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
</head>
<body>
	<div class="container">
        <div class="navigation">
            <ul>
                <li>
                    <a href="#"  >
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Tất cả trạng thái </span>
                    </a>
                </li>
                <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Tất cả </span>
                    </a>
                </li>
               <!--   <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Chờ xác nhận</span>
                    </a>
                </li>
                 <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Chờ shipper nhận đơn </span>
                    </a>
                </li>
                 <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon" >
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Chờ lấy hàng</span>
                    </a>
                </li>
                 <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Đang đi giao</span>
                    </a>
                </li>
                 <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Đã đi giao</span>
                    </a>
                </li>
	  <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Khách trả hàng</span>
                    </a>
                </li>
               <li>
                    <a href="#" onclick="purchase(this)">
                        <span class="icon">
                            <ion-icon name="logo-apple"></ion-icon>
                        </span>
                        <span class="title">Đã từ chối</span>
                    </a>
                </li> -->
               

                <li>
                    <a href="/login">
                        <span class="icon">
                            <ion-icon name="log-out-outline"></ion-icon>
                        </span>
                        <span class="title">Đăng xuất</span>
                    </a>
                </li>
            </ul>
        </div>

        <!-- ========================= Main ==================== -->
        <div class="main">
            <div class="topbar">
                <div class="toggle">
                    <ion-icon name="menu-outline"></ion-icon>
                </div>

                <div class="search">
                    <label>
                        <input type="text" placeholder="Search here">
                        <ion-icon name="search-outline"></ion-icon>
                    </label>
                </div>

                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div>
            </div>

            <!-- ======================= Cards ================== -->
            

           <!-- ================ Order Details List ================= -->
           <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Đơn hàng</h2>
                        
                    </div>
                    <%-- <div class="flexWrapper">
                    	<canvas id="chartElement" width="200" height="50"></canvas>
                    </div> --%>
					
                    <table>
                        <thead>
                            <tr>
                                		<th>Người đặt</th>
										<th>Tên sản phẩm</th>
										<th>Hình ảnh</th>
										<th>Giá</th>
										<th>Tình trạng đơn hàng</th>
                            </tr>
                        </thead>

                        <tbody>
                      <c:forEach items="${listO}" var="o">
										<tr style="margin: 8px 0;">
											<td>${o.order.user.UName}</td>
											<td>${o.product.PName}</td>
											<td><img src="${o.product.PImage}"
												style="width: 90px; height: 90px;"></td>
											<td>${o.totalPrice}</td>
											 <td >${o.orderstatus.statusName}</td> 
											 <c:set var = "s" value = "${o.orderstatus.id}"/> 
											<%-- <input type="hidden" value="${o.orderstatus.id}"  id="orderStatus${o.orderstatus.id}"  /> --%>
												<c:if test="${s==1}">
													<td><button id="orderStatus" onclick="editOrder(${o.id})" value="${o.id}">Chốt đơn</button></td>
													<td><button id="orderStatus" onclick="editOrderReject(${o.id})" value="${o.id}">Từ chối</button></td>													
												</c:if>
																		
											
											
										</tr>
									</c:forEach>
                          

                        </tbody>
                    </table>
                    <c:choose>
							<c:when test="${empty listO}">
								<div style="text-align: center;" class="home-product-shipping">
									<img
										src="https://icons.veryicon.com/png/o/miscellaneous/contribution/empty-box-1.png"
										alt="" style="width: 90px; height: 90px;">
									<p>Chưa có đơn hàng</p>
								</div>
							</c:when>
							<c:otherwise>
								<div style="text-align: center;" class="home-product-shipping">

								</div>
							</c:otherwise>
						</c:choose>
                </div>
                	
                <!-- ================= New Customers ================ -->
                
            </div>
        </div>
    </div>

    <!-- =========== Scripts =========  -->
    <script src="<c:url value="/js/managerProduct.js"/>"></script>
    <script src="<c:url value="/js/admin.js"/>"></script>
    <%-- <script src="<c:url value ="/js/stats.js" />"><</script>
 --%>    
	
    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
</body>
</html>
</body>
</html>