package Shopee.main.googleAccess;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Shopee.main.Service.iUserService;
import Shopee.main.entity.user;
import Shopee.main.repository.userRepository;

@Controller
public class LoginGoogleServlet  {
	
	@Autowired
	iUserService userService;
	@Autowired
	HttpSession httpSession;
	@GetMapping("/loginGoogle")
	public String loginGoogle(Model model,@RequestParam(name="code")String code)
	{
	
		if (code == null || code.isEmpty()) {
			return"login";
		} else {
			try {
				String accessToken = GoogleUtils.getToken(code);
				GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
				String username = googlePojo.getName();
				String email = googlePojo.getEmail();
				String image = googlePojo.getPicture();
				
				int NumberAccountGoogle = userService.numberAccount(username, email);
				user checkAccountGoogle = userService.checkAccountForgot(username, email);
				if (NumberAccountGoogle == 1 && checkAccountGoogle.getIsAccountGoogle() == 1) {
					
					httpSession.setAttribute("acc", checkAccountGoogle);
					return"redirect:/home";
				} else if (NumberAccountGoogle == 0) {
					/*
					 * userService.insertAcountGoogle(username, email, image);
					 * System.out.println("vao day");
					 */
					user userEntity=new user();
					userEntity.setUName(username);
					userEntity.setUEmail(email);
					userEntity.setUImage(image);
					userEntity.setIdRole(1);
					userEntity.setIsAccountGoogle(1);
					userService.save(userEntity);

					
					httpSession.setAttribute("acc", userService.checkAccountForgot(username, email));
					return"redirect:/home";

				} else {
					return"login";
				}
			} catch (Exception e) {
				return"login";
			}
			
			// request.setAttribute("id", googlePojo.getId());
			// request.setAttribute("name", googlePojo.getName());
			// request.setAttribute("email", googlePojo.getEmail());

		}
	}
}
