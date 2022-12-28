<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
<link  rel="stylesheet" href="<c:url value="/css/admin.css"/>" >
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</head>
<body>
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
            <div class="cardBox">
                <div class="card">
                    <div>
                        <div class="numbers">${totalShop}</div>
                        <div class="cardName">Cửa hàng</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="eye-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">${totalUser}</div>
                        <div class="cardName">Người dùng</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="cart-outline"></ion-icon>
                    </div>
                </div>

                <div class="card">
                    <div>
                        <div class="numbers">${totalProduct}</div>
                        <div class="cardName">Sản phẩm</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="chatbubbles-outline"></ion-icon>
                    </div>
                </div>
                <div class="card">
                    <div>
                        <div class="numbers"><fmt:formatNumber
														type="number" pattern="#,###"
														value="${countRevenue}"></fmt:formatNumber>₫</div>
                        <div class="cardName">Tổng doanh thu</div>
                    </div>

                    <div class="iconBx">
                        <ion-icon name="chatbubbles-outline"></ion-icon>
                    </div>
                </div>
                

               
            </div>

           <!-- ================ Order Details List ================= -->
           <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Đơn hàng</h2>
                    </div>
                    
                    
					
                    <table>
                        <thead>
                            <tr>
                                <td>Tên sản phẩm</td>
                                <td>Giá tiền</td>
                                <td>Ngày tạo</td>
                                <td>Trạng thái</td>
                                
                                
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${listSelectOrderDetail}" var="o">
                       		<tr>
                               <td>${o.product.PName}</td>  
                               <td>${o.totalPrice * o.count}</td> 
                               <td>${o.createAt}</td> 
                               <td>${o.orderstatus.statusName}</td> 
                                         
                               
                            </tr>
                         </c:forEach>
                          

                        </tbody>
                    </table>
                </div>
                <!-- ================= New Customers ================ -->
                <div class="recentCustomers">
                    <div class="cardHeader">
                        <h2>Người dùng</h2>
                    </div>
			       <table>
			                    <c:forEach items="${listUser}" var="o">
			                        <tr>
			                     
			                        
			                            <td width="60px">
			                                <div class="imgBx"><img src="${o.UImage}" alt="${o.UImage}"></div>
			                            </td>
			                           
			                            <td>
			                                <h4>${o.UFullName} <br> <span>${o.UAddress}</span></h4>
			                            </td>
			                        </tr>
								</c:forEach> 
			             </table>  
               	 </div>
            </div>
        </div>
    </div>

    <!-- =========== Scripts =========  -->
    <script src="<c:url value="/js/admin.js"/>"></script>
    <script src="<c:url value ="/js/stats.js" />"></script>
    <script src="<c:url value ="/js/managerProduct.js" />"></script>

	
	
	 
	    <!-- ====== ionicons ======= -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    
    
</body>
</html>
