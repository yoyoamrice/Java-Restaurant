package org.springframework.samples.petclinic.store;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface ProductCategoryRepository extends Repository<ProductCategory, Long> {

	@Transactional(readOnly = true)
	Collection<ProductCategory> findAll();

	@Transactional(readOnly = true)
	Optional<ProductCategory> findById(Long id);

}
