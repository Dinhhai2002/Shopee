package Shopee.main.Service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import Shopee.main.model.CartModel;

public interface iOrderService {
	public boolean addReceipt(Map<Integer, CartModel> cart,HttpSession httpsession);
}
