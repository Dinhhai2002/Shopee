package Shopee.main.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@CrossOrigin

@Entity
@Table(name="Cart")
public class Cart implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cartid")
	private int cartId;
	
	@ManyToOne
	@JoinColumn(name="uId")
	private user user;
	
	@OneToOne
	@JoinColumn(name="pid")
	private Product product;
	
	@Column(name="count")
	private int count;
	@Column(name="totalPrice")
	private float totalPrice;
	@ManyToMany
	 private Set<shop> shops;
}
