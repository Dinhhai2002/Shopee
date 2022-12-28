package Shopee.main.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import Shopee.main.entity.Category;

public interface iCategoryService {

	void deleteAll();

	void delete(Category entity);

	void deleteById(Integer id);

	long count();

	

	<S extends Category> Page<S> findAll(Example<S> example, Pageable pageable);

	List<Category> findAllById(Iterable<Integer> ids);

	List<Category> findAll(Sort sort);

	Page<Category> findAll(Pageable pageable);

	List<Category> findAll();

	<S extends Category> S save(S entity);

	List<Category> findByCnameContaining(String name);

	Optional<Category> findById(Integer id);
	Page<Category> findByCnameContaining(String name,Pageable pageable);

	Category getById(Integer id);

}
