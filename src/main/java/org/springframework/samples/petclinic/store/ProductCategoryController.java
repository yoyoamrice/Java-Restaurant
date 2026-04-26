package org.springframework.samples.petclinic.store;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") // 🔥 ALL methods restricted
public class ProductCategoryController {

	private final ProductCategoryService categoryService;

	// LIST
	@GetMapping
	public String listCategories(Model model) {
		model.addAttribute("categories", categoryService.findAllCategories());
		return "categories/categoryList";
	}

	// CREATE FORM
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("category", new ProductCategory());
		return "categories/createOrUpdateCategoryForm";
	}

	// SAVE (CREATE + UPDATE)
	@PostMapping({"/new", "/{id}/edit"})
	public String processForm(@Valid ProductCategory category, BindingResult result) {
		if (result.hasErrors()) {
			return "categories/createOrUpdateCategoryForm";
		}
		categoryService.saveCategory(category);
		return "redirect:/categories";
	}

	// EDIT
	@GetMapping("/{id}/edit")
	public String initUpdateForm(@PathVariable Long id, Model model) {
		ProductCategory category = categoryService.findCategoryById(id)
			.orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
		model.addAttribute("category", category);
		return "categories/createOrUpdateCategoryForm";
	}

	// DELETE
	@GetMapping("/{id}/delete")
	public String deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return "redirect:/categories";
	}
}
