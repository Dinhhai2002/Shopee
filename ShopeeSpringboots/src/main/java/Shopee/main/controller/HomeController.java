package Shopee.main.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Shopee.main.Service.envaluateService;
import Shopee.main.Service.iCategoryService;
import Shopee.main.Service.iProductService;
import Shopee.main.entity.Category;
import Shopee.main.entity.Product;
import Shopee.main.entity.envaluate;
import Shopee.main.entity.user;
import Shopee.main.model.CartModel;
import Shopee.main.model.envaluateModel;
import Shopee.main.utils.CartUtils;

@Controller
@ControllerAdvice
public class HomeController {
	@Autowired
	iProductService productService;

	@Autowired
	iCategoryService categoryService;

	@Autowired
	envaluateService envaluateService;
	@Autowired
	HttpSession httpSession;

	@GetMapping("/home")
	public String Home(ModelMap model) {
		List<Product> list = productService.GetTop20Product();

		List<Category> listCC = categoryService.findAll();

		model.addAttribute("listP", list);
		model.addAttribute("listCC", listCC);

		return "home";
	}
	@ModelAttribute
	public void commons(Model model,HttpSession session)
	{
		model.addAttribute("cartCounter",  CartUtils.countCart((Map<Integer, CartModel>) session.getAttribute("cart")));
	}
	// product

	@GetMapping("/product/pagenated")
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
		/* model.addAttribute("listP", listP); */
		model.addAttribute("listCC", listCC);
		return "Product";
	}

	// getproductBycateId
	@GetMapping("/product/pagenated/cateId={cateId}")
	public String getproductBycateId(Model model, @PathVariable(name = "cateId") String cateId,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
		try {
			int cateid = Integer.parseInt(cateId);
			// List<Product> listP=productService.GetProductBycateId(cateId);
			Category cateEntity = categoryService.getById(cateid);
			List<Category> listCC = categoryService.findAll();
			int count = productService.countByCateId(cateid);
			System.out.println(count);
			int currentpage = page.orElse(1);
			int pageSize = size.orElse(3);
			Pageable pageable = PageRequest.of(currentpage - 1, pageSize, Sort.by("pId"));

			Page<Product> resultPage = null;

			resultPage = productService.findByCategory(cateEntity, pageable);

			int totalPages = resultPage.getTotalPages();
			System.out.println(totalPages);
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
			model.addAttribute("listCC", listCC);
			// model.addAttribute("listP", listP);
			model.addAttribute("cateId", cateid);
			return "PagingCategory";
		} catch (Exception e) {
			return "PagingCategory";
		}
	}

	@RequestMapping("/product/searchpagenated")
	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
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
		//System.out.println(productService.findByPNameContaining("", pageable).getContent());
		return "searchProduct";

	}

	// end product

	// start detail
	@GetMapping("/detail/id={id}")
	public String detail(Model model, @PathVariable(name = "id") String id)

	{
		try {
			int pid = Integer.parseInt(id);
			Product product = productService.getById(pid);
			List<envaluate> listEnvaluate = envaluateService.findAllBypId(pid);
			System.out.println(product);
			System.out.println(1);
			List<Product> listP = productService.GetTop20ProductBycateId(product.getCategory().getCId());
			model.addAttribute("p", product);
			model.addAttribute("listP", listP);
			model.addAttribute("listComment", listEnvaluate);
			return "detail";
		} catch (Exception e) {
			return "detail";
		}

	}

	@PostMapping("/detail/id={id}")
	public String detail(Model model, @PathVariable(name = "id") String id,
			@RequestParam(name = "content") String content, RedirectAttributes redirectAttributes) {
		user acc = (user) httpSession.getAttribute("acc");
		if (acc == null) {
			return "redirect:/login";
		} else {
			int pid = Integer.parseInt(id);
			Product product = productService.getById(pid);

			// post envaluate
			envaluateModel envaluateModel = new envaluateModel();
			envaluate entity = new envaluate();
			entity.setProduct(product);
			entity.setContent(content);
			entity.setUser(acc);
			envaluateService.save(entity);
			redirectAttributes.addFlashAttribute("mess", "Đăng bình luận thành công");
			return "redirect:/detail/id={id}";
		}

	}

	// end

}
