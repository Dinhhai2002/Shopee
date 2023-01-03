package Shopee.main.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Shopee.main.Service.CookieService;
import Shopee.main.Service.iUserService;
import Shopee.main.Service.impl.SendEmail;
import Shopee.main.entity.user;
import Shopee.main.model.loginModel;


@Controller
public class loginController {
	@Autowired
	HttpSession httpSession;
	@Autowired
	CookieService cookie;
	@Autowired
	iUserService userService;
	@Autowired
	SendEmail sendEmail;
	

	@GetMapping(value = { "/login" })
	public String getLogin(ModelMap model) {
		model.addAttribute("account", new loginModel());
		 cookie.read("username","password");
		return "login";
	}
	
	//Login Form
	@PostMapping(value = { "/login" })
	public String login(Model model, @ModelAttribute("username") String username,@ModelAttribute("password") String password,@RequestParam(value = "remember", defaultValue = "false") boolean remember) throws Exception {
		try
		{
			user acc = userService.getUserByuName(username);
			String uPass=acc.getUPassword();
			
			if (acc != null && BCrypt.checkpw(password, uPass)==true) {
				
				
				httpSession.setAttribute("acc",acc);
				if (remember ==true) {
					System.out.println("khac null");
					cookie.create("username", acc.getUName(), 3);
					cookie.create("password", password, 3);
					
				} else {
					System.out.println("la null");
					cookie.create("username",acc.getUName(), 3);
					cookie.delete("password");
				}
				
				 if(acc.getUserRole()=="Role_shipper") { return "redirect:/orderShipper"; }
				 if(acc.getUserRole()=="Role_admin") { return "redirect:/admin"; }
				 
				
				return "redirect:/home";
			} else {
				model.addAttribute("mess", "tài khoản hoặc mật khẩu không chính xác");
				return "login";
			}
		}catch (Exception e) {
			model.addAttribute("mess", "tài khoản hoặc mật khẩu không chính xác");
			return "login";
		}
		
	}
	
	//Logout
	@GetMapping("/logout")
	public String logout(ModelMap model)
	{
		httpSession.removeAttribute("acc");
		return "redirect:/home";
	} 
	
	//Signup
	@GetMapping("/signup")
	public String Singup(Model model)
	{
		return "register";
	}
	
	
	//SignUp
	@PostMapping("/signup")
	public String SignUp(Model model,@RequestParam(name="username") String username,@RequestParam(name="fullname") String fullname
			,@RequestParam(name="password") String password,
			@RequestParam(name="re_pass") String re_pass,
			@RequestParam(name="email") String email,
			@RequestParam(name="phoneNumber") String phoneNumber,
			@RequestParam(name="address") String address,@RequestParam(name="fullAddress") String fullAddress,RedirectAttributes redirectAttributes)
	{
		user checkemail=userService.CheckEmail(email);
		user checkPhoneNumber=userService.CheckPhoneNumber(phoneNumber);
		user checkUsername=userService.checkUsername(username);
		
		//check
		if(checkemail!=null && checkemail.getIsAccountGoogle()==0)
		{
			model.addAttribute("mess", "mail này đã tồn tại");
			return "register";
		}
		else if(checkPhoneNumber!=null)
		{
			model.addAttribute("mess", "số điện thoại này đã tồn tại");
			return "register";
		}
		else if(checkUsername!=null)
		{
			model.addAttribute("mess", "Tên đăng nhập đã tồn tại");
			return "register";
		}
		else {
			//userService.insertAccount(username, fullname, email, address, fullAddress, password, phoneNumber);
			//redirectAttributes.addFlashAttribute("mess", "Đăng kí thành công");
			Random rand = new Random();
			int otpvalue = rand.nextInt(1255650);
			sendEmail.sendSimpleEmail(email, "Mã OTP","Mã OTP là:"+otpvalue);
			httpSession.setAttribute("otp", otpvalue);
			httpSession.setAttribute("email",email);
			httpSession.setAttribute("username",username);
			httpSession.setAttribute("fullname", fullname);
			httpSession.setAttribute("password", BCrypt.hashpw(password, BCrypt.gensalt(12)));
			httpSession.setAttribute("phoneNumber", phoneNumber);
			httpSession.setAttribute("address", address);
			httpSession.setAttribute("fullAddress", fullAddress);
			
			
			return "redirect:/otpSignup";
		}
		
	}
	
	
	//forward OTP
	@GetMapping("/otpSignup")
	public String OTP(Model model)
	{
		return "EnterOtpSignUp";
	}
	
	
	@PostMapping("/otpSignup")
	public String OTP(Model model,@RequestParam("otp") String otp,RedirectAttributes redirectAttributes)
	{
		//get Day and set format
		Calendar cal = Calendar.getInstance();  
		//creating a constructor of the SimpleDateFormat class  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		//getting current date  
		System.out.println("Today's date: "+sdf.format(cal.getTime()));  
		
		
	int otpSession=(int) httpSession.getAttribute("otp");
	String username=(String) httpSession.getAttribute("username");
	String password=(String) httpSession.getAttribute("password");
	String fullname=(String) httpSession.getAttribute("fullname");
	String  email=(String) httpSession.getAttribute("email");
	String phoneNumber=(String) httpSession.getAttribute("phoneNumber");
	String address=(String) httpSession.getAttribute("address");
	String fullAddress=(String) httpSession.getAttribute("fullAddress");
	int otpvalue=Integer.parseInt(otp);
	if(otpSession==otpvalue)
	{
		user entity=new user();
		entity.setUName(username);
		entity.setUFullName(fullname);
		entity.setUEmail(email);
		entity.setUAddress(address);
		entity.setUFullAddress(fullAddress);
		entity.setUPassword(password);
		entity.setUPhone(phoneNumber);
		entity.setUserRole("Role_user");
		entity.setIsAccountGoogle(0);
		entity.setCreateAt(cal.getTime());
		userService.insertAccount(username, fullname, email, address, fullAddress, password, phoneNumber);
		userService.save(entity);
		redirectAttributes.addFlashAttribute("mess", "Đăng kí thành công");
		return"redirect:/login";
	}
	else {
		model.addAttribute("mess", "Mã otp chưa chính xác");
		return "EnterOtpSignUp";
	}
	}
	
	//start Forgot password
	@GetMapping("/OtpForgot")
	public String OtpForgot(Model model)
	{
		return "EnterOtp";
	}
	@GetMapping("/ForgotPasswordEmail")
	public String ForgotPasswordEmail(Model model)
	{
		return "forgotPassword";
	}
	
	@PostMapping("/ForgotPasswordEmail")
	public String ForgotPasswordEmail(Model model,@RequestParam("username") String username,@RequestParam("email") String email)
	{
		user checkAccount=userService.checkAccountForgot(username, email);
		if(checkAccount!=null)
		{
			Random rand = new Random();
			int otpvalue = rand.nextInt(1255650);
			sendEmail.sendSimpleEmail(email, "Mã OTP","Mã OTP là:"+otpvalue);
			httpSession.setAttribute("otp", otpvalue);
			httpSession.setAttribute("email",email);
			httpSession.setAttribute("username",username);
			httpSession.setAttribute("accUpdate",checkAccount);
			
			return "redirect:/OtpForgot";
		}
		else {
			model.addAttribute("mess", "Tên tài khoản hoặc email không tồn tại");
			return "forgotPassword";
		}
		
	}
	
	@PostMapping("/OtpForgot")
	public String OtpForgot(Model model,@RequestParam("otp") String otp)
	{
		
		int otpSession=(int) httpSession.getAttribute("otp");
		int otpvalue=Integer.parseInt(otp);
		if(otpSession==otpvalue)
		{
			return "redirect:/newpassword";
		}
		else {
			model.addAttribute("mess", "Mã otp chưa chính xác");
			return "EnterOtp";
		}
		
	}
	
	@GetMapping("newpassword")
	public String newPassword(Model model)
	{
		return "newPassword";
	}
	
	@PostMapping("newpassword")
	public String newPassword(Model model,@RequestParam("password") String password,@RequestParam("confPassword")String confPassword,RedirectAttributes redirectAttributes)
	{
		user accUpdate=(user)httpSession.getAttribute("accUpdate");
		if(password.equals(confPassword))
		{
			accUpdate.setUPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));
			userService.save(accUpdate);
			redirectAttributes.addFlashAttribute("mess", "Reset mật khẩu thành công");
			return "redirect:/login";
		}
		else {
			model.addAttribute("mess", "không phù hợp");
			return "newPassword";
		}
	}
	
	
	
}
