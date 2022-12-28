package Shopee.main.repository;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Shopee.main.entity.Category;
import Shopee.main.entity.order;
import Shopee.main.model.CartModel;
@Repository
public interface orderRepository extends JpaRepository<order, Integer > {
	//public boolean addReceipt(Map<Integer, CartModel> cart,HttpSession httpsession);
}
