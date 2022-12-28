/**
 * 
 */

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
				},
				"shop": {
					"shopId": document.getElementById("shopId").value
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
var editmodel = document.getElementById("editShopModal")
var btn_model = document.getElementById("btn_model")
var btn_close = document.getElementById("btn_close")
btn_model.addEventListener("click", function() {
	editmodel.classList.add("in");
	editmodel.style.display = "block"
})
btn_close.addEventListener("click", function() {
	editmodel.classList.remove("in");
	editmodel.style.display = "none"
})



function editProductModal(param) {
	editProduct(param);
	$("#editProductModal").modal('show');
}

function editShopModal(param) {
	editShop(param);

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
function editShop(param) {
	fetch(`/api/managerProduct/shop/${param}`,
		{
			method: 'get'
		}).then(function(res) {
			return res.json();
		}).then(function(result) {
			document.getElementById('cateIdShop').setAttribute('value', result.category.cid);
			document.getElementById('userIdShop').setAttribute('value', result.user.uid);
			document.getElementById('editshopId').setAttribute('value', result.shopId);
			document.getElementById('editpNameShop').setAttribute('value', result.shopName);

			document.getElementById('editpImageShop').setAttribute('src', result.shopImage);
			document.getElementById('textEditShop').setAttribute('value', result.shopImage);

			document.getElementById('editShoppDescription').innerHTML = result.shopDecription;

		})
}


function updateShop() {
	let cateId = document.getElementById("cateIdShop").value
	var id = document.getElementById('editshopId').value
	fetch(`/api/managerProduct/shop/${id}`,
		{
			method: 'put',
			body: JSON.stringify({
				"shopName": document.getElementById("editpNameShop").value,
				"shopImage": document.getElementById("textEditShop").value,
				"shopDecription": document.getElementById("editShoppDescription").value,
				"category": {
					"cid": cateId
				},
				"user": {
					"uid": document.getElementById("userIdShop").value
				},
				"active": true,
				"delete": false
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

//update product
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
//api Order


function editOrder(id)
{
	fetch(`/api/managerProduct/orderShop/${id}`,
	{
		method:'put',
		body:JSON.stringify({
			"id":1,
			"statusName":"Chờ shipper nhận đơn"
		}),
		headers: {
				"Content-Type": "application/json"
			}
	}).then(function(res) {
				return res.json();
			}).then(function(data) {

				//reload trang
				location.reload();

			})
}
function editOrderReject(id)
{
	fetch(`/api/managerProduct/orderShop/${id}`,
	{
		method:'put',
		body:JSON.stringify({
			"id":6,
			"statusName":"Chờ shipper nhận đơn"
		}),
		headers: {
				"Content-Type": "application/json"
			}
	}).then(function(res) {
				return res.json();
			}).then(function(data) {

				//reload trang
				location.reload();

			})
}

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