package Shopee.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Shopee.main.entity.orderDetail;
import Shopee.main.entity.shop;
@Repository
public interface orderDetailRepository extends JpaRepository<orderDetail, Integer > {
	
	@Query(value="Select * From [order] as c join orderdetail as d  on c.orderId=d.orderId where c.uId=?1",nativeQuery = true)	
	List<orderDetail> getOrderDetailByUid(int userId);
	
	List<orderDetail> findByShop(shop shop);
	
	@Query(value="select top 10 * from orderdetail",nativeQuery = true)
	List<orderDetail> findTop10();
	
}
