package org.springframework.samples.petclinic.store;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
private final ProductRepository productRepository;

	private final ProductCategoryRepository categoryRepository;

	public ProductController(ProductRepository productRepository, ProductCategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	@GetMapping("/products")
	public String showProductList(
		@RequestParam(defaultValue = "1") int page, Model model) {
		// Pagination setup (5 items per page)
		Pageable pageable = PageRequest.of(page - 1, 5);
		Page<Product> productPage = productRepository.findAll(pageable);

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("totalItems", productPage.getTotalElements());
		model.addAttribute("listProducts", productPage.getContent());

		return "products/productList";

	}
	@GetMapping("/products/new")
	public String initCreationForm(Model model) {
		// Instaniate a default object
		Product product = new Product();
		// Add school to input model so Thymeleaf can bind data to it
		model.addAttribute("product", product);
		// Fetch all categories from the repository and add to the model
		List<ProductCategory> categories = categoryRepository.findAll().stream().toList();
		model.addAttribute("categories", categories);
		return "products/createOrUpdateProductForm";
	}

	@GetMapping("/products/{id}")
	public String showProductDetails(@PathVariable Long id, Model model) {

		Product product = productRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));

		model.addAttribute("product", product);
		return "products/productDetails";
	}
	@PostMapping("/products/new")
	public String processCreationForm(@Valid Product product, BindingResult result) {

		if (result.hasErrors()) {
			return "products/createOrUpdateProductForm";
		}

		productRepository.save(product);
		return "redirect:/products";
	}

}
