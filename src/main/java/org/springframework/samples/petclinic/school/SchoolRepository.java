package org.springframework.samples.petclinic.school;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;

public interface SchoolRepository extends Repository<School, Integer> {
	@Transactional(readOnly = true)
	Collection<School> findAll();

	@Transactional(readOnly = true)
	Page<School> findAll(Pageable pageable);

	void save(School school);

	@Transactional(readOnly = true)
	School findById(Integer id);
}
