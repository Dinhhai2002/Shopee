package Shopee.main.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import Shopee.main.Service.CookieService;
import Shopee.main.Service.iUserService;
import Shopee.main.entity.user;

@Component	
public class loginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	iUserService userService;
	@Autowired
	HttpSession httpSession;
	@Autowired
	CookieService cookie;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		
		User user= (User) authentication.getPrincipal();
		user acc=userService.checkLogin(user.getUsername());
		String remember=request.getParameter("remember");
		String password=request.getParameter("password");
		if (remember !=null) {
			System.out.println("khac null");
			cookie.create("username", acc.getUName(), 3);
			cookie.create("password", password, 3);
			
		} else {
			System.out.println("la null");
			cookie.create("username",acc.getUName(), 3);
			cookie.delete("password");
		}
		//System.out.println(acc);
		
		
		String redirectUrl=request.getContextPath();
		
		 httpSession.setAttribute("acc",acc);
		  if(acc.getUserRole().equals("Role_admin"))
		  { 
			  redirectUrl +="/admin";
			 // System.out.println("vao day");
		  
		  }
		  else if(acc.getUserRole().equals("Role_shipper"))
		  {
			 
			  redirectUrl +="/shipper/orderShipper";
		  }
		  else 
		  {
			 
			  redirectUrl +="/home";
		  }
		 
		 response.sendRedirect(redirectUrl);
		 
		super.onAuthenticationSuccess(request, response, authentication);
	}
	

	
	
}
