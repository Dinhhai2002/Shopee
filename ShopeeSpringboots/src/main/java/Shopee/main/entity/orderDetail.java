package Shopee.main.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="[orderdetail]")
public class orderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="orderid")
	private order order;
	
	@OneToOne
	@JoinColumn(name="productid")
	private Product product;
	@ManyToOne
	@JoinColumn(name="shopid")
	private shop shop;
	@Column(name="count")
	private int count;
	@Column(name="totalprice")
	private Long totalPrice;
	
	@OneToOne
	@JoinColumn(name="status")
	private orderStatus orderstatus;
	
	
	@Column(name="createat")
	private Date createAt;
}
