package Shopee.main.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class userModel {
	private int uId;
	private String uName;
	private String uFullName;
	private String uEmail;
	private String uAddress;
	private String uFullAddress;
	private String uPassword;
	private String uPhone;
	private int idRole;
	private int isAccountGoogle;
	private String uImage;
	private Date createAt;
}
