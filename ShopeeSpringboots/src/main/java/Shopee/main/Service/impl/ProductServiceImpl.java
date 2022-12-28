package Shopee.main.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import Shopee.main.Service.iProductService;
import Shopee.main.entity.Category;
import Shopee.main.entity.Product;
import Shopee.main.entity.shop;
import Shopee.main.repository.productRepository;
@Service
public class ProductServiceImpl implements iProductService {
	@Autowired
	productRepository productRepository;

	@Override
	public <S extends Product> S save(S entity) {
		
			Optional<Product> optional=findById(entity.getPId());
			if(optional.isPresent())
			{
				if(StringUtils.isEmpty(entity.getPImage()))
				{
					entity.setPImage(optional.get().getPImage());
				}else {
					entity.setPImage(entity.getPImage());
				}
			}
			return productRepository.save(entity);
		
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return productRepository.findAll(sort);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public long count() {
		return productRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

	@Override
	public void delete(Product entity) {
		productRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		productRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}

	@Override
	public List<Product> findByPnameContaining(String name) {
		// TODO Auto-generated method stub
		return productRepository.findByPnameContaining(name);
	}

	

	@Override
	public List<Product> GetTop20Product() {
		// TODO Auto-generated method stub
		return productRepository.GetTop20Product();
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.getById(id);
	}

	@Override
	public List<Product> GetTop20ProductBycateId(int cateId) {
		// TODO Auto-generated method stub
		return productRepository.GetTop20ProductBycateId(cateId);
	}

	@Override
	public List<Product> GetProductBycateId(int cateId) {
		// TODO Auto-generated method stub
		return productRepository.GetProductBycateId(cateId);
	}

	@Override
	public Page<Product> findByCategory(Category category, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findByCategory(category, pageable);
	}

	@Override
	public int countByCateId(int cateId) {
		// TODO Auto-generated method stub
		return productRepository.countByCateId(cateId);
	}

	@Override
	public Page<Product> findBypNameContaining(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findBypNameContaining(name, pageable);
	}

	@Override
	public List<Product> GetProductByShop(int uId) {
		// TODO Auto-generated method stub
		return productRepository.GetProductByShop(uId);
	}

	@Override
	public int GetShopid(int uId) {
		// TODO Auto-generated method stub
		return productRepository.GetShopid(uId);
	}

	@Override
	public int countByShop(int uId) {
		// TODO Auto-generated method stub
		return productRepository.countByShop(uId);
	}

	@Override
	public Page<Product> findByShop(shop shop, Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findByShop(shop, pageable);
	}

	@Override
	public Product getOne(Integer id) {
		return productRepository.getOne(id);
	}

	

	

	

	
	
	
	
}
