package Shopee.main.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Shopee.main.Service.iOrderDetailService;
import Shopee.main.entity.orderDetail;
import Shopee.main.entity.orderStatus;
import Shopee.main.entity.shop;
import Shopee.main.repository.orderDetailRepository;
import Shopee.main.repository.orderStatusRepository;

@Service
public class orderDetailServiceImpl implements iOrderDetailService {
	@Autowired
	orderDetailRepository orderDetailRepository;
	@Autowired
	orderStatusRepository orderStatusRepository;

	@Override
	public <S extends orderDetail> S save(S entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public List<orderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Page<orderDetail> findAll(Pageable pageable) {
		return orderDetailRepository.findAll(pageable);
	}

	@Override
	public List<orderDetail> findAll(Sort sort) {
		return orderDetailRepository.findAll(sort);
	}

	@Override
	public long count() {
		return orderDetailRepository.count();
	}

	@Override
	public orderDetail getById(Integer id) {
		return orderDetailRepository.getById(id);
	}

	@Override
	public List<orderDetail> getOrderDetailByUid(int userId) {
		// TODO Auto-generated method stub
		
		return orderDetailRepository.getOrderDetailByUid(userId);
	}

	@Override
	public List<orderDetail> findByShop(shop shop) {
		// TODO Auto-generated method stub
		return orderDetailRepository.findByShop(shop);
	}

	@Override
	public Optional<orderDetail> findById(Integer id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public orderDetail getOne(Integer id) {
		return orderDetailRepository.getOne(id);
	}

	@Override
	public List<orderDetail> findTop10() {
		// TODO Auto-generated method stub
		return orderDetailRepository.findTop10();
	}
	
	
	
	
}
