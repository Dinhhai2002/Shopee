package Shopee.main.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Shopee.main.entity.Category;
import Shopee.main.entity.Product;
import Shopee.main.entity.shop;

@Repository
public interface productRepository extends JpaRepository<Product, Integer >{
	Page<Product> findByCategory(Category category,Pageable pageable);
	
	@Query(value="select * from Product c where c.pName like %?1%",nativeQuery = true)
	List<Product> findByPnameContaining(String name);
	
	//@Query(name="select * from product where pName like ? order by pId offset 0 rows fetch next ? rows only")
	Page<Product> findBypNameContaining(String name,Pageable pageable);
	
	@Query(value="select top 20 *  from Product ",nativeQuery = true)
	List<Product> GetTop20Product();
	@Query(value="select top 20 *  from Product where cateId=?1",nativeQuery = true)
	List<Product> GetTop20ProductBycateId(int cateId);
	@Query(value="select top 20 *  from Product where cateId=?1",nativeQuery = true)
	List<Product> GetProductBycateId(int cateId);
	
	
	@Query(value="select count(*) from product where cateId=?1",nativeQuery = true)
	int countByCateId(int cateId);
	
	@Query(value="Select * From product as c join shop as d  on c.shopId=d.shopId where d.uId=?1",nativeQuery = true)
	List<Product> GetProductByShop(int uId);
	@Query(value="Select c.shopId From product as c join shop as d  on c.shopId=d.shopId where d.uId=?1",nativeQuery = true)
	int GetShopid(int uId);
	
	@Query(value="Select count(*) From product as c join shop as d  on c.shopId=d.shopId where d.uId=?1",nativeQuery = true)
	int countByShop(int uId);
	
	
	
	
	Page<Product> findByShop(shop shop,Pageable pageable);
	
	
	
}
