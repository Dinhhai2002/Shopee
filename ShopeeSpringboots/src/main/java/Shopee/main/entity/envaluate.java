package Shopee.main.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="evaluate")
public class envaluate implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="content")
	private String content;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="pid")
	private Product product;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="uid")
	private user user;
	
}
