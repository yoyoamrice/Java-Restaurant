package org.springframework.samples.petclinic.store;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

	private final ProductCategoryRepository categoryRepository;

	@Transactional(readOnly = true)
	public List<ProductCategory> findAllCategories() {
		return categoryRepository.findAll().stream().toList();
	}

	@Transactional(readOnly = true)
	public Optional<ProductCategory> findCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	@Transactional
	public void saveCategory(ProductCategory category) {
		categoryRepository.save(category);
	}

	@Transactional
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}
