package org.springframework.samples.petclinic.productrecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product-recipes")
public class ProductRecipeController {

	@Autowired
	private final ProductRecipeService productRecipeService;

	private final ProductRecipeRepository productRecipeRepository;

	public ProductRecipeController(ProductRecipeService productRecipeService,
			ProductRecipeRepository productRecipeRepository) {
		this.productRecipeRepository = productRecipeRepository;
		this.productRecipeService = productRecipeService;
	}

	@GetMapping
	public ResponseEntity<List<ProductRecipe>> getAllRecipes(@RequestParam Optional<String> type) {
		try {
			List<ProductRecipe> recipes;
			if (type.isPresent()) {
				recipes = productRecipeService.getRecipesByType(type.get());
			}
			else {
				recipes = productRecipeRepository.findAll();
			}
			return ResponseEntity.ok(recipes); // 200
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
		}
	}

	@GetMapping("/category/{recipeCategory}")
	public ResponseEntity<List<ProductRecipe>> getRecipesByCategoryAndDietaryPreference(
			@PathVariable String recipeCategory, @RequestParam Optional<String> dietaryPreference) {
		List<ProductRecipe> recipes = productRecipeService.findByCategoryAndDietaryPreferenceIgnoreCase(recipeCategory,
				dietaryPreference.orElse(null));
		if (recipes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(recipes);
	}

	@GetMapping("/{recipeId}")
	public ResponseEntity<ProductRecipe> getRecipe(@PathVariable("recipeId") Long id) {
		return productRecipeRepository.findById(id)
			.map(recipe -> ResponseEntity.ok(recipe)) // 200
			.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // 404
	}

	@PostMapping("/new")
	public ResponseEntity<ProductRecipe> addRecipe(@RequestBody ProductRecipe productRecipe) {
		if (productRecipe == null) {
			return ResponseEntity.badRequest().build(); // 400
		}
		ProductRecipe savedProductRecipe = productRecipeRepository.save(productRecipe);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProductRecipe);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductRecipe> updateRecipe(@PathVariable Long id, @RequestBody ProductRecipe updatedRecipe) {
		return productRecipeRepository.findById(id).map(existingRecipe -> {
			existingRecipe.setIngredients(updatedRecipe.getIngredients());
			existingRecipe.setInstructions(updatedRecipe.getInstructions());
			existingRecipe.setType(updatedRecipe.getType());
			existingRecipe.setCategory(updatedRecipe.getCategory());
			existingRecipe.setDietaryPreference(updatedRecipe.getDietaryPreference());
			existingRecipe.setInternalNotes(updatedRecipe.getInternalNotes());
			productRecipeRepository.save(existingRecipe);
			return ResponseEntity.ok(existingRecipe); // 200
		}).orElseGet(() -> ResponseEntity.notFound().build()); // 404
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
		if (!productRecipeRepository.existsById(id)) {
			return ResponseEntity.notFound().build(); // 404
		}
		productRecipeRepository.deleteById(id);
		return ResponseEntity.noContent().build(); // 204
	}

}
