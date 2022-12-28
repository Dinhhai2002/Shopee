package Shopee.main.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import Shopee.main.entity.orderDetail;
import Shopee.main.entity.shop;

public interface iOrderDetailService {
	List<orderDetail> getOrderDetailByUid(int userId);
	List<orderDetail> findByShop(shop shop);
	orderDetail getOne(Integer id);
	Optional<orderDetail> findById(Integer id);
	orderDetail getById(Integer id);
	long count();
	List<orderDetail> findAll(Sort sort);
	Page<orderDetail> findAll(Pageable pageable);
	List<orderDetail> findAll();
	<S extends orderDetail> S save(S entity);
	List<orderDetail> findTop10();
}
