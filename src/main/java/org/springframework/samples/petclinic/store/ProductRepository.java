package org.springframework.samples.petclinic.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends Repository<Product, Long> {
	@Transactional(readOnly = true)
	Collection<Product> findAll();

	@Transactional(readOnly = true)
	Page<Product> findAll(Pageable pageable);
	void save(Product product);

	@Transactional(readOnly = true)
	Optional<Product> findById(Long id);

	@Transactional(readOnly = true)
	@Query("SELECT s FROM Product s WHERE s.domain = :domain")
	Optional<Product> findByDomain(String domain);

}


