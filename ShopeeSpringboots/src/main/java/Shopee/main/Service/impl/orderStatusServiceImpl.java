package Shopee.main.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Shopee.main.Service.iOrderStatusService;
import Shopee.main.entity.orderStatus;
import Shopee.main.repository.orderStatusRepository;
@Service
public class orderStatusServiceImpl implements iOrderStatusService {
	@Autowired
	orderStatusRepository orderStatusRepository;

	@Override
	public <S extends orderStatus> S save(S entity) {
		return orderStatusRepository.save(entity);
	}

	@Override
	public long count() {
		return orderStatusRepository.count();
	}

	@Override
	public orderStatus getById(Integer id) {
		return orderStatusRepository.getById(id);
	}
	
}
