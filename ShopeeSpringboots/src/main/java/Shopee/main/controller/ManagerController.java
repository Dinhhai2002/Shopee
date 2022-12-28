package Shopee.main.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Shopee.main.Service.IStorageService;
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

@Controller
public class ManagerController {
	@Autowired
	iProductService productService;
	@Autowired
	iShopService shopService;
	@Autowired
	iUserService userService;
	@Autowired
	iCategoryService categoryService;
	@Autowired
	HttpSession session;
	@Autowired
	IStorageService storageService;
	@Autowired
	iStatsService statsService;
	@Autowired
	iOrderDetailService orderDetailService;
	
	/*
	 * @GetMapping("/images/{filename:.+}")
	 * 
	 * @ResponseBody public ResponseEntity<Resource> serverFile(@PathVariable String
	 * filename) { Resource file=storageService.loadAsResource(filename); return
	 * ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	 * "attachment;filename=\""+file.getFilename()+"\"").body(file); }
	 */
	
	
	@GetMapping("/managerProduct")
	public String managerProduct(Model model,@RequestParam(name="name",required = false) String name,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size)
	{
		user acc=(user) session.getAttribute("acc");
		if(acc==null) {
			return"redirect:/login";
		}
		else {
			int shopid=productService.GetShopid(acc.getUId());
			shop shopPage=shopService.findByUser(acc);
			int count=productService.countByShop(shopid);
			int currentpage = page.orElse(1);
			int pageSize = size.orElse(3);
			Pageable pageable = PageRequest.of(currentpage - 1, pageSize, Sort.by("pId"));

			Page<Product> resultPage = null;
			if (StringUtils.hasText(name)) {
				resultPage = productService.findBypNameContaining(name, pageable);
				model.addAttribute("name", name);
			}else {
				resultPage = productService.findByShop(shopPage, pageable);
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
			
			model.addAttribute("productPage", resultPage);
			List<Category> listC=categoryService.findAll();
			model.addAttribute("listC", listC);
			
			model.addAttribute("shop", shopPage);
			//shop shop=shopService.findByUser(acc);
			
			return "managerShop/managerProduct";
		}
		
	}
	
//	Thống kê
	@GetMapping("/managerProduct/stats")
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
		return "managerShop/statiscManager";
	}
	
	@GetMapping("/orderManager")
	public String orderManager(Model model,@ModelAttribute(name="shopId")String shopId)
	{
		
		user acc=(user)session.getAttribute("acc");
		if(acc==null)
		{
			return "redirect:/login";
		}
		else
		{
			List<orderDetail> list=orderDetailService.findByShop(shopService.findByUser(acc));
			model.addAttribute("listO", list);
			
			return "managerShop/AdminManager";
		}
		
	}
	@GetMapping("/registerManager")
	public String registerManager(Model model)
	{
		return "registerManagerProduct";
	}
}
