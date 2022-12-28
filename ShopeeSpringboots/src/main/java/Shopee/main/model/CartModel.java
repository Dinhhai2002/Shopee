package Shopee.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {
	private int productId;
	private String productName;
	private String productImage;
	private long price;
	private int quantity;
	
}
