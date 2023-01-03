package Shopee.main.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name="[user]")
public class user implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="uid")
	private int uId;
	
	@Column(name="uname")
	private String uName;
	
	@Column(name="ufullname")
	private String uFullName;
	
	@Column(name="uemail")
	private String uEmail;
	@Column(name="uaddress")
	private String uAddress;
	@Column(name="ufulladdress")
	private String uFullAddress;
	@Column(name="upassword")
	private String uPassword;
	@Transient
	private String confirmPassword;
	@Column(name="uphone")
	private String uPhone;
	@Column(name="userrole")
	private String userRole;
	@Column(name="isaccountgoogle")
	private int isAccountGoogle;
	@Column(name="isactive")
	private int isActive;
	
	@Column(name="uimage")
	private String uImage;
	@Column(name="createat")
	private Date createAt;
	
}
