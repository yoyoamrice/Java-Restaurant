package org.springframework.samples.petclinic.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	private final ProductCategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public Page<Product> findAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public List<ProductCategory> findAllCategories() {
		return categoryRepository.findAll().stream().toList();
	}

	@Transactional(readOnly = true)
	public Optional<Product> findProductById(Long id) {
		return productRepository.findById(id);
	}

	@Transactional
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	@Transactional
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
