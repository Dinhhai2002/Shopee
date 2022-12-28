package Shopee.main.Service;

import Shopee.main.entity.orderStatus;

public interface iOrderStatusService {

	orderStatus getById(Integer id);

	long count();

	<S extends orderStatus> S save(S entity);
	
}
