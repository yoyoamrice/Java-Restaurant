package org.springframework.samples.petclinic.productrecipe;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_recipes")
@Getter
@Setter
public class ProductRecipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "recipe_ingredients")
	@JsonProperty("recipe_ingredients")
	private String ingredients;

	private String instructions;

	private String type;

	private String category;

	private String dietaryPreference;

	private String internalNotes;

}
