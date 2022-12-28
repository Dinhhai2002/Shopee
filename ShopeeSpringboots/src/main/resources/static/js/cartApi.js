/**
 * 
 
 */


function addToCart(id, name, image, price) {
	event.preventDefault();
	let quantityInput=document.getElementById("quantityInput")
	console.log(quantityInput.value)
	fetch("/api/cart",
		{
			method: 'post',
			body: JSON.stringify({
				"productId": id,
				"productName": name,
				"productImage": image,
				"price": price,
				"quantity": document.getElementById("quantityInput").value
			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			let counter = document.getElementById("cartCounter")
			counter.innerText = data
		})
}


function updatetoCart(obj, productId) {
	event.preventDefault();
	fetch("/api/cart",
		{
			method: 'put',
			body: JSON.stringify({
				"productId": productId,
				"productName": "",
				"productImage": "",
				"price": 0,
				"quantity": obj.value
			}),
			headers: {
				"Content-Type": "application/json"
			}
		}).then(function(res) {
			return res.json();
		}).then(function(data) {
			let counter = document.getElementById("cartCounter")
			counter.innerText = data.counter
			let amount = document.getElementById("amount")
			amount.innerText = data.amount + "VNĐ"
		})
}

function deleteToCart(productId) {
	if (confirm("Bạn có chắc chắn muốn xóa hay không") == true) {
		fetch(`/api/cart/${productId}`,
			{
				method: 'delete'
			}).then(function(res) {
				return res.json();
			}).then(function(data) {
				let counter = document.getElementById("cartCounter")
				counter.innerText = data.counter
				let amount = document.getElementById("amount")
				amount.innerText = data.amount + "VNĐ"

				//reload trang
				//location.reload();


				//hidden row product delete
				let row = document.getElementById(`product${productId}`)
				row.style.display = "none"
			})
	}
}

function pay() {
	if (confirm("Bạn có chắc chắn muốn thanh toán?") == true) {
		fetch("/api/pay",
			{
				method: "post"
			}).then(function(res) {
				return res.json();
			}).then(function(data) {
				console.log(data)
				location.reload()
				alert("Đặt hàng thành công")
			})
	}
}