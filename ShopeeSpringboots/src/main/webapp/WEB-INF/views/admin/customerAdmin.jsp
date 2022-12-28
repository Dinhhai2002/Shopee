<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý người dùng</title>
 <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese" rel="stylesheet">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'> 
    <!-- ======= Styles ====== -->
	<link href="<c:url value="/css/customerAdmin.css"/>" rel="stylesheet"
	type="text/css" />
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

                 <form style="margin-top:20px;"action=""class="search">
                    <label>
                        <input type="text" value="${name}" name="name" placeholder="Search here">
                       
                        <button type="submit"><ion-icon name="search-outline"></ion-icon></button>
                       
                    </label>
                    
                    
                </form>
				<!--
                <div class="user">
                    <img src="assets/imgs/customer01.jpg" alt="">
                </div> -->
            </div>

            <!-- ======================= Cards ================== -->
            <div class="cardBox">
                
            </div>

            <!-- ================ Order Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Người dùng</h2>
                        <!-- <a href="#" class="btn">Xem tất cả</a> -->
                    </div>
					<c:if test="${userPage.totalPages>0}">
								<div aria-label="Page navigation" class="clearfix">

									<ul class="pagination">
										<li
											class="${1==userPage.number+1 ? 'page-item active':'page-item' } "><a
											href="<c:url value="/admin/user?name=${name}&size=${userPage.size}&page=${1}" />"
											tabindex="1" aria-disabled="true">First</a></li>
										<c:forEach items="${pageNumbers}" var="pageNumber">
											<c:if test="${userPage.totalPages >1}">

												<li
													class="${pageNumber==userPage.number+1 ? 'page-item active':'page-item' } "><a
													href="<c:url value="/admin/user?name=${name}&size=${userPage.size}&page=${pageNumber}"/> "
													class="page-link">${pageNumber}</a></li>

											</c:if>
										</c:forEach>
										<li
											class="${userPage.totalPages==userPage.number+1 ? 'page-item active':'page-item' } "><a
											href="<c:url value="/admin/user?name=${name}&size=${userPage.size}&page=${userPage.totalPages}"/> ">Last</a></li>
									</ul>
								</div>
							</c:if>
                    <table>
                        <thead>
                            <tr>
                            	<td>Tên đăng nhập</td>
                                <td>Họ và tên</td>
                                <td>Email</td>
                                <td>Điện thoại</td>
                                <td>Địa chỉ</td>
                                <td>Ngày tạo</td>
                                <td>Thao tác</td>
                                
                            </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${userPage.content}" var="o">
                        
                            <tr>
                                <td>${o.UName}</td>
                                <td>${o.UFullName}</td>
                                <td>${o.UEmail}</td>
                                <td>${o.UPhone}</td>
                                <td>${o.UAddress}</td>
                                <td>${o.createAt}</td>
                                <td class="customerEdit">
                                    <a href="" onclick="editEmployeeModal(${o.UId})"  class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                           			
                                </td>
                            </tr>
						</c:forEach>
                        </tbody>
                    </table>
                </div>

                <!-- ================= New Customers ================ -->
                
            </div>
        </div>
    </div>
   
    <!-- Edit Modal HTML -->
    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="/Shopee/admin/customer/edit" method="post" enctype="multipart/form-data">
                    <div class="modal-header">						
                        <h4 class="modal-title">Chỉnh sửa người dùng</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                
                    <div class="modal-body">					
                         <input id="userId" name="pQuantity" type="hidden" class="form-control" required />
									
							
                        <div class="form-group">
								<label>trạng thái người dùng</label> <select name="cateId"
									id="editStatusUser" class="form-select"
									aria-label="Default select example">
									
										<option value="0">khóa</option>
										<option value="1">hoạt động</option>
									
								</select>
							</div>				
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input onclick="updateUser()"type="button" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <!-- =========== Scripts =========  -->
   <script src="<c:url value="/js/admin.js"/>"></script>

    <!-- ====== ionicons ======= -->
    <script type="module" src="<c:url value="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"/>"></script>
    <script nomodule src="<c:url value="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"/>"></script>
</body>
</html>