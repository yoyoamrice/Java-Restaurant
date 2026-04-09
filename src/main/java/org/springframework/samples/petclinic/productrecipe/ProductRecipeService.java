package org.springframework.samples.petclinic.productrecipe;

import java.util.List;

public interface ProductRecipeService {
	List<ProductRecipe> getRecipesByType(String type);
	List<ProductRecipe> findByCategoryAndDietaryPreferenceIgnoreCase(String category, String dietaryPreference);
}
