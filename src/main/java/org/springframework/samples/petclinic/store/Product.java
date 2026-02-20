package org.springframework.samples.petclinic.store;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.samples.petclinic.school.Location;
import org.springframework.samples.petclinic.validation.UniqueDomain;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@UniqueDomain
@Getter
@Setter

public class Product {
	@Getter
	@Setter
	@Id
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private String domain;
	@Column
	private int quantity;
	@Column
	private double price;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "id", fetch = FetchType.EAGER)
	private List<ProductCategory> categories = new ArrayList<>();

}
