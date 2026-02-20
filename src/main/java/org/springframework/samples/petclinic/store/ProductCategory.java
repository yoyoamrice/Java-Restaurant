package org.springframework.samples.petclinic.store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.validation.UniqueDomain;

@Entity
@Table(name = "product_categories")
@UniqueDomain
@Getter
@Setter

public class ProductCategory {
	@Getter
	@Setter
	@Id
	@Column
	private Long id;
	@Column
	private String name;

}

