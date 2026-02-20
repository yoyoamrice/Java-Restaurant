package org.springframework.samples.petclinic.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.school.School;
import org.springframework.samples.petclinic.school.SchoolRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ProductController {
private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
		this.productRepository = productRepository;
    }
	@GetMapping("/products")
	public String showProductList(@RequestParam(defaultValue = "1") int page, Model model) {
		// Pagination setup (5 items per page)
		Pageable pageable = PageRequest.of(page - 1, 5);
		Page<Product> productPage = productRepository.findAll(pageable);

		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", productPage.getTotalPages());
		model.addAttribute("totalItems", productPage.getTotalElements());
		model.addAttribute("listProducts", productPage.getContent());

		return "products/productList";
	}
}
