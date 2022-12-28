package Shopee.main.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Shopee.main.Service.iShopService;
import Shopee.main.entity.Product;
import Shopee.main.entity.shop;
import Shopee.main.entity.user;
import Shopee.main.repository.shopRepository;
@Service
public class shopServiceImpl implements iShopService {
	@Autowired
	shopRepository shopRepository;

	

	@Override
	public <S extends shop> S save(S entity) {
		return shopRepository.save(entity);
	}

	@Override
	public Optional<shop> findById(Integer id) {
		return shopRepository.findById(id);
	}

	@Override
	public long count() {
		return shopRepository.count();
	}

	@Override
	public shop getById(Integer id) {
		return shopRepository.getById(id);
	}

	@Override
	public shop findByUser(user user) {
		// TODO Auto-generated method stub
		return shopRepository.findByUser(user);
	}

	@Override
	public List<shop> findAll() {
		return shopRepository.findAll();
	}

	@Override
	public Page<shop> findAll(Pageable pageable) {
		return shopRepository.findAll(pageable);
	}

	

	

	
	
	
}
