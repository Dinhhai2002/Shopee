package Shopee.main.Service.impl;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Shopee.main.Service.iOrderService;
import Shopee.main.Service.iOrderStatusService;
import Shopee.main.entity.order;
import Shopee.main.entity.orderDetail;
import Shopee.main.entity.user;
import Shopee.main.model.CartModel;
import Shopee.main.repository.orderDetailRepository;
import Shopee.main.repository.orderRepository;
import Shopee.main.repository.productRepository;
import Shopee.main.repository.userRepository;
import Shopee.main.utils.CartUtils;
@Service
public class orderServiceImpl implements iOrderService{
	@Autowired
	orderRepository orderRepository;
	@Autowired
	orderDetailRepository orderDetailRepository;
	@Autowired
	userRepository userRepository;
	@Autowired
	iOrderStatusService orderStatusService;
	@Autowired
	productRepository productRepository;
	@Override
	public boolean addReceipt(Map<Integer, CartModel> cart, HttpSession httpsession) {
		// TODO Auto-generated method stub
		if(cart!=null && (user)httpsession.getAttribute("acc")!=null)
		{
			try {
				user acc=(user)httpsession.getAttribute("acc");
				
				order order=new order();
				order.setUser(userRepository.getById(acc.getUId()));
				order.setUName(acc.getUName());
				order.setUAddress(acc.getUAddress());
				order.setUPhone(acc.getUPhone());
				order.setCreateAt(new Date());
				
				
				Map<String,String> stats=CartUtils.cartStats(cart);
				order.setAmountFromUse(Long.parseLong(stats.get("amount")));
				
				orderRepository.save(order);
				
				for(CartModel c: cart.values())
				{
					orderDetail d=new orderDetail();
					d.setOrder(order);
					d.setProduct(productRepository.getById(c.getProductId()));
					d.setShop(productRepository.getById(c.getProductId()).getShop());
					d.setCount(c.getQuantity());
					d.setTotalPrice(c.getPrice());
					d.setCreateAt(new Date());
					d.setOrderstatus(orderStatusService.getById(1));
					
					orderDetailRepository.save(d);
				}
				return true;
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
