package Shopee.main.utils;

import java.util.HashMap;
import java.util.Map;

import Shopee.main.model.CartModel;

public class CartUtils {
	public static int countCart(Map<Integer,CartModel> cart)
	{
		int q=0;
		if(cart!=null)
		{
		for(CartModel c: cart.values())
		{
			q+=c.getQuantity();
		}
		}
		return q;
	}
	
	public static Map<String,String> cartStats(Map<Integer,CartModel> cart)
	{
		Long s=0l;
		int q=0;
		if(cart!=null)
		{
			for(CartModel c: cart.values())
			{
				q+=c.getQuantity();
				s+=c.getQuantity()*c.getPrice();
			}
		}
		Map<String,String> kq=new HashMap<>();
		kq.put("counter",String.valueOf(q));
		kq.put("amount", String.valueOf(s));
		return kq;
	}
}
