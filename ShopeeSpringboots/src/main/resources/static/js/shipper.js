function editOrder(id)
{
	let idStatus=document.getElementById(`orderStatus${id}`).value;
	fetch(`/api/orderShipper/${id}`,
	{
		method:'put',
		body:JSON.stringify({
			"id":idStatus,
			"statusName":"Đang giao hàng"
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

