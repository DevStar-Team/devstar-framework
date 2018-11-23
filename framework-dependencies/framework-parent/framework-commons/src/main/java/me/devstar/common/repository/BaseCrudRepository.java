package me.devstar.common.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * JPA 기반 기본 CRUD Repository
 */
@NoRepositoryBean
public interface BaseCrudRepository<T, ID extends Serializable>
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

	/**
	 * ID와 작성자를 이용하여 개체를 삭제한다.
	 * @param id
	 */
	void deleteById(ID id);

	/**
	 * ID를 이용하여 복수의 개체를 삭제한다.
	 * @param ids
	 */
	void deleteByIdIn(Iterable<ID> ids);

	/**
	 * ID를 이용하여 복수의 개체를 가져온다.
	 * @param ids
	 * @return
	 */
	List<T> findByIdIn(Iterable<ID> ids);
}
