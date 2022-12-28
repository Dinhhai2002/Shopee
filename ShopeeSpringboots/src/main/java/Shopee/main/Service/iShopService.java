package Shopee.main.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Shopee.main.entity.Product;
import Shopee.main.entity.shop;
import Shopee.main.entity.user;

public interface iShopService {

	long count();

	Optional<shop> findById(Integer id);

	<S extends shop> S save(S entity);

	shop findByUser(user user);

	shop getById(Integer id);

	Page<shop> findAll(Pageable pageable);

	List<shop> findAll();
	

}
