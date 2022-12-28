package Shopee.main.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor


@EqualsAndHashCode(exclude="category")
@ToString(exclude="category")
@CrossOrigin
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pid")
	private int pId;
	
	@Column(name="pname")
	private String pName;
	
	@Column(name="pprice")
	private float pPrice;
	
	@Column(name="pimage")
	private String pImage;
	
	@Column(name="pdescription")
	private String pDescription;
	
	@Column(name="pquantity")
	private int pQuantity;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="cateid")
	private Category category;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="shopid")
	private shop shop;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="createat")
	private LocalDate createAt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="updateat")
	private Date updateAt;
}
