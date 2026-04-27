package org.springframework.samples.petclinic.store;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	// READ: View all List
	@GetMapping
	public String showProductList(@RequestParam(defaultValue = "1") int page, Model model) {
		Pageable pageable = PageRequest.of(page - 1, 5);
		Page<Product> productPage = productService.findAllProducts(pageable);

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("listProducts", productPage.getContent());
		return "products/productList"; // Consistent folder path
	}

	// 1. VIEW Details: Matches th:href="@{/products/{id}(id=${product.id})}"
	@GetMapping("/{id}")
	public String viewProduct(@PathVariable("id") Long id, Model model) {
		Product product = productService.findProductById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
		model.addAttribute("product", product);
		return "products/productDetails"; // Changed to match your folder structure
	}

	// 2. CREATE Form
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", productService.findAllCategories());
		return "products/createOrUpdateProductForm";
	}

	// 3. EDIT Form: Allows both ADMIN and MANAGER to access
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@GetMapping("/{id}/edit")
	public String initUpdateForm(@PathVariable("id") Long id, Model model) {
		Product product = productService.findProductById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
		model.addAttribute("product", product);
		model.addAttribute("categories", productService.findAllCategories());
		return "products/createOrUpdateProductForm";
	}

	// 4. SAVE/PROCESS: Handles the actual update logic
	@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
	@PostMapping({ "/new", "/{id}/edit" })
	public String processProductForm(@Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("categories", productService.findAllCategories());
			return "products/createOrUpdateProductForm";
		}
		productService.saveProduct(product);
		return "redirect:/products";
	}

	// 5. DELETE: Matches th:href="@{/products/{id}/delete(id=${product.id})}"
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}/delete")
	public String deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return "redirect:/products";
	}

}
