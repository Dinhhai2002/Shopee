package Shopee.main.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Shopee.main.Service.iOrderService;
import Shopee.main.model.CartModel;
import Shopee.main.utils.CartUtils;

@RestController
public class ApiCartController {

	@Autowired
	iOrderService orderService;

	@PostMapping("/api/cart")
	public int addToCart(@RequestBody CartModel params, HttpSession session) {
		Map<Integer, CartModel> cart = (Map<Integer, CartModel>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}

		int productId = params.getProductId();
		if (cart.containsKey(productId) == true) {
			// san pham da co trong gio
			CartModel c = cart.get(productId);
			c.setQuantity(c.getQuantity() + 1);
		} else {
			cart.put(productId, params);
		}
		session.setAttribute("cart", cart);

		return CartUtils.countCart(cart);

	}
	@PutMapping("/api/cart")
	public ResponseEntity<Map<String,String>> updateToCart(@RequestBody CartModel params, HttpSession session) {
		Map<Integer, CartModel> cart = (Map<Integer, CartModel>) session.getAttribute("cart");
		if (cart == null) {
			cart = new HashMap<>();
		}

		int productId = params.getProductId();
		if (cart.containsKey(productId) == true) {
			// san pham da co trong gio
			CartModel c = cart.get(productId);
			c.setQuantity(params.getQuantity());
		} else {
			cart.put(productId, params);
		}
		session.setAttribute("cart", cart);

		return new ResponseEntity<>(CartUtils.cartStats(cart),HttpStatus.OK);

	}
	
	@DeleteMapping("/api/cart/{productId}")
	public ResponseEntity<Map<String,String>> deleteToCart(@PathVariable(value="productId")int productId,HttpSession session)
	{
		Map<Integer, CartModel> cart = (Map<Integer, CartModel>) session.getAttribute("cart");
		//Kiểm tra cart khác null và trong cart có chứa id của sản phẩm cần xóa
		if(cart!=null && cart.containsKey(productId))
		{
			cart.remove(productId);
			session.setAttribute("cart", cart);
		}
		return new ResponseEntity<>(CartUtils.cartStats(cart),HttpStatus.OK);
	}
	
	
	@PostMapping("/api/pay")
	public HttpStatus pay(HttpSession session)
	{
		if(orderService.addReceipt((Map<Integer, CartModel>) session.getAttribute("cart"), session)==true)
		{
			session.removeAttribute("cart");
			return HttpStatus.OK;
		}
		return HttpStatus.BAD_REQUEST;
	}
}
