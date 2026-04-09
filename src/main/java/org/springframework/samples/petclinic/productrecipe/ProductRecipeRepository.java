package org.springframework.samples.petclinic.productrecipe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.codesignal.Recipe;

import java.util.List;

public interface ProductRecipeRepository extends JpaRepository<ProductRecipe, Long> {
	List<ProductRecipe> findByCategoryIgnoreCase(String category);
	List<ProductRecipe> findByTypeIgnoreCase(String type);
}
