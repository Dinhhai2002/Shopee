package Shopee.main.controller;

import java.util.Map;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import Shopee.main.Service.iOrderDetailService;
import Shopee.main.entity.orderDetail;
import Shopee.main.entity.user;
import Shopee.main.model.CartModel;
import Shopee.main.utils.CartUtils;

@Controller
public class cartController {
	@Autowired 
	iOrderDetailService orderDetailService;
	
	@GetMapping(value = "/cart")
	public String cart(Model model,HttpSession session) {
		Map<Integer, CartModel> cart = (Map<Integer, CartModel>) session.getAttribute("cart");
		if(cart==null)
		{
			model.addAttribute("carts", null);
		}
		else {
			model.addAttribute("carts", cart.values());
			//model.addAttribute("cartCounter", CartUtils.countCart((Map<Integer, CartModel>) session.getAttribute("cart")));
		}
		model.addAttribute("cartStats", CartUtils.cartStats(cart));
		return "cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model,HttpSession session)
	{
		user acc=(user)session.getAttribute("acc");
		Map<Integer, CartModel> cart = (Map<Integer, CartModel>) session.getAttribute("cart");
		if(acc!=null)
		{
			if(cart==null)
			{
				model.addAttribute("carts", null);
			}
			else {
				model.addAttribute("carts", cart.values());
			}
			
			model.addAttribute("cartStats", CartUtils.cartStats(cart));
			return "checkout";
		}
		else {
			return "redirect:/login";
		}
		
	}
	
	
	@GetMapping("/orderBuy")
	public String orderBuy(Model model,HttpSession session)
	{
		user acc=(user)session.getAttribute("acc");
		if(acc==null)
		{
			return "redirect:/login";
		}
		else {
			
			List<orderDetail> list=orderDetailService.getOrderDetailByUid(acc.getUId());
			model.addAttribute("listO", list);
			return "StoreProduct";
		}
		
		
	}
}
