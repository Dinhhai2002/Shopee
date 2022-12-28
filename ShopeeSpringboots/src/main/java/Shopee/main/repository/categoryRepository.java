package Shopee.main.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Shopee.main.entity.Category;


@Repository
public interface categoryRepository extends JpaRepository<Category, Integer > {
	
	@Query(value="select * from Category c where c.cName like %?1%",nativeQuery = true)
	List<Category> findByCnameContaining(String name);
	
	@Query(value="select e from Category e ")
	Page<Category> findByCnameContaining(String name,Pageable pageable);
}
