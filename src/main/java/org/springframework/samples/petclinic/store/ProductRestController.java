package org.springframework.samples.petclinic.store;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRestController {

	private final ProductRepository productRepository;

	// GET ALL (Paginated)
	@GetMapping
	public ResponseEntity<Page<Product>> getAll(@RequestParam(defaultValue = "0") int page) {
		return ResponseEntity.ok(productRepository.findAll(PageRequest.of(page, 5)));
	}

	// GET ONE BY ID
	@GetMapping("/{id}")
	public ResponseEntity<Product> getOne(@PathVariable Long id) {
		return productRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	// POST (Create)
	@PostMapping
	public ResponseEntity<Product> create(@Valid @RequestBody Product product) {
		productRepository.save(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}

	// PUT (Update)
	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product details) {
		return productRepository.findById(id).map(existing -> {
			existing.setName(details.getName());
			existing.setPrice(details.getPrice());
			existing.setQuantity(details.getQuantity());
			existing.setDomain(details.getDomain());
			productRepository.save(existing);
			return ResponseEntity.ok(existing);
		}).orElse(ResponseEntity.notFound().build());
	}

	// DELETE (Need to add deleteById to your ProductRepository)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		return productRepository.findById(id).map(product -> {
			productRepository.deleteById(id); // This line MUST execute
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}).orElse(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}

}
