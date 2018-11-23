package me.devstar.web.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import me.devstar.common.entity.AbstractBaseModel;
import me.devstar.common.entity.SearchForm;
import me.devstar.common.repository.BaseCrudRepository;
import me.devstar.common.service.BaseCrudService;

/**
 * 기본 CRUD 서비스를 구현한 추상 클래스
 */
@Transactional(readOnly = true)
public abstract class AbstractBaseCrudService<T extends AbstractBaseModel<ID>, F extends SearchForm, R extends BaseCrudRepository<T, ID>, ID extends Serializable>
		implements BaseCrudService<T, F, ID> {

	private static final Logger log = LoggerFactory.getLogger(AbstractBaseCrudService.class);

	protected R repository;

	@Override
	@Transactional
	public T create(T t) {
		return repository.saveAndFlush(t);
	}

	/**
	 * 검색 조건을 생성한다.
	 * @param searchForm
	 * @return
	 */
	public Specification<T> generateSpecification(F searchForm) {
		String key = searchForm.getSearchKey();
		String value = searchForm.getSearchValue();
		boolean isShowAll = searchForm.isShowAll();
		Specification<T> spec = Specification.where((root, query, cb) -> {
			final Collection<Predicate> predicates = new ArrayList<>();
			if (!isShowAll) {
				predicates.add(cb.equal(root.get("isDeleted"), false));
			}
			if (!StringUtils.isEmpty(value)) {
				predicates.add(cb.like(root.get(key), "%" + value + "%"));
			}
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		});
		return spec;
	}

	@Override
	public T get(final ID id) {
		return repository.getOne(id);
	}

	@Override
	public Page<T> getList(final F searchForm) {
		return repository.findAll(generateSpecification(searchForm), searchForm.getPageable());
	}

	/**
	 * @return the repository
	 */
	public R getRepository() {
		return repository;
	}

	@Override
	@Transactional
	public T modify(T t) {
		return repository.saveAndFlush(t);
	}

	@Override
	@Transactional
	public T modify(T t, final String currentUserId) {
		T tmp = get(t.getId());
		if (tmp.getCreatedBy().equals(currentUserId)) {
			return repository.saveAndFlush(t);
		} else {
			log.warn("modify authorized exception - target : [{}], current user : [{}]", tmp.getId().toString(),
					currentUserId);
			throw new RuntimeException("You are not authorized.");
		}
	}

	@Override
	@Transactional
	public void remove(final ID id) {
		T t = get(id);
		t.setDeleted(true);
		modify(t);
	}

	@Override
	@Transactional
	public void remove(final ID id, final String currentUserId) {
		T tmp = get(id);
		if (tmp.getCreatedBy().equals(currentUserId)) {
			tmp.setDeleted(true);
			modify(tmp);
		} else {
			log.warn("remove authorized exception - target : [{}], current user : [{}]", id.toString(), currentUserId);
			throw new RuntimeException("You are not authorized.");
		}
	}

	@Override
	@Transactional
	public void remove(final Iterable<T> iter) {
		Set<T> entities = new HashSet<>();
		for (T t : iter) {
			t.setDeleted(true);
			entities.add(t);
		}
		repository.saveAll(entities);
		repository.flush();
	}

	@Override
	@Transactional
	public void remove(final Iterable<T> iter, final String currentUserId) {
		Set<T> entities = new HashSet<>();
		T tmp = null;
		for (T t : iter) {
			tmp = get(t.getId());

			if (!currentUserId.equals(tmp.getCreatedBy())) {
				log.warn("remove authorized exception - target : [{}], current user : [{}]", tmp.getId().toString(),
						currentUserId);
				throw new RuntimeException("You are not authorized.");
			}
			tmp.setDeleted(true);
			entities.add(tmp);
		}
		repository.saveAll(entities);
		repository.flush();
	}

	@Override
	@Transactional
	public void removeByPhysical(final ID id) {
		repository.delete(repository.getOne(id));
	}

	@Override
	@Transactional
	public void removeByPhysical(final ID id, final String currentUserId) {
		T tmp = get(id);
		if (tmp.getCreatedBy().equals(currentUserId)) {
			remove(id);
		} else {
			log.warn("remove authorized exception - target : [{}], current user : [{}]", id.toString(), currentUserId);
			throw new RuntimeException("You are not authorized.");
		}
	}

	@Override
	@Transactional
	public void removeByPhysical(final Iterable<T> iter) {
		repository.deleteAll(iter);
		repository.flush();
	}

	@Override
	@Transactional
	public void removeByPhysical(final Iterable<T> iter, final String currentUserId) {
		Set<T> entities = new HashSet<>();
		T tmp = null;
		for (T t : iter) {
			tmp = get(t.getId());
			if (!currentUserId.equals(tmp.getCreatedBy())) {
				log.warn("remove authorized exception - target : [{}], current user : [{}]", tmp.getId().toString(),
						currentUserId);
				throw new RuntimeException("You are not authorized.");
			}
			entities.add(tmp);
		}
		removeByPhysical(entities);
	}

	/**
	 * @param repository
	 * @throws Exception
	 */
	public void setRepository(R repository) {
		this.repository = repository;
	}
}
