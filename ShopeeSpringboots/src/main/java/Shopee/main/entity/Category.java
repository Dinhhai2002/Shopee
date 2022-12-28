package Shopee.main.entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@CrossOrigin
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(exclude="products")
@ToString(exclude="products")
@Entity
@Table(name="category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="cid")
	private int cId;
	
	@Column(name="cname")
	private String cName;
	
	@Column(name="cimage")
	private String cImage;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="createat")
	private LocalDate createAt;
	
	@Column(name="updateat")
	private Date updateAt;
	
	@JsonIgnore
	 @OneToMany(mappedBy="category", cascade = CascadeType.ALL ) 
	 private Set<Product> products;
	 
	

	
	
	
}
