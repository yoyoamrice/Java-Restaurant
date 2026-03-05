package org.springframework.samples.petclinic.store;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.NamedEntity;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends NamedEntity{
	@Column
	@NotEmpty
	private String domain;
	@Column(nullable = true)
	@NotNull

	@Positive( message = "Must be a positive integer")
	private int quantity;
	@NotNull

	@Positive(message="must be positive decimal")
	@Digits(integer = 6, fraction = 2, message="must be positive decimal")
	@Column(precision = 8, scale = 2)
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;

}
