package Shopee.main.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@Data

@AllArgsConstructor

@NoArgsConstructor
public class loginModel {
	@NotEmpty(message="user không được để trống")

	
	private String username;

	@NotEmpty(message="Mật khẩu không được để trống!")

	@Min(value = 6, message = "Password phải từ 6 kí tự trở lên")

	private String password;

	

	private Boolean rememberMe = false;
}
