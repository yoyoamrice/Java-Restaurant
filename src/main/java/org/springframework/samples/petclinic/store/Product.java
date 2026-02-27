package org.springframework.samples.petclinic.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.validation.UniqueDomain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
	@Getter
	@Setter
	@Id
	@Column
	@GeneratedValue
	private Long id;
	@Column
	@NotEmpty
	private String name;
	@Column
	@NotEmpty
	private String domain;
	@Column
	@NotNull
	@Min(value=0, message="quantity must be 0>")
	private int quantity;
	@Column
	@NotNull
	@Min(value=0, message="price must be 0>")
	private double price;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "product_category_id")
	private ProductCategory category;

	public Product(){
		ProductCategory c=new ProductCategory();
		c.setId(1L);
		c.setName("furnature");
		this.category=c;
	}



}
