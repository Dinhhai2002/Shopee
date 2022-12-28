package Shopee.main.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import Shopee.main.Service.iUserService;
import Shopee.main.Service.impl.SendEmail;
import Shopee.main.entity.user;

@Controller
public class MyAccountController {
	@Autowired
	iUserService userService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	SendEmail sendEmail;
	
	@GetMapping("/myAccount")
	public String myAccount(Model model)
	{
		return "MyAccount";
	}
	
	@PostMapping("/myAccount")
	public String myAccount(Model model,@RequestParam(name="id")int id,@RequestParam(name="SessionEmail")String SessionEmail,
			@RequestParam(name="username")String username,@RequestParam(name="usernameSession")String usernameSession,
			@RequestParam(name="phoneNumberSession")String phoneNumberSession,@RequestParam(name="fullName")String fullName,
			@RequestParam(name="phoneNumber")String phoneNumber,@RequestParam(name="address")String address,
			@RequestParam(name="email")String email,RedirectAttributes redirectAttributes)
	{
		user acc=userService.getById(id);
		user checkPhoneNumber=userService.CheckPhoneNumber(phoneNumber);
		user checkEmail=userService.CheckEmail(email);
		user a=userService.CheckAccountUpdate(username, fullName, email, address, phoneNumber);
		
		if(username.equals(usernameSession) )
		{
			if((a==null && phoneNumber.equals(phoneNumberSession) && SessionEmail.equals(email))||(a==null && !phoneNumber.equals(phoneNumberSession) && checkPhoneNumber==null && SessionEmail.equals(email)) )
			{
				
				acc.setUName(username);
				acc.setUFullName(fullName);
				acc.setUEmail(email);
				acc.setUAddress(address);
				acc.setUPhone(phoneNumber);
				userService.save(acc);
				httpSession.setAttribute("acc", userService.CheckAccountUpdate(username, fullName, email, address, phoneNumber));
				redirectAttributes.addFlashAttribute("mess", "Cập nhật thành công");
				return"redirect:/myAccount";
			}
			else if((a==null && phoneNumber.equals(phoneNumberSession) && !SessionEmail.equals(email)&&checkEmail==null)||(a==null && !phoneNumber.equals(phoneNumberSession) && checkPhoneNumber==null && !SessionEmail.equals(email)&&checkEmail==null)) 
			{
				//send mail
				Random rand = new Random();
				int otpvalue = rand.nextInt(1255650);
				sendEmail.sendSimpleEmail(email, "Mã OTP","Mã OTP là:"+otpvalue);
				
				httpSession.setAttribute("otp",otpvalue); 
				httpSession.setAttribute("id", id);
				httpSession.setAttribute("email",email);
				httpSession.setAttribute("username",username);
				httpSession.setAttribute("fullname", fullName);
				httpSession.setAttribute("phoneNumber", phoneNumber);
				httpSession.setAttribute("address", address);
				return"redirect:/validateUpdate";
			}
			else if(a!=null) {
				model.addAttribute("mess", "Email đã tồn tại hoặc thông tin chỉnh sửa không phù hợp");
				return "MyAccount";
				
			}
			}
		else {
			model.addAttribute("mess", "Email đã tồn tại hoặc thông tin chỉnh sửa không phù hợp");
			return "MyAccount";
		}
		return "MyAccount";
	}
	//start validate otp update
	
	@GetMapping("/validateUpdate")
	public String validateUpdate(Model model)
	{
		return"EnterOtpUpdateAccount";
	}
	
	@PostMapping("/validateUpdate")
	public String validateUpdate(Model model,@RequestParam("otp") String otp,RedirectAttributes redirectAttributes)
	{
		int otpvalue=Integer.parseInt(otp);
		int otpsession=(int)httpSession.getAttribute("otp");
		int id=(int) httpSession.getAttribute("id");
		String username=(String) httpSession.getAttribute("username");
		String fullname=(String) httpSession.getAttribute("fullname");
		String  email=(String) httpSession.getAttribute("email");
		String phoneNumber=(String) httpSession.getAttribute("phoneNumber");
		String address=(String) httpSession.getAttribute("address");
		
		if (otpvalue==otpsession) 
		{
			user acc=userService.getById(id);
			
			
			acc.setUName(username);
			acc.setUFullName(fullname);
			acc.setUEmail(email);
			acc.setUAddress(address);
			acc.setUPhone(phoneNumber);
			userService.save(acc);
			httpSession.setAttribute("acc", userService.CheckAccountUpdate(username, fullname, email, address, phoneNumber));
			redirectAttributes.addFlashAttribute("mess", "Cập nhật thành công");
			
			 
			return"redirect:/myAccount";
		}
		else
		{
			model.addAttribute("mess", "mã otp không chính xác");
			
			return"EnterOtpUpdateAccount";
		
		}
		
	}
	//end validate otp update
	
	//start changPassword Account
	@GetMapping("/changePassword")
	public String changPassword(Model model)
	{
		return "changePasswordAccount";
	}
	
	@PostMapping("/changePassword")
	public String changePassword(Model model,@RequestParam(name="id")int id,@RequestParam(name="oldPass")String oldPass,
			@RequestParam(name="pass")String pass,@RequestParam(name="repass")String repass,
			RedirectAttributes redirectAttributes)
	{
		user accountSession=(user) httpSession.getAttribute("acc");
		if(pass!=null &&pass.equals(repass) && BCrypt.checkpw(oldPass, accountSession.getUPassword())==true) {
			
			user account=userService.getById(id);
			account.setUPassword(BCrypt.hashpw(pass, BCrypt.gensalt(12)));
			userService.save(account);
			httpSession.setAttribute("acc",userService.checkLogin(accountSession.getUName()) );
			redirectAttributes.addFlashAttribute("mess", "Cập nhật thành công");
			return"redirect:/changePassword";
		}
		else {
			model.addAttribute("mess","thay đổi mật khẩu thất bại");
			return "changePasswordAccount";
		}
		
	}
	
}
