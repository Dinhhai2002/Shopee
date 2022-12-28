package Shopee.main.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Shopee.main.Service.iCategoryService;
import Shopee.main.entity.Category;
import Shopee.main.repository.categoryRepository;

@Service
public class CategoryServiceImpl implements iCategoryService  {
	@Autowired
	categoryRepository categoryRepository;

	@Override
	public <S extends Category> S save(S entity) {
		return categoryRepository.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryRepository.findAll(sort);
	}

	@Override
	public List<Category> findAllById(Iterable<Integer> ids) {
		return categoryRepository.findAllById(ids);
	}

	@Override
	public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
		return categoryRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Category> findById(Integer id) {
		return categoryRepository.findById(id);
	}

	

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		categoryRepository.deleteById(id);
	}

	

	@Override
	public void delete(Category entity) {
		categoryRepository.delete(entity);
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}

	@Override
	public List<Category> findByCnameContaining(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCnameContaining(name);
	}

	@Override
	public Page<Category> findByCnameContaining(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCnameContaining(name, pageable);
	}

	@Override
	public Category getById(Integer id) {
		return categoryRepository.getById(id);
	}
	
	
}
