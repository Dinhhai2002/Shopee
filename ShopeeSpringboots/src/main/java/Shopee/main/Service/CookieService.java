package Shopee.main.Service;
import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CookieService {
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpServletResponse response;

	public Cookie create(String username,String value, int days) {
		
		Cookie cookie = new Cookie(username, value);
		cookie.setMaxAge(days * 24 * 60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}

	public Cookie read(String username,String password) {
		Cookie[] cookies = request.getCookies(); //đọc từ client
		if (cookies != null) {
			for(Cookie o: cookies)
			{
				if(o.getName().equals(username))
				{
					request.setAttribute(username, o.getValue());
				}
				if(o.getName().equals(password))
				{
					request.setAttribute(password, o.getValue());
				}
			}
		}
		return null;
	}

	public void delete(String name) {
		this.create(name, "", 0); //thời hạn tồn tại cookie = 0
	}
}
