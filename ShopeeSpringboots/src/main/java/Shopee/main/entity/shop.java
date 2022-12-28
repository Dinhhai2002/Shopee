package Shopee.main.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@CrossOrigin
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="shop")
public class shop {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="shopid")
	private int shopId;
	
	@Column(name="shopname")
	private String shopName;
	
	@OneToOne
	@JoinColumn(name="uid")
	private user user;
	
	@OneToOne
	@JoinColumn(name="cateid")
	private Category category;
	
	@Column(name="shopimage")
	private String shopImage;
	
	@Column(name="shopdecription")
	private String shopDecription;
	
	@Column(name="shopaddress")
	private String shopAddress;
	
	@Column(name="createat")
	private Date createAt;
	@Column(name="isactive")
	private boolean isActive;
	@Column(name="isdelete")
	private boolean isDelete;
}
