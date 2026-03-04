package org.springframework.samples.petclinic.store;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.validation.UniqueDomain;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_categories")
@Getter
@Setter

public class ProductCategory extends NamedEntity {
	@OneToMany(mappedBy = "productCategory")
	private List<Product> products = new ArrayList<>();
}

