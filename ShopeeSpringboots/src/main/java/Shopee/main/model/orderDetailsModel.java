package Shopee.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import Shopee.main.entity.Product;
import Shopee.main.entity.order;
import Shopee.main.entity.orderStatus;
import Shopee.main.entity.shop;

public class orderDetailsModel {
	
	private int id;
	private order order;
	private Product product;
	private shop shop;
	
	private int count;
	
	private Long totalPrice;
	
	
	private orderStatus orderstatus;
	
	private Date createAt;
}
