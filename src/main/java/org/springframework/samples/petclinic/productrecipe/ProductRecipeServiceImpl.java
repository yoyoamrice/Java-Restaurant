package org.springframework.samples.petclinic.productrecipe;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductRecipeServiceImpl implements ProductRecipeService {

	private final ProductRecipeRepository productRecipeRepository;

	public ProductRecipeServiceImpl(ProductRecipeRepository recipeRepository) {
		this.productRecipeRepository = recipeRepository;
	}

	@Override
	public List<ProductRecipe> getRecipesByType(String type) {
		if (type == null || type.isEmpty()) {
			return productRecipeRepository.findAll();
		}
		return productRecipeRepository.findByTypeIgnoreCase(type);
	}

	@Override
	public List<ProductRecipe> findByCategoryAndDietaryPreferenceIgnoreCase(String category, String preference) {
		// 1. Get all recipes in that category from the DB
		List<ProductRecipe> categoryRecipes = productRecipeRepository.findByCategoryIgnoreCase(category);

		// 2. If a preference was provided, filter the list in Java
		if (preference != null && !preference.isEmpty()) {
			return categoryRecipes.stream()
				.filter(recipe -> recipe.getDietaryPreference().equalsIgnoreCase(preference))
				.collect(Collectors.toList());
		}

		// 3. Otherwise, return everything in that category
		return categoryRecipes;
	}

}
