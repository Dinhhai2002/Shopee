/**
 * 
 */
// add hovered class to selected list item
let list = document.querySelectorAll(".navigation li");

function activeLink() {
  list.forEach((item) => {
    item.classList.remove("hovered");
  });
  this.classList.add("hovered");
}

list.forEach((item) => item.addEventListener("mouseover", activeLink));

// Menu Toggle
let toggle = document.querySelector(".toggle");
let navigation = document.querySelector(".navigation");
let main = document.querySelector(".main");

toggle.onclick = function () {
  navigation.classList.toggle("active");
  main.classList.toggle("active");
};
/*
let selectImage = document.getElementById('idImage');
const text = document.querySelector('.text');
const file = document.querySelector('.file');


selectImage.onchange = displayvaluechange;
function displayvaluechange() {
	if (this.value == "link") {
		text.style.display = "block";
		file.style.display = "none";
		file.id = "image1";
		file.name = "imageFile1"
		text.id = "image";
		text.name = "imageFile";
	}
	if (this.value == "file") {
		file.style.display = "block";
		text.style.display = "none";

		text.id = "image1";
		text.name = "imageFile1";
		file.id = "image";
		file.name = "imageFile"
	}

}
let selectImageEdit = document.getElementById('idImageEdit');
const textEdit = document.getElementById('textEdit');
const fileEdit = document.getElementById('fileEdit');
selectImageEdit.onchange = displayvaluechangeEdit;
function displayvaluechangeEdit() {
	if (this.value == "link") {
		textEdit.style.display = "block";
		fileEdit.style.display = "none";
		fileEdit.name = "image1";
		textEdit.name = "image";
	}
	if (this.value == "file") {
		fileEdit.style.display = "block";
		textEdit.style.display = "none";
		textEdit.name = "image1";
		fileEdit.name = "image";
	}

}

*/
function deleteCategoryModal(param) {
	document.getElementById('deleteId').setAttribute('value', param);
	$("#deleteCategoryModal").modal('show');
}
function addCategoryModal() {
	
	$("#addCategoryModal").modal('show');
}
function editCategoryModal(param) {
	getCategory(param)
	$("#editCategoryModal").modal('show');
}
function editShopModal(param) {
	getShop(param)
	$("#editCategoryModal").modal('show');
}
function editProductModal(param) {
	editProduct(param);
	$("#editProductModal").modal('show');
}

function deleteEmployeeModal(param) {
	document.getElementById('deleteId').setAttribute('value', param);
	$("#deleteEmployeeModal").modal('show');
}

function editEmployeeModal(id) {
	getUser(id);
	$("#editEmployeeModal").modal('show');
}
function getUser(param) {
	fetch(`/api/admin/user/${param}`,
		{
			method: 'get'
		}).then(function(res) {
			return res.json();
		}).then(function(result) {
			
			document.getElementById('userId').setAttribute('value', result.uid);

		})
}
function getCategory(param) {
	fetch(`/api/admin/category/${param}`,
		{
			method: 'get'
		}).then(function(res) {
			return res.json();
		}).then(function(result) {
			document.getElementById('editIdCategory').setAttribute('value', result.cid);
			document.getElementById('editNameCategory').setAttribute('value', result.cname);
			document.getElementById('ImageCategory').setAttribute('value', result.cimage);
			document.getElementById('editImageCategory').setAttribute('src', result.cimage);
			

		})
}
function getShop(param) {
	fetch(`/api/admin/shop/${param}`,
		{
			method: 'get'
		}).then(function(res) {
			return res.json();
		}).then(function(result) {
			document.getElementById('editIdShop').setAttribute('value', result.shopId);
			document.getElementById('editNameShop').setAttribute('value', result.shopName);
			document.getElementById('ImageShop').setAttribute('value', result.shopImage);
			document.getElementById('editImageShop').setAttribute('src', result.shopImage);
			

		})
}
var currentDate = new Date().toJSON().slice(0, 10);
function updateShop() {
	let id=document.getElementById("editIdShop").value
	
	
	fetch(`/api/admin/shop/${id}`,
		{
			method: 'put',
			body: JSON.stringify({
				"shopName":document.getElementById('editNameShop').value,
				"shopImage":document.getElementById('ImageShop').value,
				"updateAt":currentDate
			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			alert("thành công")
			console.log(data)
			location.reload()
		})

}
function updateCategory() {
	let id=document.getElementById("editIdCategory").value
	
	
	fetch(`/api/admin/category/${id}`,
		{
			method: 'put',
			body: JSON.stringify({
				"cname":document.getElementById('editNameCategory').value,
				"cimage":document.getElementById('ImageCategory').value
			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			alert("thành công")
			console.log(data)
			location.reload()
		})

}
function addCategory() {

	
	fetch("/api/admin/category",
		{

			method: 'post',
			body: JSON.stringify({
				"cname": document.getElementById("CategoryName").value,
				"cimage": document.getElementById("imageCategory").value,
				
				
				
			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			console.log(data)
			
			location.reload();

		})
}
function updateUser() {
	let id=document.getElementById("userId").value
	let editStatusUser = document.getElementById("editStatusUser").value
	
	fetch(`/api/admin/user/${id}`,
		{
			method: 'put',
			body: JSON.stringify({
				"isActive":editStatusUser
			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			alert("thành công")
			console.log(data)
			location.reload()
		})

}

//product
function addProduct() {

	let cateId = document.getElementById("category").value
	alert(cateId)
	fetch("/api/managerProduct",
		{

			method: 'post',
			body: JSON.stringify({
				"pname": document.getElementById("pName").value,
				"pprice": document.getElementById("pPrice").value,
				"pimage": document.getElementById("image").value,
				"pdescription": document.getElementById("pDescription").value,
				"pquantity": document.getElementById("pQuantity").value,
				"category": {
					"cid": cateId
				}
				
			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			console.log(data)
			/*
			let trProduct=document.createElement('tr');
				 trProduct.className="product${data.pid}";
			
			trProduct.innerHTML=`<td>${data.pname}</td>
											<td><img style="width: 150px; height: 150px; object-fit: contain;"
															id="images" src="${data.pimage}" alt=" ${data.pname}"></td>
					
											<td>${data.pprice}đ</td>
											<td><a href="" onclick="editProductModal(${data.pid})"
												class="edit" data-toggle="modal"><i
													class='bx bxs-pencil'></i></a> <a
												onclick="deleteProduct(${data.pid})" style="cursor: pointer;"
												class="delete" data-toggle="modal"><i
													class='bx bx-trash'></i></a></td>`;
											
										
			tbodyP.appendChild(trProduct);
			*/
			location.reload();

		})
}
function editProduct(param) {
	fetch(`/api/managerProduct/${param}`,
		{
			method: 'get'
		}).then(function(res) {
			return res.json();
		}).then(function(result) {
			document.getElementById('editpId').setAttribute('value', result.pid);
			document.getElementById('editshopId').setAttribute('value', result.shop.shopId);
			document.getElementById('editpName').setAttribute('value', result.pname);

			document.getElementById('editpImage').setAttribute('src', result.pimage);
			document.getElementById('textEdit').setAttribute('value', result.pimage);
			document.getElementById('editpPrice').setAttribute('value', result.pprice);
			document.getElementById('editpQuantity').setAttribute('value', result.pquantity);
			document.getElementById('editpDescription').innerHTML = result.pdescription;
			var select = document.getElementById('editpCategory');
			var option;
			for (var i = 0; i < select.options.length; i++) {
				option = select.options[i];

				if (option.text === result.category.cname) {
					option.setAttribute('selected', true);
					// For a single select, the job's done
					return;
				}
			}
		})
}

function updateProduct() {
	let cateId = document.getElementById("editpCategory").value
	var id = document.getElementById('editpId').value
	fetch(`/api/managerProduct/${id}`,
		{
			method: 'put',
			body: JSON.stringify({
				"pname": document.getElementById("editpName").value,
				"pprice": document.getElementById("editpPrice").value,
				"pimage": document.getElementById("textEdit").value,
				"pdescription": document.getElementById("editpDescription").value,
				"pquantity": document.getElementById("editpQuantity").value,
				"category": {
					"cid": cateId
				},
				"shop": {
					"shopId": document.getElementById("editshopId").value
				}

			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			alert("thành công")
			console.log(data)
			location.reload()
		})

}

function deleteProduct(productId) {
	if (confirm("Bạn có chắc chắn muốn xóa hay không") == true) {
		fetch(`/api/managerProduct/${productId}`,
			{
				method: 'delete'
			}).then(function(res) {
				return res.json();
			}).then(function(data) {

				alert("xóa thành công")
				//reload trang
				location.reload();


				//hidden row product delete
				//let row = document.getElementById(`product${productId}`)
				//row.style.display = "none"
			})
	}
}

//statistic
//statis
function generateColor()
{
	let r=parseInt(Math.random()*255);
	let g=parseInt(Math.random()*255);
	let b=parseInt(Math.random()*255);
	
	return `rgb(${r},${g},${b})`;
}

function cateChart(id,cateLabels=[],cateInfo=[]) {
	let colors=[]
	
	for(let i=0;i<cateInfo.length;i++)
	{
		colors.push(generateColor());
	}
	
	
	const data = {
		labels: cateLabels,
		datasets: [{
			label: 'Thống kê sản phẩm theo danh mục',
			data:cateInfo ,
			backgroundColor: colors,
			hoverOffset: 4
		}]
	};
	const config = {
		type: 'doughnut',
		data: data,
	};
	
	let ctx=document.getElementById(id).getContext("2d");
	
	new Chart(ctx,config);

}
function ProductChart(id,ProductLabels=[],ProductInfo=[]) {
	let colors=[]
	
	for(let i=0;i<ProductInfo.length;i++)
	{
		colors.push(generateColor());
	}
	
	
	const data = {
		labels: ProductLabels,
		datasets: [{
			label: 'Thống kê doanh thu sản phẩm',
			data:ProductInfo ,
			backgroundColor: colors,
			 borderColor:colors,
			hoverOffset: 4
		}]
	};
	const config = {
		type: 'bar',
		data: data,
	};
	
	let ctx=document.getElementById(id).getContext("2d");
	
	new Chart(ctx,config);

}
function ProductMonthChart(id,ProductMonthLabels=[],ProductMonthInfo=[]) {
	let colors=[]
	
	for(let i=0;i<ProductMonthInfo.length;i++)
	{
		colors.push(generateColor());
	}
	
	
	const data = {
		labels: ProductMonthLabels,
		datasets: [{
			label: 'Thống kê doanh thu sản phẩm theo tháng',
			data:ProductMonthInfo ,
			backgroundColor: colors,
			 borderColor:colors,
			hoverOffset: 4
		}]
	};
	const config = {
		type: 'bar',
		data: data,
	};
	
	let ctx=document.getElementById(id).getContext("2d");
	
	new Chart(ctx,config);

}
function ProductShopChart(id,ProductShopLabels=[],ProductShopInfo=[]) {
	let colors=[]
	
	for(let i=0;i<ProductShopInfo.length;i++)
	{
		colors.push(generateColor());
	}
	
	
	const data = {
		labels: ProductShopLabels,
		datasets: [{
			label: 'Thống kê sản phẩm của các cửa hàng',
			data:ProductShopInfo ,
			backgroundColor: colors,
			 borderColor:colors,
			hoverOffset: 4
		}]
	};
	const config = {
		type: 'bar',
		data: data,
	};
	
	let ctx=document.getElementById(id).getContext("2d");
	
	new Chart(ctx,config);

}



