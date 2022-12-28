package Shopee.main.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import Shopee.main.Service.iCategoryService;
import Shopee.main.Service.iShopService;
import Shopee.main.Service.iUserService;
import Shopee.main.entity.Category;
import Shopee.main.entity.Product;
import Shopee.main.entity.shop;
import Shopee.main.entity.user;

@RestController
public class apiAdminController {
	@Autowired
	iUserService userService;
	@Autowired
	iCategoryService categoryService;
	@Autowired
	iShopService shopService;
	
	//user
	 @RequestMapping(value = "/api/admin/user/{id}", method = RequestMethod.GET)
		public user findUser(@PathVariable("id") int id) {
		
			user user= userService.getById(id);
			if(user == null) {
				ResponseEntity.notFound().build();
			}
			return user;
		}
	 @PutMapping(value="/api/admin/user/{id}")
	 public ResponseEntity<user> updateUser(@PathVariable int id,@RequestBody user bodyuser)
	 {
		 user formUser = userService.getById(id);
		 formUser.setIsActive(bodyuser.getIsActive());
		    userService.save(formUser);
		  
		    return ResponseEntity.ok(formUser);
	 }
	
	 //end user
	 
	 //start category
	 @RequestMapping(value = "/api/admin/category/{id}", method = RequestMethod.GET)
		public Category findCategory(@PathVariable("id") int id) {
		
			Category category= categoryService.getById(id);
			if(category == null) {
				ResponseEntity.notFound().build();
			}
			return category;
		}
	 @PutMapping(value="/api/admin/category/{id}")
	 public ResponseEntity<Category> updateCategory(@PathVariable int id,@RequestBody Category bodycategory)
	 {
		 Category formCategory = categoryService.getById(id);
		 formCategory.setCName(bodycategory.getCName());
		 formCategory.setCImage(bodycategory.getCImage());
		    categoryService.save(formCategory);
		  
		    return ResponseEntity.ok(formCategory);
	 }
	 @PostMapping(value = "/api/admin/category")
		public Category saveContact(@Valid @RequestBody Category category) {
		 	if(category==null)
		 	{
		 		ResponseEntity.notFound().build();
		 	}
		 	
			return categoryService.save(category);
		}
	 //shop
	 @RequestMapping(value = "/api/admin/shop/{id}", method = RequestMethod.GET)
		public shop findShop(@PathVariable("id") int id) {
		
			shop shop= shopService.getById(id);
			if(shop == null) {
				ResponseEntity.notFound().build();
			}
			return shop;
		}
	 @PutMapping(value="/api/admin/shop/{id}")
	 public ResponseEntity<shop> updateShop(@PathVariable int id,@RequestBody shop bodyShop)
	 {
		 shop formShop = shopService.getById(id);
		 formShop.setShopName(bodyShop.getShopName());
		 formShop.setShopImage(bodyShop.getShopImage());
		
		 
		    shopService.save(formShop);
		  
		    return ResponseEntity.ok(formShop);
	 }
	 @PostMapping(value = "/api/admin/shop")
		public shop postShop(@Valid @RequestBody shop shop) {
		 	if(shop==null)
		 	{
		 		ResponseEntity.notFound().build();
		 	}
		 	
			return shopService.save(shop);
		}

		
}
