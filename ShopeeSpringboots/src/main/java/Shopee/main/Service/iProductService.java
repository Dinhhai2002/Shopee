package Shopee.main.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import Shopee.main.entity.Category;
import Shopee.main.entity.Product;
import Shopee.main.entity.shop;

public interface iProductService {

	void deleteAll();

	void deleteAllById(Iterable<? extends Integer> ids);

	void delete(Product entity);

	void deleteById(Integer id);

	long count();

	Optional<Product> findById(Integer id);

	List<Product> findAll(Sort sort);

	Page<Product> findAll(Pageable pageable);

	List<Product> findAll();

	<S extends Product> S save(S entity);
	List<Product> findByPnameContaining(String name);
	
	Page<Product> findBypNameContaining(String name,Pageable pageable);
	List<Product> GetTop20Product();

	Product getById(Integer id);
	List<Product> GetTop20ProductBycateId(int cateId);
	List<Product> GetProductBycateId(int cateId);
	Page<Product> findByCategory(Category category,Pageable pageable);
	int countByCateId(int cateId);
	List<Product> GetProductByShop(int uId);
	int GetShopid(int uId);
	int countByShop(int uId);
	Page<Product> findByShop(shop shop,Pageable pageable);

	Product getOne(Integer id);

}
