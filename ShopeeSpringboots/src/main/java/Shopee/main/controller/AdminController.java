package Shopee.main.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Shopee.main.Service.iCategoryService;
import Shopee.main.Service.iOrderDetailService;
import Shopee.main.Service.iProductService;
import Shopee.main.Service.iShopService;
import Shopee.main.Service.iStatsService;
import Shopee.main.Service.iUserService;
import Shopee.main.entity.Category;
import Shopee.main.entity.Product;
import Shopee.main.entity.orderDetail;
import Shopee.main.entity.shop;
import Shopee.main.entity.user;
import Shopee.main.model.CategoryModel;

@Controller
@RequestMapping(value = { "/admin" })
public class AdminController {

	@Autowired
	iCategoryService categoryService;
	@Autowired
	iProductService productService;
	@Autowired
	iShopService shopService;
	@Autowired
	iUserService userService;
	@Autowired
	iStatsService statsService;
	@Autowired
	iOrderDetailService orderDetailService;

	@GetMapping("")
	public String Admin(Model model) {
		try {
			long countUser = userService.count();
			long countShop = shopService.count();
			long countProduct = productService.count();
			
			 long countRevenue = statsService.countRevenue();

			model.addAttribute("totalUser", countUser);
			model.addAttribute("totalShop", countShop);
			model.addAttribute("totalProduct", countProduct);
			
			
				model.addAttribute("countRevenue", countRevenue);
			
			

			List<orderDetail> list = orderDetailService.findTop10();
			List<user> listUser = userService.getTop10();
			model.addAttribute("listSelectOrderDetail", list);
			model.addAttribute("listUser", listUser);
			return "/admin/Admin";
		}catch (Exception e) {
			e.printStackTrace();
			return "/admin/Admin";
		}
		
		
	}

	@GetMapping("/user")
	public String userAdmin(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		int count = (int) userService.count();
		int currentpage = page.orElse(1);
		int pageSize = size.orElse(3);
		Pageable pageable = PageRequest.of(currentpage - 1, pageSize, Sort.by("uId"));

		Page<user> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = userService.findByuNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = userService.findAll(pageable);
		}

		int totalPages = resultPage.getTotalPages();
		if (totalPages > 0) {
			int start = Math.max(1, currentpage - 2);
			int end = Math.min(currentpage + 2, totalPages);
			if (totalPages > count) {
				if (end == totalPages)
					start = end - count;
				else if (start == 1)
					end = start + count;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("userPage", resultPage);
		return "/admin/customerAdmin";
	}
	@GetMapping("/product")
	public String product(Model model,@RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		List<Product> listP = productService.findAll();
		List<Category> listCC = categoryService.findAll();
		int count = (int) productService.count();
		int currentpage = page.orElse(1);
		int pageSize = size.orElse(3);
		Pageable pageable = PageRequest.of(currentpage - 1, pageSize, Sort.by("pId"));

		Page<Product> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = productService.findBypNameContaining(name, pageable);
			model.addAttribute("name", name);
		} else {
			resultPage = productService.findAll(pageable);
		}

		int totalPages = resultPage.getTotalPages();
		System.out.println("Vao day nha");
		if (totalPages > 0) {
			int start = Math.max(1, currentpage - 2);
			int end = Math.min(currentpage + 2, totalPages);
			if (totalPages > count) {
				if (end == totalPages)
					start = end - count;
				else if (start == 1)
					end = start + count;
			}
			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		
		model.addAttribute("productPage", resultPage);
		model.addAttribute("listC", listCC); 
		
		return "/admin/productAdmin";
	}
	
	
	@GetMapping("/category")
	public String CategoryAdmin(Model model)
	{
		List<Category> listC=categoryService.findAll();
		model.addAttribute("listCategory", listC);
		
		return "/admin/categoryAdmin";
	}
	@GetMapping("/shop")
	public String ShopAdmin(Model model)
	{
		List<shop> listC=shopService.findAll();
		model.addAttribute("listShop", listC);
		
		return "/admin/ShopAdmin";
	}
	@GetMapping("/statistic/category")
	public String stats(Model model,@ModelAttribute(name="productName") String productName,@ModelAttribute("fromDate") String fromDate,
			@ModelAttribute("toDate") String toDate,@ModelAttribute("fromDateMonth") String fromDateMonth,
			@ModelAttribute("toDateMonth") String toDateMonth)
	{
		System.out.println(productName);
		List<Object[]> list=statsService.countProductBycategory();
		List<Object[]> listP=statsService.statictisProduct(productName,fromDate,toDate);
		List<Object[]> listpByMonth=statsService.statictisProductByMonth(fromDateMonth,toDateMonth );
		
		model.addAttribute("list", list);
		model.addAttribute("listP", listP);
		model.addAttribute("listPMonth", listpByMonth);
		return "/admin/statisticAdmin";
	}
	@GetMapping("/statistic/product")
	public String statsProduct(Model model,@ModelAttribute(name="productName") String productName,@ModelAttribute("fromDate") String fromDate,
			@ModelAttribute("toDate") String toDate,@ModelAttribute("fromDateMonth") String fromDateMonth,
			@ModelAttribute("toDateMonth") String toDateMonth)
	{
		System.out.println(productName);
		//List<Object[]> list=statsService.countProductBycategory();
		List<Object[]> listP=statsService.statictisProduct(productName,fromDate,toDate);
		//List<Object[]> listpByMonth=statsService.statictisProductByMonth(fromDateMonth,toDateMonth );
		
		//model.addAttribute("list", list);
		model.addAttribute("listP", listP);
		//model.addAttribute("listPMonth", listpByMonth);
		return "/admin/statisticProductAdmin";
	}
	@GetMapping("/statistic/month")
	public String statsMonth(Model model,@ModelAttribute(name="productName") String productName,@ModelAttribute("fromDate") String fromDate,
			@ModelAttribute("toDate") String toDate,@ModelAttribute("fromDateMonth") String fromDateMonth,
			@ModelAttribute("toDateMonth") String toDateMonth)
	{
		System.out.println(productName);
		//List<Object[]> list=statsService.countProductBycategory();
		//List<Object[]> listP=statsService.statictisProduct(productName,fromDate,toDate);
		List<Object[]> listpByMonth=statsService.statictisProductByMonth(fromDateMonth,toDateMonth );
		
		//model.addAttribute("list", list);
		//model.addAttribute("listP", listP);
		model.addAttribute("listPMonth", listpByMonth);
		return "/admin/statisticMonthAdmin";
	}
	@GetMapping("/statistic/shop")
	public String statsShop(Model model,@ModelAttribute(name="productName") String productName,@ModelAttribute("fromDate") String fromDate,
			@ModelAttribute("toDate") String toDate,@ModelAttribute("fromDateMonth") String fromDateMonth,
			@ModelAttribute("toDateMonth") String toDateMonth)
	{
		System.out.println(productName);
		List<Object[]> listC=statsService.countProductBycategory();
		List<Object[]> listS=statsService.countProductByShop();
		List<Object[]> listP=statsService.statictisProduct(productName,fromDate,toDate);
		List<Object[]> listpByMonth=statsService.statictisProductByMonth(fromDateMonth,toDateMonth );
		
		model.addAttribute("list", listC);
		model.addAttribute("listS", listS);
		model.addAttribute("listP", listP);
		model.addAttribute("listPMonth", listpByMonth);
		return "/admin/statisticShopAdmin";
	}
}
