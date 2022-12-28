package Shopee.main.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@CrossOrigin
@Entity
@Table(name="[order]")
public class order implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderid")
	private int orderId;
	
	@ManyToOne
	@JoinColumn(name="uid")
	private user user;
	
	
	
	@Column(name="uname")
	private String uName;
	
	@Column(name="deliveryid")
	private int deliveryId;
	
	@Column(name="uphone")
	private String uPhone;
	@Column(name="uaddress")
	private String uAddress;
	@Column(name="amountfromuse")
	private Long amountFromUse;
	@Column(name="createat")
	private Date createAt;
}
