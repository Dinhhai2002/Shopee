package Shopee.main.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopModel {
	private int shopId;
	private String shopName;
	private int uId;
	private int cateId;
	private String shopImage;
	private String shopDecription;
	private String shopAddress;
	private Date createAt;
	private boolean isActive;
	private boolean isDelete;
}
