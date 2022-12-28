package Shopee.main.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Shopee.main.Service.envaluateService;
import Shopee.main.entity.envaluate;
import Shopee.main.repository.envaluateRepository;
@Service
public class envaluateServiceImpl implements envaluateService{
	@Autowired
	envaluateRepository envaluateRepository;

	@Override
	public <S extends envaluate> S save(S entity) {
		return envaluateRepository.save(entity);
	}

	@Override
	public List<envaluate> findAll() {
		return envaluateRepository.findAll();
	}

	@Override
	public List<envaluate> findAllById(Iterable<Integer> ids) {
		return envaluateRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return envaluateRepository.count();
	}

	@Override
	public envaluate getById(Integer id) {
		return envaluateRepository.getById(id);
	}

	@Override
	public void deleteAll() {
		envaluateRepository.deleteAll();
	}

	@Override
	public List<envaluate> findAllBypId(int pId) {
		// TODO Auto-generated method stub
		return envaluateRepository.findAllBypId(pId);
	}
	
	
}
