package Shopee.main.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductModel {
	private int pId;
	private String pName;
	private float pPrice;
	private MultipartFile imageFile;
	private String pImage;
	private String pDescription;
	private int pQuantity;
	private int cateId;
	private int shopId;
	private LocalDate createAt;
	private Date updateAt;
	
	private Boolean isSources=false;
}
