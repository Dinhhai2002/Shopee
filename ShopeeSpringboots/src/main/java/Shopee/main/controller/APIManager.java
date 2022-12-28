package Shopee.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudinary.Cloudinary;

import Shopee.main.Service.IStorageService;
import Shopee.main.Service.iCategoryService;
import Shopee.main.Service.iOrderDetailService;
import Shopee.main.Service.iOrderStatusService;
import Shopee.main.Service.iProductService;
import Shopee.main.Service.iShopService;
import Shopee.main.Service.iUserService;
import Shopee.main.entity.Category;
import Shopee.main.entity.Product;
import Shopee.main.entity.orderDetail;
import Shopee.main.entity.orderStatus;
import Shopee.main.entity.shop;
import Shopee.main.entity.user;

@RestController
public class APIManager {
	@Autowired
	iProductService productService;
	@Autowired
	iCategoryService categoryService;
	@Autowired
	iShopService shopService;
	@Autowired
	Cloudinary cloudinary;
	@Autowired
	IStorageService storageService;
	@Autowired
	iOrderDetailService orderDetailService;
	@Autowired
	iOrderStatusService orderStatusService;
	@Autowired
	iUserService userService;
	
	@DateTimeFormat
	//hiển thị hình ảnh
	
	 @RequestMapping(value = "/api/managerProduct/{id}", method = RequestMethod.GET)
	public Product findContact(@PathVariable("id") int id) {
	
		Product product= productService.getOne(id);
		if(product == null) {
			ResponseEntity.notFound().build();
		}
		return product;
	}
	 @RequestMapping(value = "/api/managerProduct/shop/{id}", method = RequestMethod.GET)
		public shop findShop(@PathVariable("id") int id) {
		
			shop shop= shopService.getById(id);
			if(shop == null) {
				ResponseEntity.notFound().build();
			}
			return shop;
		}
	
	 @PostMapping(value = "/api/managerProduct")
		public Product saveContact(@Valid @RequestBody Product product) {
		 	if(product.getCategory()!=null)
		 	{
		 		Category category=categoryService.getById(product.getCategory().getCId());
		 		product.setCategory(category);
		 	}
		 	if(product.getShop()!=null)
		 	{
		 		shop shop=shopService.getById(product.getShop().getShopId());
		 		product.setShop(shop);
		 	}
		 	
		 	
		 	
			return productService.save(product);
		}
	 
	 @PutMapping(value="/api/managerProduct/{id}")
	 public ResponseEntity<Product> updateProduct(@PathVariable int id,@RequestBody Product product)
	 {
		 Product formProduct = productService.getOne(id);
		    if(product == null) {
		        return ResponseEntity.notFound().build();
		    }
		    formProduct.setPName(product.getPName());
		    formProduct.setPPrice(product.getPPrice());
		    formProduct.setPImage(product.getPImage());
		    formProduct.setPDescription(product.getPDescription());
		    formProduct.setPQuantity(product.getPQuantity());
		    formProduct.setUpdateAt(product.getUpdateAt());
		    formProduct.setCategory(product.getCategory());
		    
		    Product updateproduct=productService.save(formProduct);
		    return ResponseEntity.ok(updateproduct);
	 }
	 
	 
	 @DeleteMapping(value="/api/managerProduct/{id}")
	 public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") int id)
	 {
		 Product product=productService.getOne(id);
		 if(product == null) {
		        return ResponseEntity.notFound().build();
		    }
		 else {
			 productService.delete(product);
			 return new ResponseEntity<>(product,HttpStatus.OK);
		 }
	 }
	 @PutMapping(value="/api/managerProduct/shop/{id}")
	 public ResponseEntity<shop> updateshop(@PathVariable int id,@RequestBody shop shop)
	 {
		 shop formshop = shopService.getById(id);
		    if(shop == null) {
		        return ResponseEntity.notFound().build();
		    }
		   formshop.setShopName(shop.getShopName());
		   formshop.setShopImage(shop.getShopImage());
		   formshop.setShopDecription(shop.getShopDecription());
		    
		    shop updateshop=shopService.save(formshop);
		    return ResponseEntity.ok(updateshop);
	 }
	 @PutMapping(value="/api/managerProduct/orderShop/{id}")
	 public ResponseEntity<orderDetail> updateOrderShop(@PathVariable int id,@RequestBody orderStatus orderStatus)
	 {
		 
		orderDetail oderdetail=orderDetailService.getOne(id);
		if(orderStatus.getId()==1)
		{
			orderStatus.setId(2);
			orderStatus newEntity=orderStatusService.getById(2);
			oderdetail.setOrderstatus(newEntity);
		}
		else if(orderStatus.getId()==6)
		{
			orderStatus.setId(6);
			orderStatus newEntity=orderStatusService.getById(6);
			oderdetail.setOrderstatus(newEntity);
		}
		
		orderDetailService.save(oderdetail);
		 //set status
		//save orderDetail
		
		    return ResponseEntity.ok(oderdetail);
	 }
	 @PostMapping(value = "/api/managerProduct/registerShop")
		public shop postShop(@Valid @RequestBody shop shop) {
		
			 if(shop==null)
			 	{
			 		ResponseEntity.notFound().build();
			 	}
			 	user acc=userService.getById(shop.getUser().getUId());
			 	if(acc.getIdRole()==3)
			 	{
			 		ResponseEntity.notFound().build();
			 	}
			 	else {
			 		shop.setUser(acc);
			 		acc.setIdRole(3);
			 		userService.save(acc);
				 	
					
			 	}
			 	 return shopService.save(shop);
	
	
		 	
		}
	 
}
