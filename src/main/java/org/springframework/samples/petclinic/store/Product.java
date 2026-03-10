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
	@NotEmpty(message="Domain is required")
	private String domain;
	@Column(nullable = true)
	@NotNull(message="Quantity is required")

	@Positive( message = "Must be a positive integer")
	private Integer quantity;
	@NotNull(message="Price is required")

	@Positive(message="must be positive decimal")
	@Digits(integer = 6, fraction = 2, message="must be positive decimal")
	@Column(precision = 8, scale = 2)
	private BigDecimal price;

	@OneToOne
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;

}
