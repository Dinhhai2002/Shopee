package Shopee.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import Shopee.main.Service.iOrderDetailService;
import Shopee.main.entity.orderDetail;
import Shopee.main.entity.user;
@Controller
public class shipperController {
	@Autowired
	HttpSession session;
	
	@Autowired
	iOrderDetailService orderDetailService;
	
	@GetMapping("/shipper/orderShipper")
	public String orderManager(Model model)
	{
		
		user acc=(user)session.getAttribute("acc");
		if(acc==null)
		{
			return "redirect:/login";
		}
		else
		{
			List<orderDetail> list=orderDetailService.findAll();
			model.addAttribute("listO", list);
			
			return "ShipperAdmin";
		}
		
	}
	
	
}
