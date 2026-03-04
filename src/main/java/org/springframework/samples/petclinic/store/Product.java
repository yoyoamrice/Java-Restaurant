package org.springframework.samples.petclinic.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.NamedEntity;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends NamedEntity{
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
	@ManyToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;

}
