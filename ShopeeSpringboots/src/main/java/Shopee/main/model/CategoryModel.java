package Shopee.main.model;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CategoryModel {
	private int cId;
	private String cName;
	private String cImage;
	private Date createAt;
	private Date updateAt;
	
	
	
	
}
