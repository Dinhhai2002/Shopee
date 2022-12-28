package Shopee.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Shopee.main.entity.Product;
@Repository
public interface statsRepository extends JpaRepository<Product, Integer >{
	
	@Query(value="select c.cName,count(c.cName)  from product as p join category as c on p.cateId=c.cId group by c.cName",nativeQuery = true)
	public List<Object[]> countProductBycategory();
	@Query(value="select s.shopName,count(s.shopName)  from product as p join shop as s on p.shopId=s.shopId group by s.shopName",nativeQuery = true)
	public List<Object[]> countProductByShop();
	
	@Query(value="select p.pName,SUM(od.totalPrice*od.count) from product as p ,orderdetail as od , [order] o where  p.pId=od.productId and od.orderId=o.orderId and p.pName like %?1%  and od.createAt between CAST(?2 as Date) and CAST(?3 as Date)  group by p.pName",nativeQuery = true)
	public List<Object[]> statictisProduct(String productName,String fromDate,String toDate);
	@Query(value="select FORMAT(od.createAt, 'MM/yyyy') as f,SUM(od.totalPrice*od.count) from product as p ,orderdetail as od , [order] o where  p.pId=od.productId and od.orderId=o.orderId and Month(od.createAt) between Month(Cast(?1 as Date)) and Month(Cast(?2 as Date)) group by FORMAT(od.createAt, 'MM/yyyy')",nativeQuery = true)
	public List<Object[]> statictisProductByMonth(String fromDate,String toDate);
	
	@Query(value="select SUM(od.totalPrice*od.count) from product as p ,orderdetail as od , [order] o where  p.pId=od.productId and od.orderId=o.orderId ",nativeQuery = true)
	public long countRevenue();
}
