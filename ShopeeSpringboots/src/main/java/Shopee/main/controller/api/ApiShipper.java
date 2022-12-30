package Shopee.main.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Shopee.main.Service.iOrderDetailService;
import Shopee.main.Service.iOrderStatusService;
import Shopee.main.entity.orderDetail;
import Shopee.main.entity.orderStatus;

@RestController
public class ApiShipper {
	@Autowired
	iOrderDetailService orderDetailService;
	@Autowired
	iOrderStatusService orderStatusService;

	@PutMapping(value = "/api/orderShipper/{id}")
	public ResponseEntity<orderDetail> updateOrderShop(@PathVariable int id, @RequestBody orderStatus orderStatus) {

		orderDetail oderdetail = orderDetailService.getOne(id);
		if (orderStatus.getId() == 1) {
			orderStatus.setId(5);
			orderStatus newEntity = orderStatusService.getById(5);
			oderdetail.setOrderstatus(newEntity);
		}
		if (orderStatus.getId() == 2) {

			orderStatus.setId(3);
			orderStatus newEntity = orderStatusService.getById(3);
			oderdetail.setOrderstatus(newEntity);
		} else if (orderStatus.getId() == 3) {
			orderStatus.setId(4);
			orderStatus newEntity = orderStatusService.getById(4);
			oderdetail.setOrderstatus(newEntity);
		}

		orderDetailService.save(oderdetail);
		return ResponseEntity.ok(oderdetail);
	}

}
